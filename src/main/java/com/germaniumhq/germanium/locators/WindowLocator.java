package com.germaniumhq.germanium.locators;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.selectors.Window;

import java.util.List;

public class WindowLocator implements Locator<WebDriverWindow> {
    public WindowLocator(GermaniumDriver germanium,
                         Window selector) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public WebDriverWindow element() {
        return null;
    }

    @Override
    public WebDriverWindow element(Visibility visibility) {
        return null;
    }

    @Override
    public List<WebDriverWindow> elementList() {
        return null;
    }

    @Override
    public List<WebDriverWindow> elementList(Visibility visibility) {
        return null;
    }

    @Override
    public WebDriverWindow elementList(int index) {
        return null;
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public boolean exists(Visibility visibility) {
        return false;
    }

    @Override
    public boolean notExists() {
        return false;
    }

    @Override
    public boolean notExists(Visibility visibility) {
        return false;
    }

    @Override
    public String text() {
        return null;
    }

    @Override
    public String text(Visibility visibility) {
        return null;
    }

    @Override
    public List<WebDriverWindow> get() {
        return null;
    }
}
