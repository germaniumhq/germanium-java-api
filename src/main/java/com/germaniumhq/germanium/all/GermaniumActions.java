package com.germaniumhq.germanium.all;

import com.germaniumhq.germanium.all.operations.MouseActions;
import com.germaniumhq.germanium.all.operations.actions.TypeKeys;
import com.germaniumhq.germanium.locators.Locator;
import com.germaniumhq.germanium.points.Point;
import com.germaniumhq.germanium.selectors.Alert;
import org.openqa.selenium.WebElement;

public class GermaniumActions {
    public static void click(String selector) {
        click(GermaniumApi.<WebElement>S(selector).element());
    }

    public static void click(Locator<WebElement> locator) {
        click(locator.element());
    }

    public static void click(WebElement element) {
        MouseActions.click(element, null);
    }

    public static void click(Point point) {
        MouseActions.click(point, null);
    }

    public static void click(String selector, Point offset) {
        click(GermaniumApi.<WebElement>S(selector).element(),
              offset);
    }

    public static void click(Locator<WebElement> locator, Point offset) {
        click(locator.element(),
              offset);
    }

    public static void click(WebElement element, Point offset) {
        MouseActions.click(element,
                           offset);
    }

    public static void click(Point point, Point offset) {
        MouseActions.click(point,
                           offset);
    }

    public static void rightClick(String selector) {
        rightClick(GermaniumApi.<WebElement>S(selector).element());
    }

    public static void rightClick(Locator<WebElement> locator) {
        rightClick(locator.element());
    }

    public static void rightClick(WebElement element) {
        MouseActions.rightClick(element, null);
    }

    public static void rightClick(Point point) {
        MouseActions.rightClick(point, null);
    }

    public static void rightClick(String selector, Point offset) {
        rightClick(GermaniumApi.<WebElement>S(selector).element(),
                offset);
    }

    public static void rightClick(Locator<WebElement> locator, Point offset) {
        rightClick(locator.element(),
                offset);
    }

    public static void rightClick(WebElement element, Point offset) {
        MouseActions.rightClick(element,
                offset);
    }

    public static void rightClick(Point point, Point offset) {
        MouseActions.rightClick(point,
                offset);
    }
    
    
    public static void doubleClick(String selector) {
        doubleClick(GermaniumApi.<WebElement>S(selector).element());
    }

    public static void doubleClick(Locator<WebElement> locator) {
        doubleClick(locator.element());
    }

    public static void doubleClick(WebElement element) {
        MouseActions.doubleClick(element, null);
    }

    public static void doubleClick(Point point) {
        MouseActions.doubleClick(point, null);
    }

    public static void doubleClick(String selector, Point offset) {
        doubleClick(GermaniumApi.<WebElement>S(selector).element(),
                offset);
    }

    public static void doubleClick(Locator<WebElement> locator, Point offset) {
        doubleClick(locator.element(),
                offset);
    }

    public static void doubleClick(WebElement element, Point offset) {
        MouseActions.doubleClick(element,
                offset);
    }

    public static void doubleClick(Point point, Point offset) {
        MouseActions.doubleClick(point,
                offset);
    }
    
    public static void hover(String selector) {
        hover(GermaniumApi.<WebElement>S(selector).element());
    }

    public static void hover(Locator<WebElement> locator) {
        hover(locator.element());
    }

    public static void hover(WebElement element) {
        MouseActions.hover(element, null);
    }

    public static void hover(Point point) {
        MouseActions.hover(point, null);
    }

    public static void hover(String selector, Point offset) {
        hover(GermaniumApi.<WebElement>S(selector).element(),
                offset);
    }

    public static void hover(Locator<WebElement> locator, Point offset) {
        hover(locator.element(),
                offset);
    }

    public static void hover(WebElement element, Point offset) {
        MouseActions.hover(element,
                offset);
    }

    public static void hover(Point point, Point offset) {
        MouseActions.hover(point,
                offset);
    }


    public static void typeKeys(String keys) {
        TypeKeys.typeKeys(keys, null, 0);
    }

    public static void typeKeys(String keys, String selector) {
        TypeKeys.typeKeys(keys, selector, 0);
    }

    public static void typeKeys(String keys, Alert alert) {
        TypeKeys.typeKeys(keys, alert, 0);
    }

    public static void typeKeys(String keys, Locator<WebElement> locator) {
        TypeKeys.typeKeys(keys, locator, 0);
    }

    public static void typeKeys(String keys, float delayInSeconds) {
        TypeKeys.typeKeys(keys, null, delayInSeconds);
    }

    public static void typeKeys(String keys, String selector, float delayInSeconds) {
        TypeKeys.typeKeys(keys, selector, delayInSeconds);
    }

    public static void typeKeys(String keys, Alert alert, float delayInSeconds) {
        TypeKeys.typeKeys(keys, alert, delayInSeconds);
    }

    public static void typeKeys(String keys, Locator<WebElement> locator, float delayInSeconds) {
        TypeKeys.typeKeys(keys, locator, delayInSeconds);
    }
}
