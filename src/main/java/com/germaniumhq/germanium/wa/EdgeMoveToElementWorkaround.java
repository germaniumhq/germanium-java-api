package com.germaniumhq.germanium.wa;

import com.germaniumhq.germanium.all.GermaniumApi;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.CompositeAction;
import org.openqa.selenium.interactions.MoveToOffsetAction;
import org.openqa.selenium.internal.Locatable;

/**
 * This is a workaround for Edge, since calling move to element directly doesn't actually
 * moves the mouse, but moving to the element with an offset, will move the mouse correctly.
 */
public class EdgeMoveToElementWorkaround extends RunnableWorkaround {
    private final CompositeAction actionChain;
    private final WebElement element;

    public EdgeMoveToElementWorkaround(CompositeAction actionChain, WebElement element, Runnable defaultCode) {
        super(defaultCode);
        this.actionChain = actionChain;
        this.element = element;
    }

    @Override
    protected void executeWorkAround() {
        actionChain.addAction(
                new MoveToOffsetAction(
                        GermaniumApi.getGermanium().getMouse(),
                        (Locatable) element,
                        0,
                        0));
    }

    @Override
    protected boolean isApplicable() {
        if (!(GermaniumApi.getWebDriver() instanceof HasCapabilities)) {
            return false;
        }

        HasCapabilities capabilities = ((HasCapabilities)GermaniumApi.getWebDriver());

        return "microsoftedge".equals(capabilities.getCapabilities().getBrowserName().toLowerCase());
    }
}
