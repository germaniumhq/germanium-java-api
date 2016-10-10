package com.germaniumhq.germanium.impl;

import com.germaniumhq.germanium.locators.EmptyStrategy;
import com.germaniumhq.germanium.locators.Locator;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FilterNotDisplayed {
    public static WebElement filterOneForAction(List<WebElement> elements) {
        List<WebElement> items = filterNotDisplayed(
                elements,
                Locator.Visibility.ONLY_VISIBLE,
                EmptyStrategy.THROW_WHEN_EMPTY);

        return items.get(0);
    }

    public static List<WebElement> filterNotDisplayed(List<WebElement> elements,
                                                Locator.Visibility visibility,
                                                EmptyStrategy throwStrategy) {
        if (elements == null || elements.size() == 0) {
            if (throwStrategy == EmptyStrategy.THROW_WHEN_EMPTY) {
                raiseNoItemsFountForAction();
            }

            return Collections.emptyList();
        }

        if (visibility == Locator.Visibility.ALL_ELEMENTS) {
            return elements;
        }

        List<WebElement> result = elements.stream()
                .filter(WebElement::isDisplayed)
                .collect(Collectors.toList());

        if (result.isEmpty() && throwStrategy == EmptyStrategy.THROW_WHEN_EMPTY) {
            raiseNoVisibleItemsFoundForAction(elements);
        }

        return result;
    }

    private static void raiseNoItemsFountForAction() {
        throw new IllegalStateException(
                "No items, visible or invisible, matched the selector given " +
                        "for the action.");
    }

    private static void raiseNoVisibleItemsFoundForAction(List<WebElement> elements) {
        throw new IllegalStateException(String.format(
                "While there were %d element(s) found, all of them were " +
                        "either invisible (display: none, visibility: false, or " +
                        "position off screen), or elements that are detached " +
                        "from the current DOM document. Detached elements might " +
                        "happen if the action is called while the page is " +
                        "reloading.", elements.size()
        ));
    }
}
