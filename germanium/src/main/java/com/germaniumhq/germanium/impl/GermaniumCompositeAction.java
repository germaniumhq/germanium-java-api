package com.germaniumhq.germanium.impl;

import org.openqa.selenium.interactions.CompositeAction;

import java.util.function.Consumer;

/**
 * A regular composite action that allows executing actions that
 * will be inserted on demand in  the action chain.
 */
public class GermaniumCompositeAction extends CompositeAction {
    public GermaniumCompositeAction addDynamicAction(Consumer<GermaniumCompositeAction> compositeProvider) {
        this.addAction(() -> {
            GermaniumCompositeAction nestedActions = new GermaniumCompositeAction();
            compositeProvider.accept(nestedActions);

            nestedActions.perform();
        });

        return this;
    }
}
