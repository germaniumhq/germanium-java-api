package com.germaniumhq.germanium.all.operations;

import org.openqa.selenium.interactions.Action;

public class DelayAction implements Action {
    private float delayInSeconds;

    public DelayAction(float delayInSeconds) {
        this.delayInSeconds = delayInSeconds;
    }

    @Override
    public void perform() {
        try {
            Thread.sleep((int)(delayInSeconds * 1000));
        } catch (InterruptedException e) {
            throw new IllegalStateException("Interrupted.", e);
        }
    }
}
