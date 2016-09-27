package com.germaniumhq.germanium.locators;

import com.germaniumhq.germanium.GermaniumDriver;
import org.openqa.selenium.WebElement;

public abstract class FilterLocator extends DeferredLocator {
    protected final DeferredLocator parentLocator;

    public FilterLocator(GermaniumDriver germanium,
                         WebElement rootElement,
                         DeferredLocator parentLocator) {
        super(germanium, rootElement);

        this.parentLocator = parentLocator;
    }

    @Override
    public DeferredLocator setRootElement(WebElement rootElement) {
        this.parentLocator.setRootElement(rootElement);

        return this;
    }
}
