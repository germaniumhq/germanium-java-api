package com.germaniumhq.germanium.all.operations;

import com.germaniumhq.germanium.all.GermaniumApi;
import org.openqa.selenium.WebElement;

public class ParentNode {
    public static WebElement parentNode(Object selector) {
        WebElement element = GermaniumApi.getGermanium().<WebElement>S(selector).element();
        return GermaniumApi.js("return arguments[0].parentNode;", element);
    }
}
