package com.germaniumhq.germanium.all;

import com.germaniumhq.germanium.locators.Locator;
import com.germaniumhq.germanium.selectors.AbstractSelector;
import com.germaniumhq.germanium.selectors.Alert;
import org.openqa.selenium.WebElement;

public class GermaniumActions {
    public static void click(String selector) {
        click(GermaniumApi.<WebElement>S(selector).element());
    }

    public static void click(Locator<WebElement> locator) {
        click(locator.element());
    }

    public static void click(WebElement element) {
        element.click();
    }

    public static void typeKeys(String keys) {
    }

    public static void typeKeys(String keys, Alert alert) {
    }

    public static void typeKeys(String keys, Locator<WebElement> locator) {

    }
}
