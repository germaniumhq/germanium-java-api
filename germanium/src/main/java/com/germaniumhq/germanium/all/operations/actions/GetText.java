package com.germaniumhq.germanium.all.operations.actions;

import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.util.ActionElementFinder;
import org.openqa.selenium.WebElement;

public class GetText {
    public static String getText(Object selector) {
        WebElement element;

        if (selector == null) {
            throw new IllegalArgumentException("The passed selector into getText() was null. " +
                    "In case you are using getText with waited: getText(waited(Element(..))) then " +
                    "it means the waited() call has failed.");
        }

        if (selector instanceof WebElement) {
            element = (WebElement) selector;
        } else {
            element = ActionElementFinder.singleElement(selector);
        }

        String result = GermaniumApi.js(
                "return arguments[0].textContent || arguments[0].innerText || '';",
                element);

        return result.replaceAll("\r\n", "\n");
    }
}
