package com.germaniumhq.germanium.all.operations.wdbuilder;

import com.germaniumhq.EnsureDriver;
import com.germaniumhq.germanium.all.operations.OpenBrowser;
import com.germaniumhq.germanium.wa.FirefoxOpenBrowserWithMarionette;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static com.germaniumhq.germanium.all.operations.OpenBrowser.throwUnknownBrowser;

/**
 * Create the webdriver service locally.
 */
public class LocalWebDriverBuilder {
    private volatile static GeckoDriverService geckoDriverService;
    private volatile static ChromeDriverService chromeDriverService;
    private volatile static InternetExplorerDriverService ieDriverService;
    private volatile static EdgeDriverService edgeDriverService;


    public static WebDriver webDriver(String browser) {
        if ("firefox".equalsIgnoreCase(browser) || "ff".equalsIgnoreCase(browser)) {
            return openLocalFirefox();
        } else if ("chrome".equalsIgnoreCase(browser)) {
            return openLocalChrome();
        } else if ("ie".equalsIgnoreCase(browser)) {
            return openLocalIe();
        } else if ("edge".equalsIgnoreCase(browser)) {
            return openLocalEdge();
        } else {
            return throwUnknownBrowser(browser);
        }
    }

    private static WebDriver openLocalIe() {
        String driverPath = EnsureDriver.ensureDriver("ie");
        ensureIeDriverService(driverPath);

        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability("requireWindowFocus", true);

        return new InternetExplorerDriver(ieDriverService, capabilities);
    }

    private static WebDriver openLocalEdge() {
        String driverPath = EnsureDriver.ensureDriver("edge");
        ensureEdgeDriverService(driverPath);

        return new EdgeDriver(edgeDriverService);
    }

    private static WebDriver openLocalChrome() {
        String driverPath = EnsureDriver.ensureDriver("chrome");
        ensureChromeDriverService(driverPath);

        return new ChromeDriver(chromeDriverService);
    }

    private static WebDriver openLocalFirefox() {
        return new FirefoxOpenBrowserWithMarionette(() -> {
            DesiredCapabilities firefoxCapabilites = DesiredCapabilities.firefox();
            firefoxCapabilites.setCapability("unexpectedAlertBehaviour", "ignore");
            firefoxCapabilites.setCapability("marionette", false);

            return new FirefoxDriver(
                    firefoxCapabilites,
                    null);
        }).execute();
    }

    private static ChromeDriverService ensureChromeDriverService(String driverPath) {
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

    public static GeckoDriverService ensureGeckoDriverService(String driverPath) {
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

    private static InternetExplorerDriverService ensureIeDriverService(String driverPath) {
        if (ieDriverService != null) {
            return ieDriverService;
        }

        synchronized (OpenBrowser.class) {
            if (ieDriverService != null) {
                return ieDriverService;
            }

            ieDriverService = new InternetExplorerDriverService.Builder()
                    .usingDriverExecutable(new File(driverPath))
                    .usingAnyFreePort()
                    .build();
        }

        return ieDriverService;
    }

    private static EdgeDriverService ensureEdgeDriverService(String driverPath) {
        if (edgeDriverService != null) {
            return edgeDriverService;
        }

        synchronized (OpenBrowser.class) {
            if (edgeDriverService != null) {
                return edgeDriverService;
            }

            edgeDriverService = new EdgeDriverService.Builder()
                    .usingDriverExecutable(new File(driverPath))
                    .usingAnyFreePort()
                    .build();
        }

        return edgeDriverService;
    }


}
