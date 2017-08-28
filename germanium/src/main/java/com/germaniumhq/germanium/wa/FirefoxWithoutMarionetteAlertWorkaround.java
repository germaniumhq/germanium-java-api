package com.germaniumhq.germanium.wa;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.all.GermaniumApi;
import org.openqa.selenium.Alert;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;

import java.util.function.Supplier;

public class FirefoxWithoutMarionetteAlertWorkaround extends SupplierWorkaround<Alert> {
    private final GermaniumDriver germaniumDriver;

    public FirefoxWithoutMarionetteAlertWorkaround(GermaniumDriver germaniumDriver, Supplier<Alert> defaultCode) {
        super(defaultCode);
        this.germaniumDriver = germaniumDriver;
    }

    @Override
    protected Alert executeWorkAround() {
        try {
            ((JavascriptExecutor)GermaniumApi.getWebDriver())
                    .executeScript("1 == 1");
            return germaniumDriver.getLastAlert();
        } catch (Exception e) {
            return germaniumDriver.switchTo().alert();
        }
    }

    @Override
    protected boolean isApplicable() {
        if (!(GermaniumApi.getWebDriver() instanceof HasCapabilities)) {
            return false;
        }

        HasCapabilities capabilities = ((HasCapabilities)GermaniumApi.getWebDriver());

        return "firefox".equals(capabilities.getCapabilities().getBrowserName().toLowerCase()) &&
                !capabilities.getCapabilities().is("marionette");
    }
}
