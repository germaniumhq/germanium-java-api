package com.germaniumhq.germanium.locators;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.selectors.Window;

import java.util.List;

public class WindowLocator implements Locator<WebDriverWindow> {
    public WindowLocator(GermaniumDriver germanium,
                         Window selector) {

    }

    @Override
    public WebDriverWindow element() {
        return null;
    }

    @Override
    public List<WebDriverWindow> elementList() {
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
