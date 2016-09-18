package com.germaniumhq.germanium.locators;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StaticElementLocator extends DeferredLocator {
    private List<WebElement> elements;

    public StaticElementLocator(WebElement element) {
        super(null, null);

        if (element == null) {
            elements = Collections.emptyList();
            return;
        }

        elements = Collections.singletonList(element);
    }

    public StaticElementLocator(Iterable<WebElement> iterableSelector) {
        super(null, null);
        elements = new ArrayList<>();
        iterableSelector.forEach(elements::add);
    }

    @Override
    protected WebElement findElement() {
        if (elements.isEmpty()) {
            return null;
        }

        return elements.get(0);
    }

    @Override
    protected List<WebElement> findElementList() {
        return Collections.unmodifiableList(elements);
    }
}
