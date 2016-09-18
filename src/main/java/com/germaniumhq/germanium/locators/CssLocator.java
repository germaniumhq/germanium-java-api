package com.germaniumhq.germanium.locators;

import com.germaniumhq.germanium.GermaniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;

public class CssLocator extends DeferredLocator {
    private final String selector;

    public CssLocator(GermaniumDriver germanium, String stringSelector) {
        super(germanium, null);

        this.selector = stringSelector;
    }

    @Override
    protected WebElement findElement() {
        try {
            if (rootElement != null) {
                return rootElement.findElement(By.cssSelector(selector));
            }

            return germanium.findElement(By.cssSelector(selector));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    protected List<WebElement> findElementList() {
        try {
            List<WebElement> result;

            if (rootElement != null) {
                result = rootElement.findElements(By.cssSelector(selector));
            } else {
                result = germanium.findElements(By.cssSelector(selector));
            }

            if (result == null) {
                return Collections.emptyList();
            }

            return result;
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}