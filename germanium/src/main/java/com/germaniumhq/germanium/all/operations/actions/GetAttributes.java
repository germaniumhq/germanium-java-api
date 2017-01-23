package com.germaniumhq.germanium.all.operations.actions;

import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.impl.ScriptLoader;
import com.germaniumhq.germanium.locators.Locator;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class GetAttributes {
    public static Map<String, String> getAttributes(Object selector, Locator.Visibility visibility) {
        WebElement element;

        if (selector instanceof WebElement) {
            element = (WebElement) selector;
        } else {
            element = GermaniumApi.getGermanium().<WebElement>S(selector).element(visibility);
        }

        if (element == null) {
            throw new IllegalArgumentException(String.format(
                    "Unable to find '%s' to get_attributes.",
                    selector));
        }

        String code = ScriptLoader.getScript("/germanium/util/get-attributes.min.js");

        return GermaniumApi.js(code, element);
    }
}
