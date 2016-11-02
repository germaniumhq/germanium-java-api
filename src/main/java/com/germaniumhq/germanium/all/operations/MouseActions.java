package com.germaniumhq.germanium.all.operations;

import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.points.Point;
import com.germaniumhq.germanium.util.ActionElementFinder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.interactions.CompositeAction;
import org.openqa.selenium.interactions.MoveMouseAction;
import org.openqa.selenium.interactions.MoveToOffsetAction;
import org.openqa.selenium.internal.Locatable;

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
    private static Object elementOrNull(Object selector, Point point) {
        if (selector instanceof Point) {
            return null;
        }

        if (point != null) {
            return null;
        }

        return ActionElementFinder.singleElement(selector);
    }

    private static void moveToElement(CompositeAction actionChain, WebElement element) {
        actionChain.addAction(new MoveMouseAction(
                GermaniumApi.getGermanium().getMouse(),
                (Locatable) element));
    }

    private static CompositeAction mouseMove(Object selector, Point point, CompositeAction action) {
        if (action == null) {
            action = new CompositeAction();
        }

        Object element = elementOrPosition(selector);

        if (element instanceof Point) {
            action.addAction(new MoveToOffsetAction(
                    GermaniumApi.getGermanium().getMouse(),
                    (Locatable) GermaniumApi.S("body").element(),
                    ((Point) element).getX(),
                    ((Point) element).getY()));
        } else if (selector != null && point != null) {
            action.addAction(new MoveToOffsetAction(
                    GermaniumApi.getGermanium().getMouse(),
                    (Locatable) element,
                    point.getX(),
                    point.getY()));
        } else if (selector != null) {
            moveToElement(action, (WebElement) element);
        }

        return action;
    }

    public static void click(Object selector, Point point) {
        Object element = elementOrNull(selector, point);

        if (element != null) {
            mouseMove(element, null, null)
                    .addAction(new ClickAction(
                            GermaniumApi.getGermanium().getMouse(),
                            (Locatable) element))
                    .perform();

            return;
        }

        mouseMove(selector, point, null)
                .addAction(new ClickAction(
                        GermaniumApi.getGermanium().getMouse(),
                        (Locatable) element))
                .perform();
    }
}