package com.germaniumhq.germanium.all;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.all.operations.ChildNodes;
import com.germaniumhq.germanium.all.operations.OpenBrowser;
import com.germaniumhq.germanium.all.operations.actions.GetAttributes;
import com.germaniumhq.germanium.all.operations.actions.GetText;
import com.germaniumhq.germanium.all.operations.actions.GetValue;
import com.germaniumhq.germanium.locators.Locator;
import com.germaniumhq.germanium.selectors.Selector;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * The full static Germanium API.
 */
public class GermaniumApi {
    private static GermaniumDriver INSTANCE;

    public static OpenBrowser openBrowser() {
        return new OpenBrowser();
    }

    public static GermaniumDriver getGermanium() {
        return INSTANCE;
    }

    public static Locator<WebElement> S(WebElement element) {
        return GermaniumApi.getGermanium().S(element);
    }

    public static Locator<WebElement> S(WebElement element, GermaniumDriver germanium) {
        return germanium.S(element);
    }

    public static <T> Locator<T> S(Selector<T> selector) {
        return GermaniumApi.getGermanium().S(selector);
    }

    public static <T> Locator<T> S(Selector<T> selector, GermaniumDriver germanium) {
        return germanium.S(selector);
    }

    public static <T> Locator<T> S(String selector) {
        return GermaniumApi.S(selector, GermaniumApi.getGermanium());
    }

    public static <T> Locator<T> S(String selector, GermaniumDriver germanium) {
        return germanium.S(selector);
    }

    public static <T> Locator<T> S(Supplier<?> selector) {
        return GermaniumApi.getGermanium().S(selector);
    }

    public static <T> Locator<T> S(Supplier<?> selector, GermaniumDriver germanium) {
        return germanium.S(selector);
    }

    /**
     * Sets the current instance of germanium to the given one,
     * and returns the old one that was set previously.
     *
     * @param germanium
     * @return
     */
    public static GermaniumDriver setGermanium(GermaniumDriver germanium) {
        GermaniumDriver oldInstance = INSTANCE;
        INSTANCE = germanium;

        return oldInstance;
    }

    /**
     * Close the currently running browser.
     */
    public static void closeBrowser() {
        INSTANCE.close();
        INSTANCE = null;
    }

    public static <T> T js(String code, Object ... parameters) {
        return getGermanium().js(code, parameters);
    }

    public static String getValue(String selector) {
        return GetValue.getValue(selector);
    }

    public static String getValue(WebElement element) {
        return GetValue.getValue(element);
    }

    public static String getValue(Selector<WebElement> selector) {
        return GetValue.getValue(selector);
    }

    public static String getValue(Locator<WebElement> locator) {
        return GetValue.getValue(locator);
    }

    public static String getText(String selector) {
        return GetText.getText(selector);
    }

    public static String getText(WebElement element) {
        return GetText.getText(element);
    }

    public static String getText(Selector<WebElement> selector) {
        return GetText.getText(selector);
    }

    public static String getText(Locator<WebElement> locator) {
        return GetText.getText(locator);
    }

    public static List<String> getValueAll(String selector) {
        return GetValue.getValueAll(selector);
    }

    public static List<String> getValueAll(WebElement element) {
        return GetValue.getValueAll(element);
    }

    public static List<String> getValueAll(Selector<WebElement> selector) {
        return GetValue.getValueAll(selector);
    }

    public static List<String> getValueAll(Locator<WebElement> locator) {
        return GetValue.getValueAll(locator);
    }

    public static List<WebElement> childNodes(String selector) {
        return ChildNodes.getChildNodes(selector, ChildNodes.ElementType.ONLY_ELEMENTS);
    }

    public static List<WebElement> childNodes(WebElement selector) {
        return ChildNodes.getChildNodes(selector, ChildNodes.ElementType.ONLY_ELEMENTS);
    }

    public static List<WebElement> childNodes(Selector<WebElement> selector) {
        return ChildNodes.getChildNodes(selector, ChildNodes.ElementType.ONLY_ELEMENTS);
    }

    public static List<WebElement> childNodes(Locator<WebElement> selector) {
        return ChildNodes.getChildNodes(selector, ChildNodes.ElementType.ONLY_ELEMENTS);
    }

    public static List<WebElement> childNodes(String selector, ChildNodes.ElementType nodeType) {
        return ChildNodes.getChildNodes(selector, nodeType);
    }

    public static List<WebElement> childNodes(WebElement selector, ChildNodes.ElementType nodeType) {
        return ChildNodes.getChildNodes(selector, nodeType);
    }

    public static List<WebElement> childNodes(Selector<WebElement> selector, ChildNodes.ElementType nodeType) {
        return ChildNodes.getChildNodes(selector, nodeType);
    }

    public static List<WebElement> childNodes(Locator<WebElement> selector, ChildNodes.ElementType nodeType) {
        return ChildNodes.getChildNodes(selector, nodeType);
    }

    public static Map<String, String> getAttributes(String selector) {
        return GetAttributes.getAttributes(selector, Locator.Visibility.ONLY_VISIBLE);
    }

    public static Map<String, String> getAttributes(WebElement selector) {
        return GetAttributes.getAttributes(selector, Locator.Visibility.ONLY_VISIBLE);
    }

    public static Map<String, String> getAttributes(Locator<WebElement> selector) {
        return GetAttributes.getAttributes(selector, Locator.Visibility.ONLY_VISIBLE);
    }

    public static Map<String, String> getAttributes(Selector<WebElement> selector) {
        return GetAttributes.getAttributes(selector, Locator.Visibility.ONLY_VISIBLE);
    }

    public static Map<String, String> getAttributes(String selector, Locator.Visibility visibility) {
        return GetAttributes.getAttributes(selector, visibility);
    }

    public static Map<String, String> getAttributes(WebElement selector, Locator.Visibility visibility) {
        return GetAttributes.getAttributes(selector, visibility);
    }

    public static Map<String, String> getAttributes(Locator<WebElement> selector, Locator.Visibility visibility) {
        return GetAttributes.getAttributes(selector, visibility);
    }

    public static Map<String, String> getAttributes(Selector<WebElement> selector, Locator.Visibility visibility) {
        return GetAttributes.getAttributes(selector, visibility);
    }
}
