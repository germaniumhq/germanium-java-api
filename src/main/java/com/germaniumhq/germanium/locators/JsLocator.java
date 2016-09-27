package com.germaniumhq.germanium.locators;

import com.germaniumhq.germanium.GermaniumDriver;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;

public class JsLocator extends DeferredLocator {
    private final String code;

    public JsLocator(GermaniumDriver germanium, String code, WebElement rootElement) {
        super(germanium, rootElement);

        this.code = code;
    }

    @Override
    protected WebElement findElement() {
        List<WebElement> result = findElementList();

        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }

    @Override
    protected List<WebElement> findElementList() {
        Object result = germanium.js(code, rootElement);

        if (result == null) {
            return Collections.emptyList();
        }

        if (!(result instanceof List)) {
            throw new IllegalStateException(String.format(
                    "Code `%s` is not returning a list of elements.",
                    this.code
            ));
        }

        return (List<WebElement>) result;
    }
}
