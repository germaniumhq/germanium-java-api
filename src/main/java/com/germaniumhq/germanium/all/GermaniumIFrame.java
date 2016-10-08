package com.germaniumhq.germanium.all;

import com.germaniumhq.germanium.GermaniumDriver;

import java.util.function.Supplier;

public class GermaniumIFrame {
    /**
     * Run the given code into the specified IFrame, then switch
     * back to the original IFrame.
     */
    public static void runInFrame(Runnable runnable) {
        GermaniumIFrame.runInFrame(GermaniumApi.getGermanium(),
                "default",
                runnable);
    }

    /**
     * Run the given code into the specified IFrame, then switch
     * back to the original IFrame, returning the result given.
     */
    public static <T> T runInFrame(Supplier<T> supplier) {
        return GermaniumIFrame.runInFrame(GermaniumApi.getGermanium(),
                "default",
                supplier);
    }

    /**
     * Run the given code into the specified IFrame, then switch
     * back to the original IFrame.
     */
    public static void runInFrame(String iframeName, Runnable runnable) {
        GermaniumIFrame.runInFrame(GermaniumApi.getGermanium(),
                                   iframeName,
                                   runnable);
    }

    /**
     * Run the given code into the specified IFrame, then switch
     * back to the original IFrame, returning the result given.
     */
    public static <T> T runInFrame(String iframeName, Supplier<T> supplier) {
        return GermaniumIFrame.runInFrame(GermaniumApi.getGermanium(),
                iframeName,
                supplier);
    }

    /**
     * Run the given code into the specified IFrame, then switch
     * back to the original IFrame.
     */
    public static void runInFrame(GermaniumDriver germanium, String iframeName, Runnable runnable) {
        String originalIFrame = germanium.getCurrentIFrame();

        try {
            germanium.selectIFrame(iframeName);
            runnable.run();
        } finally {
            germanium.selectIFrame(originalIFrame);
        }
    }

    /**
     * Run the given code into the specified IFrame, then switch
     * back to the original IFrame, returning the result given.
     */
    public static <T> T runInFrame(GermaniumDriver germanium, String iframeName, Supplier<T> supplier) {
        String originalIFrame = germanium.getCurrentIFrame();

        try {
            germanium.selectIFrame(iframeName);
            return supplier.get();
        } finally {
            germanium.selectIFrame(originalIFrame);
        }
    }

}
