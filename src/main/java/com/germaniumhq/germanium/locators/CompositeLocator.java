package com.germaniumhq.germanium.locators;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CompositeLocator extends DeferredLocator {
    private final List<DeferredLocator> locatorList;

    public CompositeLocator(List<DeferredLocator> locatorList) {
        super(null, null);
        this.locatorList = locatorList;
    }

    @Override
    public DeferredLocator setRootElement(WebElement rootElement) {
        this.locatorList.forEach(it -> it.setRootElement(rootElement));

        return this;
    }

    @Override
    protected WebElement findElement() {
        for (DeferredLocator locator: locatorList) {
            WebElement result = locator.findElement();

            if (result != null) {
                return result;
            }
        }

        return null;
    }

    @Override
    protected List<WebElement> findElementList() {
        List<WebElement> result = new ArrayList<>();

        for (DeferredLocator locator: locatorList) {
            result.addAll(locator.findElementList());
        }

        return result;
    }
}
