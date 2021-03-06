package com.germaniumhq.germanium.locators;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.impl.FilterNotDisplayed;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class DeferredLocator implements Locator<WebElement> {
    protected GermaniumDriver germanium;
    protected WebElement rootElement;

    public DeferredLocator(GermaniumDriver germanium, WebElement rootElement) {
        this.germanium = germanium;
        this.rootElement = rootElement;
    }

    protected abstract WebElement findElement();
    protected abstract List<WebElement> findElementList();

    @Override
    public WebElement element() {
        return element(Visibility.ONLY_VISIBLE);
    }

    @Override
    public WebElement element(Visibility visibility) {
        if (visibility == Visibility.ONLY_VISIBLE) {
            List<WebElement> elements = elementList(Visibility.ONLY_VISIBLE);

            if (elements == null || elements.size() == 0) {
                return null;
            }

            return elements.get(0);
        }

        return findElement();
    }

    @Override
    public List<WebElement> elementList() {
        return elementList(Visibility.ONLY_VISIBLE);
    }

    @Override
    public List<WebElement> elementList(Visibility visibility) {
        List<WebElement> elements = findElementList();

        return FilterNotDisplayed.filterNotDisplayed(
                elements,
                visibility,
                EmptyStrategy.EMPTY_COLLECTION);
    }

    @Override
    public WebElement elementList(int index) {
        if (index < 0) {
            throw new IllegalArgumentException(String.format(
                    "The index inside a list must be a positive number, not %d.", index));
        }

        List<WebElement> webElements = elementList();

        if (index >= webElements.size()) {
            throw new IllegalArgumentException(String.format(
                    "The element with index %d was requested, but only %d elements " +
                    "were available in the result.",
                    index,
                    webElements.size()
            ));
        }

        return webElements.get(index);
    }

    @Override
    public boolean exists() {
        return exists(Visibility.ONLY_VISIBLE);
    }

    @Override
    public boolean exists(Visibility visibility) {
        return this.element(visibility) != null;
    }

    @Override
    public boolean notExists() {
        return notExists(Visibility.ONLY_VISIBLE);
    }

    @Override
    public boolean notExists(Visibility visibility) {
        return this.element(visibility) == null;
    }

    @Override
    public String text() {
        return text(Visibility.ONLY_VISIBLE);
    }

    @Override
    public String text(Visibility visibility) {
        return this.element(visibility).getText();
    }

    public DeferredLocator setRootElement(WebElement rootElement) {
        this.rootElement = rootElement;

        return this;
    }

    @Override
    public WebElement get() {
        return element();
    }
}
