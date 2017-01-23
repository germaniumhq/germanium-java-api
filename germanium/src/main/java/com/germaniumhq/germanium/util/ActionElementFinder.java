package com.germaniumhq.germanium.util;

import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.impl.FilterNotDisplayed;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Finds a single element for an action that requires a visual
 * element. In case no elements are found, a decent error will
 * be reported.
 */
public class ActionElementFinder {
    public static WebElement singleElement(Object selector) {
        WebElement element = null;

        if (selector != null) {
            List<WebElement> items= GermaniumApi.getGermanium()
                    .<WebElement>S(selector).elementList();
            element = FilterNotDisplayed.filterOneForAction(items);
        }

        return element;
    }
}
