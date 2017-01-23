package com.germaniumhq.germanium.all;

import com.germaniumhq.germanium.util.Waited;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

public class Wait {
    /**
     * A static version of the whileNot.
     * @param conditions
     */
    public static com.germaniumhq.germanium.util.Wait whileNot(Supplier<?>...conditions) {
        return new com.germaniumhq.germanium.util.Wait(
                com.germaniumhq.germanium.util.Wait.DEFAULT_TIMEOUT_IN_SECONDS)
                .whileNot(conditions);
    }

    /**
     * A static version of the whileNot.
     * @param conditions
     */
    public static com.germaniumhq.germanium.util.Wait whileNot(float timeout, Supplier<?>...conditions) {
        return new com.germaniumhq.germanium.util.Wait(timeout)
                .whileNot(conditions);
    }

    /**
     * A static version of the waitFor.
     * @param conditions
     */
    public static void waitFor(Supplier<?>...conditions) {
        waitFor(com.germaniumhq.germanium.util.Wait.DEFAULT_TIMEOUT_IN_SECONDS,
                conditions);
    }

    /**
     * A static version of the waitFor.
     * @param conditions
     */
    public static void waitFor(float timeout, Supplier<?>...conditions) {
        new com.germaniumhq.germanium.util.Wait(timeout).waitFor(conditions);
    }

    public static WebElement waited(Supplier<?>...conditions) {
        return new Waited(com.germaniumhq.germanium.util.Wait.DEFAULT_TIMEOUT_IN_SECONDS)
                .waitFor(conditions);
    }

    public static WebElement waited(float timeout, Supplier<?>...conditions) {
        return new Waited(timeout)
                .waitFor(conditions);
    }
}
