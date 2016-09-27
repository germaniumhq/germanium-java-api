package com.germaniumhq.germanium.locators;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.impl.ScriptLoader;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class InsideFilterLocator extends FilterLocator {
    private final List<Locator<WebElement>> insideFilters;
    private final List<Locator<WebElement>> containingFilters;
    private final List<Locator<WebElement>> containingAllFilters;
    private final boolean withoutChildrenElements;

    public InsideFilterLocator(GermaniumDriver germanium,
                               DeferredLocator locator,
                               List<Locator<WebElement>> insideFilters,
                               List<Locator<WebElement>> containingFilters,
                               List<Locator<WebElement>> containingAllFilters,
                               boolean withoutChildrenElements) {
        super(germanium, null, locator);

        this.insideFilters = insideFilters;
        this.containingFilters = containingFilters;
        this.containingAllFilters = containingAllFilters;
        this.withoutChildrenElements = withoutChildrenElements;
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
         * Since the inside/contains/without children works with the DOM
         * structure, it might be used to find invisible elements. So
         * we need to get the raw list of elements.
         */

        Set<WebElement> insideElements = new LinkedHashSet<>();

        for (Locator<WebElement> selector: insideFilters) {
            List<WebElement> elementList = selector.elementList(Visibility.ALL_ELEMENTS);

            // if we have an inside element that itself can't be found,
            // don't bother to search the elements further
            if (elementList.isEmpty()) {
                return Collections.emptyList();
            }

            insideElements.addAll(elementList);
        }

        if (insideElements.isEmpty()) {
            insideElements.add(null);
        }

        Set<WebElement> elements = new LinkedHashSet<>();
        for (WebElement insideElement : insideElements) {
            List<WebElement> insideFoundElements = parentLocator
                    .setRootElement(insideElement)
                    .findElementList();

            elements.addAll(insideFoundElements);
        }

        Set<WebElement> containingElements = new LinkedHashSet<>();
        for (Locator<WebElement> selector : containingFilters) {
            List<WebElement> elementList = selector.elementList(Visibility.ALL_ELEMENTS);

            // if we don't have any elements that we're supposed to
            // contain, it means the selector isn't matching, so don't
            // bother, trying to find further, otherwise we will match
            // a lot of false positives.

            if (elementList.isEmpty()) {
                return Collections.emptyList();
            }

            containingElements.addAll(elementList);
        }

        /*
        # `containing_all` needs to create groups for each selector
        # and then filter the resulting elements against the
        # groups.
        #
        # We will create a dictionary that holds all the elements,
        # linked with all the group indexes they belong to, as a
        # string CSV.
        #
        # The search will remove all the elements that don't contain
        # all the groups.
        */

        int groupIndex = -1;
        Map<WebElement, Set<String>> containingAllElements = new LinkedHashMap<>();

        for (Locator<WebElement> selector : this.containingAllFilters) {
            groupIndex++;
            List<WebElement> elementList = selector.elementList(Visibility.ALL_ELEMENTS);

            /*
            # if we have things we need to contain, but the selectors
            # don't return the elements, we don't bother so we don't
            # get false positives. eg A().contains(Text("missing")) will
            # match all A elements otherwise, the contains becomes bogus.
            */

            if (elementList == null || elementList.isEmpty()) {
                return Collections.emptyList();
            }

            for (WebElement containingAllElement : elementList) {
                String groupIndexString = Integer.toString(groupIndex);
                if (containingAllElements.containsKey(containingAllElement)) {
                    containingAllElements.get(containingAllElement).add(groupIndexString);
                    continue;
                }

                HashSet<String> items = new HashSet<>();
                items.add(groupIndexString);
                containingAllElements.put(containingAllElement, items);
            }
        }

        List<Object> jsArguments = new ArrayList<>();

        String code = ScriptLoader.getScript("inside-filter.min.js");
        jsArguments.add(this.withoutChildrenElements ? 1 : 0);
        jsArguments.add(0); // FIXME:remove
        jsArguments.add(containingElements.size());
        jsArguments.addAll(containingElements);

        jsArguments.add(containingAllFilters.size()); // groupCount
        jsArguments.add(containingAllElements.size());

        for (Map.Entry<WebElement, Set<String>> entry : containingAllElements.entrySet()) {
            jsArguments.add(entry.getKey());
            jsArguments.add(entry.getValue().stream().collect(Collectors.joining(",")));
        }

        jsArguments.add(elements.size());
        jsArguments.addAll(elements);

        List<WebElement> resultElements = germanium.js(code, jsArguments.toArray());

        return resultElements;
    }
}
