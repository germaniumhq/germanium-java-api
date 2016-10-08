package com.germaniumhq.germanium.all;

import com.germaniumhq.germanium.locators.Locator;
import com.germaniumhq.germanium.selectors.AbstractSelector;
import org.openqa.selenium.WebElement;

public class GermaniumActions {
    public void click(String selector) {
        click(GermaniumApi.<WebElement>S(selector).element());
    }

    public void click(AbstractSelector selector) {
        click(selector.element());
    }

    public void click(Locator<WebElement> locator) {
        click(locator.element());
    }

    public void click(WebElement element) {
        element.click();
    }

    public void typeKeys(String keys) {
    }
}
