package com.germaniumhq.germanium.selectors;

import org.openqa.selenium.WebElement;

import java.util.Collection;

public class AnyOfSelector extends AbstractSelector {
    private final Selector<WebElement>[] usedSelectors;

    public AnyOfSelector(Selector<WebElement>... usedSelectors) {
        if (usedSelectors == null || usedSelectors.length == 0) {
            throw new IllegalArgumentException("You need to pass a list of selectors that the " +
                                               "AnyOfSelector will try to use when matching");
        }

        this.usedSelectors = usedSelectors;
    }

    @Override
    public Collection<String> getSelectors() {
        throw new UnsupportedOperationException("You shouldn't call getSelectors directly.");
    }

    public Selector<WebElement>[] getUsedSelectors() {
        return usedSelectors;
    }
}
