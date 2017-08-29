package com.germaniumhq.germanium.locators;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.impl.ScriptLoader;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StaticElementLocator extends DeferredLocator {
    private List<WebElement> elements;

    public StaticElementLocator(GermaniumDriver germanium, WebElement element) {
        super(germanium, null);

        if (element == null) {
            elements = Collections.emptyList();
            return;
        }

        elements = Collections.singletonList(element);
    }

    public StaticElementLocator(GermaniumDriver germanium, Iterable<WebElement> iterableSelector) {
        super(germanium, null);
        elements = new ArrayList<>();
        iterableSelector.forEach(elements::add);
    }

    public StaticElementLocator(GermaniumDriver germanium, WebElement[] usedElements) {
        super(germanium, null);

        elements = new ArrayList<>();
        Collections.addAll(elements, usedElements);
    }

    @Override
    protected WebElement findElement() {
        if (elements.isEmpty()) {
            return null;
        }

        return elements.get(0);
    }

    @Override
    protected List<WebElement> findElementList() {
        if (this.rootElement == null) {
            return elements;
        }

        List<Object> jsArguments = new ArrayList<>();

        String code = ScriptLoader.getScript("/germanium/locators/inside-filter.min.js");
        jsArguments.add(0); // ignore without children
        jsArguments.add(1);
        jsArguments.add(this.rootElement);
        jsArguments.add(0); // no containing_elements
        jsArguments.add(0); // no outside_elements
        jsArguments.add(0); // no containing_all selectors
        jsArguments.add(0); // no containing_all element/groupId pairs
        jsArguments.add(elements.size());
        jsArguments.addAll(this.elements);

        List<WebElement> resultElements = germanium.js(code, jsArguments.toArray());

        return resultElements;
    }
}
