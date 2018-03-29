package com.germaniumhq.germanium.all.operations;

import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.impl.GermaniumCompositeAction;
import com.germaniumhq.germanium.points.Point;
import com.germaniumhq.germanium.util.ActionElementFinder;
import com.germaniumhq.germanium.wa.EdgeMoveToElementWorkaround;
import com.germaniumhq.germanium.wa.IE8MoveMouseCheckHover;
import org.openqa.selenium.WebElement;

public class MouseActions {
    private static Object elementOrPosition(Object item) {
        if (item instanceof Point) {
            return item;
        }

        return ActionElementFinder.singleElement(item);
    }

    /**
     * Function to check if the given selector is only a regular
     * element without offset clicking. If that is the case, then we
     * enable the double hovering in the mouse actions, to solve a
     * host of issues with hovering and scrolling, such as elements
     * appearing on mouse in, or edge not hovering correctly.
     *
     * @param selector
     * @param point
     * @return
     */
    private static WebElement elementOrNull(Object selector, Point point) {
        if (selector instanceof Point) {
            return null;
        }

        if (point != null) {
            return null;
        }

        return ActionElementFinder.singleElement(selector);
    }

    private static void moveToElement(GermaniumCompositeAction actionChain, WebElement element) {
        new EdgeMoveToElementWorkaround(actionChain, element, () -> {
            new IE8MoveMouseCheckHover(actionChain, element, () -> {
                actionChain.moveToElement(element);
            }).execute();
        }).execute();
    }

    private static GermaniumCompositeAction mouseMove(Object selector, Point point, GermaniumCompositeAction action) {
        if (action == null) {
            action = new GermaniumCompositeAction(GermaniumApi.getWebDriver());
        }

        Object element = elementOrPosition(selector);

        if (element instanceof Point) {
            action.moveToElement(
                    GermaniumApi.S("body").element(),
                    ((Point) element).getX(),
                    ((Point) element).getY());
        } else if (selector != null && point != null) {
            action.moveToElement(
                    (WebElement) element,
                    point.getX(),
                    point.getY());
        } else if (selector != null) {
            moveToElement(action, (WebElement) element);
        }

        return action;
    }

    public static void click(Object selector, Point point) {
        Object element = elementOrNull(selector, point);

        if (element != null) {
            mouseMove(element, null, null)
                    .click((WebElement) element)
                    .perform();

            return;
        }

        mouseMove(selector, point, null)
                .click()
                .perform();
    }

    public static void rightClick(Object selector, Point point) {
        Object element = elementOrNull(selector, point);

        if (element != null) {
            mouseMove(element, null, null)
                    .contextClick((WebElement) element)
                    .perform();

            return;
        }

        mouseMove(selector, point, null)
                .contextClick()
                .perform();
    }

    public static void doubleClick(Object selector, Point point) {
        Object element = elementOrNull(selector, point);

        if (element != null) {
            mouseMove(element, null, null)
                    .doubleClick((WebElement) element)
                    .perform();

            return;
        }

        mouseMove(selector, point, null)
                .doubleClick()
                .perform();
    }

    public static void hover(Object selector, Point point) {
        Object element = elementOrNull(selector, point);

        if (element != null) {
            mouseMove(element, null, null)
                    .perform();

            return;
        }

        mouseMove(selector, point, null)
                .perform();
    }

    public static void dragAndDrop(Object fromSelector, Point fromPoint,
                                   Object toSelector, Point toPoint) {
        WebElement fromElement = elementOrNull(fromSelector, fromPoint);

        GermaniumCompositeAction action;

        if (fromElement != null) {
            action = (GermaniumCompositeAction) mouseMove(fromElement, null, null)
                    .clickAndHold(fromElement);
        } else {
            action = (GermaniumCompositeAction) mouseMove(fromSelector, fromPoint, null)
                    .clickAndHold();
        }

        WebElement toElement = elementOrNull(toSelector, toPoint);

        if (toElement != null) {
            action = (GermaniumCompositeAction) mouseMove(toElement, null, action)
                    .release(toElement);
        } else {
            action = (GermaniumCompositeAction) mouseMove(toSelector, toPoint, action)
                    .release();
        }

        action.perform();
    }
}