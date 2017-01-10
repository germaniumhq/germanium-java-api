package com.germaniumhq.germanium.wa;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.all.GermaniumApi;
import org.openqa.selenium.HasCapabilities;

/**
 * Since IE8 doesn't have getComputedStyle,
 * we need to shim it, and load it via
 * support scripts.
 */
public class Ie8LoadSupportScripts extends RunnableWorkaround {
    public Ie8LoadSupportScripts(Runnable defaultCode) {
        super(defaultCode);
    }

    @Override
    protected void executeWorkAround() {
        GermaniumDriver germanium = GermaniumApi.getGermanium();
        if (germanium.js("return !window.__GERMANIUM_EXTENSIONS_LOADED")) {
            germanium.loadScript("germanium-ie8-getComputedStyle.js");

            for (String script: germanium.getSupportScripts()) {
                germanium.loadScript(script);
            }

            germanium.loadScript("germanium-extensions-loaded.js");
        }
    }

    @Override
    protected boolean isApplicable() {
        if (!(GermaniumApi.getWebDriver() instanceof HasCapabilities)) {
            return false;
        }

        HasCapabilities capabilities = ((HasCapabilities)GermaniumApi.getWebDriver());

        return "internet explorer".equals(capabilities.getCapabilities().getBrowserName().toLowerCase()) &&
                "8".equals(capabilities.getCapabilities().getVersion());
    }
}
