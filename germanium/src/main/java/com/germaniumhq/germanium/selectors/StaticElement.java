package com.germaniumhq.germanium.selectors;

import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.List;

public class StaticElement extends AbstractSelector {
    private final WebElement[] usedElements;

    public StaticElement(List<WebElement> usedElements) {
        this(usedElements.toArray(new WebElement[usedElements.size()]));
    }

    public StaticElement(WebElement... usedElements) {
        if (usedElements == null || usedElements.length == 0) {
            throw new IllegalArgumentException("You need to pass a list of selectors that the " +
                                               "AnyOfSelector will try to use when matching");
        }

        this.usedElements = usedElements;
    }

    @Override
    public Collection<String> getSelectors() {
        throw new UnsupportedOperationException("You shouldn't call getSelectors directly.");
    }

    public WebElement[] getUsedElements() {
        return usedElements;
    }
}
