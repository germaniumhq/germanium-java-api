package com.germaniumhq.germanium.locators;

import com.germaniumhq.germanium.GermaniumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PositionalFilterLocator implements Locator<WebElement> {
    public PositionalFilterLocator(GermaniumDriver germanium,
                                   DeferredLocator parentLocator,
                                   List<Locator<WebElement>> leftOfFilters,
                                   List<Locator<WebElement>> rightOfFilters,
                                   List<Locator<WebElement>> aboveFilters,
                                   List<Locator<WebElement>> belowFilters) {

    }

    @Override
    public WebElement element() {
        return null;
    }

    @Override
    public List<WebElement> elementList() {
        return null;
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public boolean notExists() {
        return false;
    }

    @Override
    public String text() {
        return null;
    }
}
