package com.germaniumhq.germanium.all.operations.actions;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.all.GermaniumApi;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GetValue {
    public static String getValue(Object selector) {
        WebElement element = GermaniumApi.getGermanium().<WebElement>S(selector).element();

        return GermaniumApi.js("return arguments[0].value;", element);
    }

    public static List<String> getValueAll(Object selector) {
        GermaniumDriver germanium = GermaniumApi.getGermanium();
        WebElement element = germanium.<WebElement>S(selector).element();

        if ("select".equals(element.getTagName())) {
            String multi = element.getAttribute("multiple");
            if (multi != null && !"false".equals(multi)) {
                return new Select(element)
                        .getAllSelectedOptions()
                        .stream()
                        .map(x -> x.getAttribute("value"))
                        .collect(Collectors.toList());
            }
        }

        String value = germanium.js("return arguments[0].value;", element);

        return Collections.singletonList(value);
    }
}
