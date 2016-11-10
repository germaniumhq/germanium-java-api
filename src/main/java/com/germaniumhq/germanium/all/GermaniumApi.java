package com.germaniumhq.germanium.all;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.all.operations.ChildNodes;
import com.germaniumhq.germanium.all.operations.OpenBrowser;
import com.germaniumhq.germanium.all.operations.ParentNode;
import com.germaniumhq.germanium.all.operations.actions.Deselect;
import com.germaniumhq.germanium.all.operations.actions.GetAttributes;
import com.germaniumhq.germanium.all.operations.actions.GetStyle;
import com.germaniumhq.germanium.all.operations.actions.GetText;
import com.germaniumhq.germanium.all.operations.actions.GetValue;
import com.germaniumhq.germanium.all.operations.actions.Highlight;
import com.germaniumhq.germanium.all.operations.actions.Select;
import com.germaniumhq.germanium.all.operations.actions.SelectFile;
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
     * @param germanium The germanium instance to use.
     * @return The old instance if present. Null if no other instance was there.
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

    public static <T> T js(String code, Object... parameters) {
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

    public static WebElement parentNode(String selector) {
        return ParentNode.parentNode(selector);
    }

    public static WebElement parentNode(WebElement selector) {
        return ParentNode.parentNode(selector);
    }

    public static WebElement parentNode(Locator<WebElement> selector) {
        return ParentNode.parentNode(selector);
    }

    public static WebElement parentNode(Selector<WebElement> selector) {
        return ParentNode.parentNode(selector);
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

    public static String getStyle(String selector, String keyName) {
        return GetStyle.getStyle(selector, keyName, Locator.Visibility.ONLY_VISIBLE);
    }

    public static String getStyle(WebElement selector, String keyName) {
        return GetStyle.getStyle(selector, keyName, Locator.Visibility.ONLY_VISIBLE);
    }

    public static String getStyle(Locator<WebElement> selector, String keyName) {
        return GetStyle.getStyle(selector, keyName, Locator.Visibility.ONLY_VISIBLE);
    }

    public static String getStyle(Selector<WebElement> selector, String keyName) {
        return GetStyle.getStyle(selector, keyName, Locator.Visibility.ONLY_VISIBLE);
    }

    public static String getStyle(String selector, String keyName, Locator.Visibility visibility) {
        return GetStyle.getStyle(selector, keyName, visibility);
    }

    public static String getStyle(WebElement selector, String keyName, Locator.Visibility visibility) {
        return GetStyle.getStyle(selector, keyName, visibility);
    }

    public static String getStyle(Locator<WebElement> selector, String keyName, Locator.Visibility visibility) {
        return GetStyle.getStyle(selector, keyName, visibility);
    }

    public static String getStyle(Selector<WebElement> selector, String keyName, Locator.Visibility visibility) {
        return GetStyle.getStyle(selector, keyName, visibility);
    }

    public static void highlight(String selector) {
        Highlight.highlight(selector, Highlight.Console.NONE, Highlight.DEFAULT_SHOW_HIGHLIGHT_SECONDS, Highlight.DEFAULT_BLINK_DURATION_SECONDS);
    }

    public static void highlight(WebElement selector) {
        Highlight.highlight(selector, Highlight.Console.NONE, Highlight.DEFAULT_SHOW_HIGHLIGHT_SECONDS, Highlight.DEFAULT_BLINK_DURATION_SECONDS);
    }

    public static void highlight(Locator<WebElement> selector) {
        Highlight.highlight(selector, Highlight.Console.NONE, Highlight.DEFAULT_SHOW_HIGHLIGHT_SECONDS, Highlight.DEFAULT_BLINK_DURATION_SECONDS);
    }

    public static void highlight(Selector<WebElement> selector) {
        Highlight.highlight(selector, Highlight.Console.NONE, Highlight.DEFAULT_SHOW_HIGHLIGHT_SECONDS, Highlight.DEFAULT_BLINK_DURATION_SECONDS);
    }

    public static void highlight(String selector, Highlight.Console console) {
        Highlight.highlight(selector, console, Highlight.DEFAULT_SHOW_HIGHLIGHT_SECONDS, Highlight.DEFAULT_BLINK_DURATION_SECONDS);
    }

    public static void highlight(WebElement selector, Highlight.Console console) {
        Highlight.highlight(selector, console, Highlight.DEFAULT_SHOW_HIGHLIGHT_SECONDS, Highlight.DEFAULT_BLINK_DURATION_SECONDS);
    }

    public static void highlight(Locator<WebElement> selector, Highlight.Console console) {
        Highlight.highlight(selector, console, Highlight.DEFAULT_SHOW_HIGHLIGHT_SECONDS, Highlight.DEFAULT_BLINK_DURATION_SECONDS);
    }

    public static void highlight(Selector<WebElement> selector, Highlight.Console console) {
        Highlight.highlight(selector, console, Highlight.DEFAULT_SHOW_HIGHLIGHT_SECONDS, Highlight.DEFAULT_BLINK_DURATION_SECONDS);
    }

    public static void highlight(String selector, Highlight.Console console, float showHighLightSeconds, float blinkDurationSeconds) {
        Highlight.highlight(selector, console, showHighLightSeconds, blinkDurationSeconds);
    }

    public static void highlight(WebElement selector, Highlight.Console console, float showHighLightSeconds, float blinkDurationSeconds) {
        Highlight.highlight(selector, console, showHighLightSeconds, blinkDurationSeconds);
    }

    public static void highlight(Locator<WebElement> selector, Highlight.Console console, float showHighLightSeconds, float blinkDurationSeconds) {
        Highlight.highlight(selector, console, showHighLightSeconds, blinkDurationSeconds);
    }

    public static void highlight(Selector<WebElement> selector, Highlight.Console console, float showHighLightSeconds, float blinkDurationSeconds) {
        Highlight.highlight(selector, console, showHighLightSeconds, blinkDurationSeconds);
    }

    public static void select(String selector, String... text) {
        Select.select(selector, text);
    }

    public static void select(WebElement selector, String... text) {
        Select.select(selector, text);
    }

    public static void select(Locator<WebElement> selector, String... text) {
        Select.select(selector, text);
    }

    public static void select(Selector<WebElement> selector, String... text) {
        Select.select(selector, text);
    }

    public static void selectByIndex(String selector, int... indexes) {
        Select.selectByIndex(selector, indexes);
    }

    public static void selectByIndex(WebElement selector, int... indexes) {
        Select.selectByIndex(selector, indexes);
    }

    public static void selectByIndex(Locator<WebElement> selector, int... indexes) {
        Select.selectByIndex(selector, indexes);
    }

    public static void selectByIndex(Selector<WebElement> selector, int... indexes) {
        Select.selectByIndex(selector, indexes);
    }

    public static void selectByValue(String selector, String... values) {
        Select.selectByValue(selector, values);
    }

    public static void selectByValue(WebElement selector, String... values) {
        Select.selectByValue(selector, values);
    }

    public static void selectByValue(Locator<WebElement> selector, String... values) {
        Select.selectByValue(selector, values);
    }

    public static void selectByValue(Selector<WebElement> selector, String... values) {
        Select.selectByValue(selector, values);
    }

    public static void selectFile(String selector, String fileName) {
        SelectFile.selectFile(selector, fileName, SelectFile.CheckPath.CHECK);
    }

    public static void selectFile(WebElement selector, String fileName) {
        SelectFile.selectFile(selector, fileName, SelectFile.CheckPath.CHECK);
    }

    public static void selectFile(Locator<WebElement> selector, String fileName) {
        SelectFile.selectFile(selector, fileName, SelectFile.CheckPath.CHECK);
    }

    public static void selectFile(String selector, String fileName, SelectFile.CheckPath checkPath) {
        SelectFile.selectFile(selector, fileName, checkPath);
    }

    public static void selectFile(WebElement selector, String fileName, SelectFile.CheckPath checkPath) {
        SelectFile.selectFile(selector, fileName, checkPath);
    }

    public static void selectFile(Selector<WebElement> selector, String fileName, SelectFile.CheckPath checkPath) {
        SelectFile.selectFile(selector, fileName, checkPath);
    }

    public static void selectFile(Locator<WebElement> selector, String fileName, SelectFile.CheckPath checkPath) {
        SelectFile.selectFile(selector, fileName, checkPath);
    }

    //
    public static void deselect(String selector, String... text) {
        Deselect.deselect(selector, text);
    }

    public static void deselect(WebElement selector, String... text) {
        Deselect.deselect(selector, text);
    }

    public static void deselect(Locator<WebElement> selector, String... text) {
        Deselect.deselect(selector, text);
    }

    public static void deselect(Selector<WebElement> selector, String... text) {
        Deselect.deselect(selector, text);
    }

    public static void deselectByIndex(String selector, int... indexes) {
        Deselect.deselectByIndex(selector, indexes);
    }

    public static void deselectByIndex(WebElement selector, int... indexes) {
        Deselect.deselectByIndex(selector, indexes);
    }

    public static void deselectByIndex(Locator<WebElement> selector, int... indexes) {
        Deselect.deselectByIndex(selector, indexes);
    }

    public static void deselectByIndex(Selector<WebElement> selector, int... indexes) {
        Deselect.deselectByIndex(selector, indexes);
    }

    public static void deselectByValue(String selector, String... values) {
        Deselect.deselectByValue(selector, values);
    }

    public static void deselectByValue(WebElement selector, String... values) {
        Deselect.deselectByValue(selector, values);
    }

    public static void deselectByValue(Locator<WebElement> selector, String... values) {
        Deselect.deselectByValue(selector, values);
    }

    public static void deselectByValue(Selector<WebElement> selector, String... values) {
        Deselect.deselectByValue(selector, values);
    }
}
