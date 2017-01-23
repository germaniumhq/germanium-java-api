package com.germaniumhq.germanium.all.operations.actions;

import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.impl.FilterNotDisplayed;
import com.germaniumhq.germanium.locators.EmptyStrategy;
import com.germaniumhq.germanium.locators.Locator;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Highlight {
    public static final float DEFAULT_SHOW_HIGHLIGHT_SECONDS = 2.0f;
    public static final float DEFAULT_BLINK_DURATION_SECONDS = 0.2f;

    public static enum Console {
        LOG,
        NONE
    }

    public static void highlight(Object selector, Console console, float showSeconds, float blinkDuration) {
        WebElement element;
        boolean isElementVisible;
        List<WebElement> items;
        List<WebElement> elements;

        if (selector instanceof WebElement) {
            element = (WebElement) selector;
            isElementVisible = FilterNotDisplayed.isWebElementDisplayed(element);
        } else {
            items = GermaniumApi.getGermanium().<WebElement>S(selector).elementList(Locator.Visibility.ALL_ELEMENTS);
            isElementVisible = true;
            elements = FilterNotDisplayed.filterNotDisplayed(items,
                    Locator.Visibility.ONLY_VISIBLE,
                    EmptyStrategy.EMPTY_COLLECTION);

            if (elements.isEmpty()) {
                elements = items;
                isElementVisible = false;
            }

            if (!elements.isEmpty()) {
                element = elements.get(0);
            } else {
                element = null;
            }
        }

        if (element == null) {
            String message = String.format("GERMANIUM: Unable to find element with selector `%s`.", selector);

            if (console == Console.LOG) {
                GermaniumApi.js("console.error(arguments[0]);",
                                message);
            } else {
                GermaniumApi.js("" +
                        "var message = arguments[0];" +
                        "setTimeout(function() {" +
                        "    alert(message);" +
                        "});", message);
            }

            return; // ==> not element
        }

        if (!isElementVisible) {
            String message = String.format("GERMANIUM: Element with selector `%s` was found, but is not visible.", selector);

            if (console == Console.LOG) {
                GermaniumApi.js("console.error(arguments[0], arguments[1]);", message, element);
            } else {
                GermaniumApi.js("" +
                    "var message = arguments[0];" +
                    "setTimeout(function() {" +
                    "    alert(message);" +
                    "});", message);
            }

            return; // ==> not isElementVisible
        }

        if (console == Console.LOG) {
            String message = String.format("GERMANIUM: Element with selector `%s` was found.", selector);
            GermaniumApi.js("console.log(arguments[0], arguments[1]);",
                    message,
                    element);
        }

        String code = String.format("" +
                "if (window._germaniumCurrentHighlight) {\n" +
                "    return;\n" +
                "}\n" +
                "\n" +
                "window._germaniumCurrentHighlight = arguments[0];\n" +
                "\n" +
                "var element = arguments[0];\n" +
                "var index = %d;\n" +
                "var originalOutline = element.style.outline;\n" +
                "\n" +
                "element.style.outline = '#00ff00 2px solid';\n" +
                "\n" +
                "var interval = setInterval(function() {\n" +
                "    index--;\n" +
                "\n" +
                "    if (index %% 2 == 1) {\n" +
                "        element.style.outline = '#00ff00 2px solid';\n" +
                "    } else {\n" +
                "        element.style.outline = originalOutline;\n" +
                "    }\n" +
                "\n" +
                "    if (index == 0) {\n" +
                "        delete window._germaniumCurrentHighlight;\n" +
                "        clearInterval(interval);\n" +
                "    }\n" +
                "}, %d);\n",  (int)(showSeconds / blinkDuration), (int) blinkDuration * 1000);

        GermaniumApi.js(code, element);
    }
}
