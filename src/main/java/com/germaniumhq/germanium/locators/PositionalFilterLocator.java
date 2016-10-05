package com.germaniumhq.germanium.locators;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.impl.ScriptLoader;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PositionalFilterLocator extends FilterLocator {
    private final List<Locator<WebElement>> leftOfFilters;
    private final List<Locator<WebElement>> rightOfFilters;
    private final List<Locator<WebElement>> aboveFilters;
    private final List<Locator<WebElement>> belowFilters;

    public PositionalFilterLocator(GermaniumDriver germanium,
                                   DeferredLocator parentLocator,
                                   List<Locator<WebElement>> leftOfFilters,
                                   List<Locator<WebElement>> rightOfFilters,
                                   List<Locator<WebElement>> aboveFilters,
                                   List<Locator<WebElement>> belowFilters
                                   ) {
        super(germanium, null, parentLocator);

        this.leftOfFilters = leftOfFilters;
        this.rightOfFilters = rightOfFilters;
        this.aboveFilters = aboveFilters;
        this.belowFilters = belowFilters;
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
        /*
        # Since this filter sorts elements that would appear visually right of
        # or left of things, we don't need to get the raw element list using
        # `_find_element_list` but we can work with the filtered list.
        */

        List<WebElement> elements = parentLocator.elementList();

        Set<WebElement> leftOfElements = new HashSet<>();
        for (Locator<WebElement> selector : leftOfFilters) {
            leftOfElements.addAll(selector.elementList());
        }

        Set<WebElement> rightOfElements = new HashSet<>();
        for (Locator<WebElement> selector : rightOfFilters) {
            rightOfElements.addAll(selector.elementList());
        }

        Set<WebElement> aboveElements = new HashSet<>();
        for (Locator<WebElement> selector : aboveFilters) {
            aboveElements.addAll(selector.elementList());
        }

        Set<WebElement> belowElements = new HashSet<>();
        for (Locator<WebElement> selector : belowFilters) {
            belowElements.addAll(selector.elementList());
        }

        List<Object> jsArguments = new ArrayList<>();

        String code = ScriptLoader.getScript("/germanium/locators/positional-filter.min.js");

        jsArguments.add(aboveElements.size());
        jsArguments.addAll(aboveElements);
        jsArguments.add(rightOfElements.size());
        jsArguments.addAll(rightOfElements);
        jsArguments.add(belowElements.size());
        jsArguments.addAll(belowElements);
        jsArguments.add(leftOfElements.size());
        jsArguments.addAll(leftOfElements);

        jsArguments.add(elements.size());
        jsArguments.addAll(elements);

        List<WebElement> resultElements = germanium.js(code, jsArguments);

        return resultElements;
    }
}
