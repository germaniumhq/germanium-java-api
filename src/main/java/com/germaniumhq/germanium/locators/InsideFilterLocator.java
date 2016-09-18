package com.germaniumhq.germanium.locators;

import com.germaniumhq.germanium.GermaniumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InsideFilterLocator implements Locator<WebElement> {
    public InsideFilterLocator(GermaniumDriver germanium, Locator<WebElement> locator, List<Locator<WebElement>> insideFilters, List<Locator<WebElement>> containingFilters, List<Locator<WebElement>> containingAllFilters, boolean withoutChildrenElements) {
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
