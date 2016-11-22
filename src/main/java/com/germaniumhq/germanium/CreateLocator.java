package com.germaniumhq.germanium;

import com.germaniumhq.germanium.locators.AlertLocator;
import com.germaniumhq.germanium.locators.CompositeLocator;
import com.germaniumhq.germanium.locators.CssLocator;
import com.germaniumhq.germanium.locators.DeferredLocator;
import com.germaniumhq.germanium.locators.InsideFilterLocator;
import com.germaniumhq.germanium.locators.Locator;
import com.germaniumhq.germanium.locators.PositionalFilterLocator;
import com.germaniumhq.germanium.locators.StaticElementLocator;
import com.germaniumhq.germanium.locators.WindowLocator;
import com.germaniumhq.germanium.locators.XPathLocator;
import com.germaniumhq.germanium.selectors.AbstractSelector;
import com.germaniumhq.germanium.selectors.Alert;
import com.germaniumhq.germanium.selectors.InsideFilterSelector;
import com.germaniumhq.germanium.selectors.PositionalFilterSelector;
import com.germaniumhq.germanium.selectors.Selector;
import com.germaniumhq.germanium.selectors.Window;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CreateLocator {
    public static final Pattern LOCATOR_SPECIFIER = Pattern.compile("((\\w[\\w\\d]*?):)(.*)",
            Pattern.MULTILINE | Pattern.DOTALL);

    public static final String DETECT = "detect";

    public static <T> Locator<T> createLocator(GermaniumDriver germanium,
                                               Object selector) {
        return (Locator<T>) CreateLocator.createLocator(germanium, selector, DETECT);
    }

    public static <T> Locator<T> createLocator(GermaniumDriver germanium,
                                 Object selector,
                                 String strategy) {
        if (selector == null) {
            throw new IllegalArgumentException(
                    "A `null` selector was passed to Germanium to create a " +
                    "locator out of it. Maybe an invalid function return " +
                    "is being used?");
        }

        if (!DETECT.equals(strategy)) {
            Class<? extends Locator> clazz = germanium.getLocatorClass(strategy);
            return instantiateLocator(clazz, germanium, selector);
        }

        if (selector instanceof Locator && !(selector instanceof Selector)) {
            if (!DETECT.equals(strategy)) {
                throw new IllegalArgumentException(String.format(
                        "The locator is already constructed, but a strategy is " +
                        "also defined: '%s'", strategy));
            }

            return (Locator) selector;
        }

        if (selector instanceof PositionalFilterSelector) {
            PositionalFilterSelector positionalFilterSelector =
                    (PositionalFilterSelector) selector;

            List<Locator<WebElement>> leftOfFilters = positionalFilterSelector.getLeftOfFilters()
                    .stream()
                    .map((x) -> CreateLocator.<WebElement>createLocator(germanium, x))
                    .collect(Collectors.toList());
            List<Locator<WebElement>> rightOfFilters = positionalFilterSelector.getRightOfFilters()
                    .stream()
                    .map((x) -> CreateLocator.<WebElement>createLocator(germanium, x))
                    .collect(Collectors.toList());
            List<Locator<WebElement>> aboveFilters = positionalFilterSelector.getAboveFilters()
                    .stream()
                    .map((x) -> CreateLocator.<WebElement>createLocator(germanium, x))
                    .collect(Collectors.toList());
            List<Locator<WebElement>> belowFilters = positionalFilterSelector.getBelowFilters()
                    .stream()
                    .map((x) -> CreateLocator.<WebElement>createLocator(germanium, x))
                    .collect(Collectors.toList());

            return (Locator<T>) new PositionalFilterLocator(
                    germanium,
                    (DeferredLocator) CreateLocator.<WebElement>createLocator(germanium, positionalFilterSelector.getParentSelector()),
                    leftOfFilters,
                    rightOfFilters,
                    aboveFilters,
                    belowFilters
            );
        }
        
        if (selector instanceof InsideFilterSelector) {
            InsideFilterSelector insideFilterSelector = (InsideFilterSelector) selector;

            List<Locator<WebElement>> insideFilters = insideFilterSelector.getInsideFilters()
                    .stream()
                    .map((x) -> CreateLocator.<WebElement>createLocator(germanium, x))
                    .collect(Collectors.toList());
            List<Locator<WebElement>> containingFilters = insideFilterSelector.getContainingFilters()
                    .stream()
                    .map((x) -> CreateLocator.<WebElement>createLocator(germanium, x))
                    .collect(Collectors.toList());
            List<Locator<WebElement>> containingAllFilters = insideFilterSelector.getContainingAllFilters()
                    .stream()
                    .map((x) -> CreateLocator.<WebElement>createLocator(germanium, x))
                    .collect(Collectors.toList());

            return (Locator<T>) new InsideFilterLocator(
                    germanium,
                    (DeferredLocator) CreateLocator.<WebElement>createLocator(germanium, insideFilterSelector.getParentSelector()),
                    insideFilters,
                    containingFilters,
                    containingAllFilters,
                    insideFilterSelector.isWithoutChildrenElements()
            );
        }

        if (selector instanceof AbstractSelector) {
            AbstractSelector abstractSelector = (AbstractSelector) selector;

            Collection<String> selectors = abstractSelector.getSelectors();

            if (selectors.size() == 1) {
                return CreateLocator.createLocator(
                        germanium,
                        selectors.iterator().next());
            }

            List<DeferredLocator> locatorList = new ArrayList<>();

            for (String s: selectors) {
                locatorList.add((DeferredLocator)
                        CreateLocator.<WebElement>createLocator(germanium, s));
            }

            return (Locator<T>) new CompositeLocator(locatorList);
        }

        if (selector instanceof WebElement) {
            return (Locator<T>) new StaticElementLocator((WebElement) selector);
        }

        if (selector instanceof Alert) {
            return (Locator<T>) new AlertLocator(germanium);
        }

        if (selector instanceof Window) {
            return (Locator<T>) new WindowLocator((Window) selector);
        }

        if (selector instanceof Supplier) {
            return createLocator(germanium, ((Supplier)selector).get());
        }

        if (selector instanceof Iterable) {
            Iterable iterableSelector = (Iterable) new ArrayList<WebElement>();

            for (Object item: iterableSelector) {
                if (!(item instanceof WebElement)) {
                    raiseBadSelectorType(selector);
                }
            }

            return (Locator<T>) new StaticElementLocator(iterableSelector);
        }

        if (!(selector instanceof String)) {
            raiseBadSelectorType(selector);
        }

        if (selector == "alert") {
            return (Locator<T>) new AlertLocator(germanium);
        }

        String stringSelector = (String) selector;

        if (stringSelector.startsWith("//")) {
            return (Locator<T>) new XPathLocator(germanium, stringSelector);
        }

        Matcher m = LOCATOR_SPECIFIER.matcher(stringSelector);

        if (m.matches()) {
            Class<? extends Locator> clazz = germanium.getLocatorMap().get(m.group(2));
            if (clazz != null) {
                return instantiateLocator(clazz, germanium, m.group(3));
            }
        }

        return (Locator<T>) new CssLocator(germanium, stringSelector);
    }

    private static void raiseBadSelectorType(Object selector) {
        throw new IllegalArgumentException(String.format(
                "Unable to build locator from the selector. " +
                "The selector: %s, that is of type: %s is " +
                "not a string selector, does not inherit from " +
                "AbstractSelector, is not an Alert, nor even a " +
                "selenium WebElement or WebElement list.",
                selector,
                selector == null ? "<None>" : selector.getClass().getCanonicalName()
        ));
    }

    private static Locator instantiateLocator(Class<? extends Locator> clazz,
                                       GermaniumDriver germanium,
                                       Object selector) {
        try {
            Constructor<? extends Locator> constructor = clazz.getConstructor(GermaniumDriver.class, Object.class);
            return constructor.newInstance(germanium, selector);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new IllegalArgumentException("Unable to instantiate: " + clazz, e);
        }
    }
}
