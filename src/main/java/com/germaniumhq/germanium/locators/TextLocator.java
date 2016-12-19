package com.germaniumhq.germanium.locators;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.impl.ScriptLoader;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;

public class TextLocator extends DeferredLocator {
    private final String text;
    private final boolean exactMatch;
    private final boolean trimText;

    public TextLocator(GermaniumDriver germanium,
                       String text,
                       boolean exactMatch,
                       boolean trimText) {
        super(germanium, null);

        this.text = text;
        this.exactMatch = exactMatch;
        this.trimText = trimText;
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
        String code = ScriptLoader.getScript("/germanium/locators/text.min.js");
        Object result = germanium.js(code, rootElement, text, exactMatch, trimText);

        if (result == null) {
            return Collections.emptyList();
        }

        if (!(result instanceof List)) {
            throw new IllegalStateException(String.format(
                    "Code `%s` is not returning a list of elements.",
                    code
            ));
        }

        return (List<WebElement>) result;
    }
}
