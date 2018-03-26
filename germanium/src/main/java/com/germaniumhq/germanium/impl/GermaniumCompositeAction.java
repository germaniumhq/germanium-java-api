package com.germaniumhq.germanium.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.function.Consumer;

/**
 * A regular composite action that allows executing actions that
 * will be inserted on demand in  the action chain.
 */
public class GermaniumCompositeAction extends Actions {
    private final WebDriver webDriver;

    public GermaniumCompositeAction(WebDriver driver) {
        super(driver);
        this.webDriver = driver;
    }

    public GermaniumCompositeAction addDynamicAction(Consumer<GermaniumCompositeAction> compositeProvider) {
        this.action.addAction(() -> {
            GermaniumCompositeAction nestedActions = new GermaniumCompositeAction(this.webDriver);
            compositeProvider.accept(nestedActions);

            nestedActions.perform();
        });

        return this;
    }

    public GermaniumCompositeAction addAction(Action action) {
        this.action.addAction(action);
        return this;
    }

    @Override
    public GermaniumCompositeAction moveToElement(WebElement target) {
        super.moveToElement(target);
        return this;
    }

    @Override
    public GermaniumCompositeAction moveToElement(WebElement target, int xOffset, int yOffset) {
        super.moveToElement(target, xOffset, yOffset);
        return this;
    }
}
