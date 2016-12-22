package com.germaniumhq.germanium.all.operations;

import com.germaniumhq.EnsureDriver;
import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.IFrameSelector;
import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.iframe.DefaultIFrameSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenBrowser {
    private String browser = "firefox";
    private WebDriver webDriver;
    private IFrameSelector iFrameSelector = new DefaultIFrameSelector();
    private String screenshotFolder = "screenshots";
    private float timeout = 60f;

    private volatile static GeckoDriverService geckoDriverService;
    private volatile static ChromeDriverService chromeDriverService;

    private static final Pattern REMOTE_ADDRESS = Pattern.compile("^(\\w+?):(.*?)$");

    public OpenBrowser browser(String browser) {
        this.browser = browser;
        return this;
    }

    public OpenBrowser webDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
        return this;
    }

    public OpenBrowser iFrameSelector(IFrameSelector iFrameSelector) {
        this.iFrameSelector = iFrameSelector;
        return this;
    }

    public OpenBrowser iFrameSelector(BiConsumer<GermaniumDriver, String> iFrameSelector) {
        this.iFrameSelector = (germanium, iFrameName) -> {
            iFrameSelector.accept(germanium, iFrameName);
            return iFrameName;
        };

        return this;
    }

    public OpenBrowser screenshotFolder(String screenshotFolder) {
        this.screenshotFolder = screenshotFolder;
        return this;
    }

    public OpenBrowser timeout(float timeoutInSeconds) {
        this.timeout = timeoutInSeconds;
        return this;
    }

    /**
     * Open the given browser.
     *
     * @return
     */
    public GermaniumDriver get() {
        if (GermaniumApi.getGermanium() != null) {
            throw new IllegalStateException("A browser already runs. Close it first with GermaniumApi.closeBrowser().");
        }

        Matcher remoteMatch = REMOTE_ADDRESS.matcher(browser);

        WebDriver webDriver = null;

        if (this.webDriver != null) {
            webDriver = this.webDriver;
        } else if (remoteMatch.matches()) {
            String remoteBrowser = remoteMatch.group(1);
            DesiredCapabilities remoteCapabilites;

            if ("firefox".equalsIgnoreCase(remoteBrowser) || "ff".equalsIgnoreCase(remoteBrowser)) {
                remoteCapabilites = DesiredCapabilities.firefox();
            } else if ("chrome".equalsIgnoreCase(remoteBrowser)) {
                remoteCapabilites = DesiredCapabilities.chrome();
            } else if ("ie".equalsIgnoreCase(remoteBrowser)) {
                remoteCapabilites = DesiredCapabilities.internetExplorer();
            } else if ("edge".equalsIgnoreCase(remoteBrowser)) {
                remoteCapabilites = DesiredCapabilities.edge();
            } else {
                return throwUnknownBrowser();
            }

            String remoteUrlString = remoteMatch.group(2);
            webDriver = new RemoteWebDriver(new HttpCommandExecutor(getUrl(remoteUrlString)), remoteCapabilites);
        } else if ("firefox".equalsIgnoreCase(browser) || "ff".equalsIgnoreCase(browser)) {
            webDriver = openLocalFirefox();
        } else if ("chrome".equalsIgnoreCase(browser)) {
            webDriver = openLocalChrome();
        } else if ("ie".equalsIgnoreCase(browser)) {
            webDriver = openLocalIe();
        } else if ("edge".equalsIgnoreCase(browser)) {
            webDriver = openLocalEdge();
        } else {
            return throwUnknownBrowser();
        }

        GermaniumDriver germanium = new GermaniumDriver(webDriver, iFrameSelector, screenshotFolder);

        GermaniumApi.setGermanium(germanium);

        return germanium;
    }

    private WebDriver openLocalIe() {
        throw new IllegalStateException("Not implemented");
    }

    private WebDriver openLocalEdge() {
        throw new IllegalStateException("Not implemented");
    }

    private WebDriver openLocalChrome() {
        String driverPath = EnsureDriver.ensureDriver("chrome");
        ensureChromeDriverService(driverPath);

        return new ChromeDriver(chromeDriverService);
    }

    private WebDriver openLocalFirefox() {
        String driverPath = EnsureDriver.ensureDriver("firefox");
        ensureGeckoDriverService(driverPath);

        return new FirefoxDriver(geckoDriverService,
                DesiredCapabilities.firefox(),
                null);
    }

    private ChromeDriverService ensureChromeDriverService(String driverPath) {
        if (chromeDriverService != null) {
            return chromeDriverService;
        }

        synchronized (OpenBrowser.class) {
            if (chromeDriverService != null) {
                return chromeDriverService;
            }

            chromeDriverService = new ChromeDriverService.Builder()
                    .usingAnyFreePort()
                    .usingDriverExecutable(new File(driverPath))
                    .build();
        }

        return chromeDriverService;
    }

    private static GeckoDriverService ensureGeckoDriverService(String driverPath) {
        if (geckoDriverService != null) {
            return geckoDriverService;
        }

        synchronized (OpenBrowser.class) {
            if (geckoDriverService != null) {
                return geckoDriverService;
            }

            geckoDriverService = new GeckoDriverService.Builder()
                    .usingDriverExecutable(new File(driverPath))
                    .usingAnyFreePort()
                    .build();
        }

        return geckoDriverService;
    }

    private GermaniumDriver throwUnknownBrowser() {
        throw new IllegalArgumentException(String.format(
                "Unknown browser: %s, only firefox, " +
                    "chrome and ie are supported." ,
                browser
        ));
    }

    private URL getUrl(String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Unable to parse URL for browser: " + urlString, e);
        }
    }
}
