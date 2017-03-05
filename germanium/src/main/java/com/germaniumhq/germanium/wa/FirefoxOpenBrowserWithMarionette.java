package com.germaniumhq.germanium.wa;

import com.germaniumhq.EnsureDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.function.Supplier;

import static com.germaniumhq.germanium.all.operations.wdbuilder.LocalWebDriverBuilder.ensureGeckoDriverService;

/**
 * Instantiate firefox using the marionette driver,
 * if it's set in the environment variables.
 */
public class FirefoxOpenBrowserWithMarionette extends SupplierWorkaround<RemoteWebDriver> {
    public FirefoxOpenBrowserWithMarionette(Supplier<RemoteWebDriver> defaultCode) {
        super(defaultCode);
    }

    @Override
    protected RemoteWebDriver executeWorkAround() {
        String path = EnsureDriver.ensureDriver("firefox");
        GeckoDriverService geckoDriverService = ensureGeckoDriverService(path);

        DesiredCapabilities firefoxCapabilites = DesiredCapabilities.firefox();
        firefoxCapabilites.setCapability("unexpectedAlertBehaviour", "ignore");
        firefoxCapabilites.setCapability("marionette", true);

        return new FirefoxDriver(geckoDriverService,
                firefoxCapabilites,
                null);
    }

    @Override
    protected boolean isApplicable() {
        return System.getenv("GERMANIUM_FIREFOX_USE_MARIONETTE") != null;
    }
}
