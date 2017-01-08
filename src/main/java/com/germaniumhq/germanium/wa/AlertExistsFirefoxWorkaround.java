package com.germaniumhq.germanium.wa;

import com.germaniumhq.germanium.all.GermaniumApi;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;

import java.util.function.Supplier;

public class AlertExistsFirefoxWorkaround extends SupplierWorkaround<Boolean> {
    public AlertExistsFirefoxWorkaround(Supplier<Boolean> defaultCode) {
        super(defaultCode);
    }

    @Override
    protected Boolean executeWorkAround() {
        try {
            ((JavascriptExecutor)GermaniumApi.getWebDriver())
                    .executeScript("1 == 1");
            return false;
        } catch (Exception e) {
            return true;
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
