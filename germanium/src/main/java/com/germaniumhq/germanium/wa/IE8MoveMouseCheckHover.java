package com.germaniumhq.germanium.wa;

import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.impl.GermaniumCompositeAction;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.MoveMouseAction;
import org.openqa.selenium.interactions.MoveToOffsetAction;
import org.openqa.selenium.interactions.PauseAction;
import org.openqa.selenium.internal.Locatable;

import static com.germaniumhq.germanium.all.GermaniumApi.getGermanium;
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
                .addAction(new MoveMouseAction(getGermanium().getMouse(), (Locatable) element))
                .addAction(new PauseAction(200));

        actionChain.addDynamicAction((chain) -> {
            if (currentScroll[0].equals(getScrollXY())) {
                return;
            }

            chain.addAction(new MoveToOffsetAction(
                            getGermanium().getMouse(),
                            (Locatable) element,
                            -1,
                            -1)
                    ).addAction(new MoveMouseAction(
                            getGermanium().getMouse(),
                            (Locatable) element)
                    );
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
