package com.germaniumhq.germanium.wa;

import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.impl.GermaniumCompositeAction;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PauseAction;

import static com.germaniumhq.germanium.all.GermaniumApi.js;

public class IE8MoveMouseCheckHover extends RunnableWorkaround {
    private final GermaniumCompositeAction actionChain;
    private final WebElement element;

    public IE8MoveMouseCheckHover(GermaniumCompositeAction actionChain, WebElement element, Runnable defaultCode) {
        super(defaultCode);
        this.actionChain = actionChain;
        this.element = element;
    }

    @Override
    protected void executeWorkAround() {
        final Object[] currentScroll = new Object[1];

        actionChain.addAction(() -> currentScroll[0] = getScrollXY())
                .moveToElement(element)
                .addAction(new PauseAction(200));

        actionChain.addDynamicAction((chain) -> {
            if (currentScroll[0].equals(getScrollXY())) {
                return;
            }

            chain.moveToElement(element, -1, -1)
                    .moveToElement(element);
        });

    }

    private Object getScrollXY() {
        return js("return [document.documentElement.scrollTop, document.documentElement.scrollLeft];");
    }

    @Override
    protected boolean isApplicable() {
        if (!(GermaniumApi.getWebDriver() instanceof HasCapabilities)) {
            return false;
        }

        HasCapabilities capabilities = ((HasCapabilities)GermaniumApi.getWebDriver());

        return "internet explorer".equals(capabilities.getCapabilities().getBrowserName().toLowerCase()) &&
                "8".equals(capabilities.getCapabilities().getVersion());
    }
}
