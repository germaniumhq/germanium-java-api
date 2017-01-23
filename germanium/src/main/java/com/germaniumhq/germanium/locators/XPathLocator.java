package com.germaniumhq.germanium.locators;

import com.germaniumhq.germanium.GermaniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;

public class XPathLocator extends DeferredLocator {
    private final String selector;

    public XPathLocator(GermaniumDriver germanium, Object stringSelector) {
        super(germanium, /* rootElement */ null);
        this.selector = String.valueOf(stringSelector);
    }

    @Override
    protected WebElement findElement() {
        try {
            if (rootElement != null) {
                return rootElement.findElement(By.xpath(selector));
            }
            return germanium.findElement(By.xpath(selector));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    protected List<WebElement> findElementList() {
        try {
            List<WebElement> result;

            if (rootElement != null) {
                result = rootElement.findElements(By.xpath(selector));
            } else {
                result = germanium.findElements(By.xpath(selector));
            }

            if (result == null) {
                return Collections.emptyList();
            }

            return result;
        } catch (NoSuchElementException e) {
            return Collections.emptyList();
        }
    }
}
