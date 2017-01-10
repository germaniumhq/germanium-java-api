package com.germaniumhq.germanium;

import com.germaniumhq.germanium.impl.AlertChecker;
import com.germaniumhq.germanium.impl.ScriptLoader;
import com.germaniumhq.germanium.locators.AlertLocator;
import com.germaniumhq.germanium.locators.CssLocator;
import com.germaniumhq.germanium.locators.JsLocator;
import com.germaniumhq.germanium.locators.Locator;
import com.germaniumhq.germanium.locators.XPathLocator;
import com.germaniumhq.germanium.util.Wait;
import com.germaniumhq.germanium.wa.Ie8LoadSupportScripts;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * GermaniumDriver.
 */
public class GermaniumDriver implements WebDriver, JavascriptExecutor, TakesScreenshot, HasInputDevices {
    private final static Logger log = Logger.getLogger(GermaniumDriver.class);

    private final WebDriver webDriver;
    private IFrameSelector iFrameSelector;
    private String screenshotFolder;
    private List<String> supportScripts;
    private String currentIFrame;

    private Map<String, Class<? extends Locator>> locatorMap = new HashMap<>();
    /**
     * Create a new GermaniumDriver instance wrapper over an existing
     * WebDriver instance.
     *
     * This will offer all the API capabilities of Germanium
     *
     * @param webDriver
     * @param iFrameSelector
     * @param screenshotFolder
     */
    public GermaniumDriver(WebDriver webDriver,
                           IFrameSelector iFrameSelector,
                           String screenshotFolder,
                           List<String> supportScripts) {
        this.webDriver = webDriver;
        this.iFrameSelector = iFrameSelector;
        this.screenshotFolder = screenshotFolder;
        this.supportScripts = supportScripts;

        locatorMap.put("xpath", XPathLocator.class);
        locatorMap.put("css", CssLocator.class);
        locatorMap.put("js", JsLocator.class);
        locatorMap.put("alert", AlertLocator.class);

        currentIFrame = this.iFrameSelector.selectIFrame(this, "default");
    }

    /**
     * Load the given page.
     * @param url
     */
    @Override
    public void get(String url) {
        get(url, 30f);
    }

    public void get(String url, float timeout) {
        AlertChecker.allowAlert(this, () -> {
            webDriver.get(url);
        });

        this.waitForPageToLoad(timeout);
    }

    public void waitForPageToLoad(float timeout) {
        AlertChecker alertChecker = new AlertChecker(this);

        new Wait(timeout).waitFor(
                alertChecker::isAlertExisting,
                () -> this.js("return 'complete' == document['readyState']"));

        if (alertChecker.isAlertExisting()) {
            log.warn("Since an alert was present, waitForPageToLoad " +
                    "exited prematurely, and the support scripts were not loaded. " +
                    "You need to call `getGermanium().loadSupportScripts()` " +
                    "manually.");
            return;
        }

        this.loadSupportScripts();
    }

    /**
     * Take a screenshot in the screenshot folder.
     * @param name
     */
    public void takeScreenshot(String name) {
        Path path = Paths.get(this.screenshotFolder, name + ".png");
        byte[] imageData = this.getScreenshotAs(OutputType.BYTES);

        try {
            Files.copy(new ByteArrayInputStream(imageData), path);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format(
                    "Unable to save screenshot: %s in path: %s, for %s.",
                    name,
                    path,
                    this
            ));
        }
    }

    /**
     * Selects the current iframe, using the currently registered
     * iframe selector.
     *
     * @param iframeName
     * @return
     */
    public String selectIFrame(String iframeName) {
        if (!currentIFrame.equals(iframeName)) {
            currentIFrame = iFrameSelector.selectIFrame(this, iframeName);
        }

        if (currentIFrame == null) {
            currentIFrame = iframeName;
        }

        return currentIFrame;
    }

    private void loadSupportScripts() {
        new Ie8LoadSupportScripts(() -> {
            if (this.js("return !window.__GERMANIUM_EXTENSIONS_LOADED")) {
                for (String script: this.supportScripts) {
                    loadScript(script);
                }

                loadScript("germanium-extensions-loaded.js");
            }
        }).execute();
    }

    public List<String> getSupportScripts() {
        return supportScripts;
    }

    /**
     * Load a script from the classpath.
     * @param name
     */
    public <T> T loadScript(String name) {
        String absoluteName = String.format("/%s/%s",
                this.getClass().getPackage().getName().replaceAll("\\.", "/"),
                name);

        InputStream inputStream = GermaniumDriver.class.getResourceAsStream(name);
        if (inputStream == null) {
            throw new IllegalArgumentException(String.format(
                    "Unable to load script with name: %s. Script " +
                            "was not found in the classpath (%s).",
                    name,
                    absoluteName
            ));
        }

        String code;

        try {
            Scanner s = new Scanner(inputStream).useDelimiter("\\A");
            code = s.hasNext() ? s.next() : "";
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                // ignoring on purpose.
            }
        }

        return this.js(code);
    }

    public <T> T js(String code, Object ... arguments) {
        try {
            String wrapperCode = ScriptLoader.getScript("/germanium/js_wrapper_script.js");

            Object result = AlertChecker.allowAlert(this, () -> {
                String wrappedCode = String.format(wrapperCode, code);
                return executeScript(wrappedCode, arguments);
            });

            if (result instanceof WebElement ||
                    result instanceof List) {
                return (T) result;
            }

            if (!(result instanceof Map)) {
                return (T) result;
            }

            Map resultMap = (Map) result;

            if ("SUCCESS".equals(resultMap.get("status"))) {
                return (T) resultMap.get("data");
            } else {
                throw new JavaScriptException(
                        resultMap.get("name"),
                        resultMap.get("message"));
            }
        } catch (Exception e) {
            log.error("Failure executing script: " + e.getMessage(), e);
            throw e;
        }
    }

    public IFrameSelector getIFrameSelector() {
        return iFrameSelector;
    }

    public void setIFrameSelector(IFrameSelector iFrameSelector) {
        this.iFrameSelector = iFrameSelector;
    }

    @Override
    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return webDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return webDriver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return webDriver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return webDriver.getPageSource();
    }

    @Override
    public void close() {
        webDriver.close();
    }

    @Override
    public void quit() {
        webDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return webDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return webDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return webDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return webDriver.navigate();
    }

    @Override
    public Options manage() {
        return webDriver.manage();
    }

    @Override
    public Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor)webDriver).executeScript(script, args);
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        return ((JavascriptExecutor)webDriver).executeAsyncScript(script, args);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return ((TakesScreenshot)webDriver).getScreenshotAs(target);
    }

    public <T> Locator<T> S(Object selector) {
        return this.S(selector, CreateLocator.DETECT);
    }

    public <T> Locator<T> S(Object selector, String strategy) {
        return CreateLocator
                .createLocator(this, selector, strategy);
    }

    /**
     * Returns a locator class for the given strategy.
     * @param strategy
     * @return
     */
    Class<? extends Locator> getLocatorClass(String strategy) {
        Class<? extends Locator> result = locatorMap.get(strategy);

        if (result == null) {
            throw new IllegalArgumentException(String.format(
                    "Unable to find a registered locator with name '%s' " +
                    "in the Germanium driver. Registered drivers are: %s.",
                    strategy,
                    locatorMap.keySet().stream()
                            .map(it -> "'" + it + "'")
                            .collect(Collectors.joining(", "))
                    ));
        }

        return result;
    }

    public Map<String, Class<? extends Locator>> getLocatorMap() {
        return locatorMap;
    }

    public String getCurrentIFrame() {
        return currentIFrame;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    @Override
    public Keyboard getKeyboard() {
        return ((HasInputDevices)webDriver).getKeyboard();
    }

    @Override
    public Mouse getMouse() {
        return ((HasInputDevices)webDriver).getMouse();
    }
}
