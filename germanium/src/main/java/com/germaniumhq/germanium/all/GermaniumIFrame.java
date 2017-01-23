package com.germaniumhq.germanium.all;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.selectors.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

    public static void useWindow(String windowTitle) {
        useWindow(new Window().title(windowTitle));
    }

    public static void useWindow(Window window) {
        if (window.getId() != null) {
            GermaniumApi.getGermanium().switchTo().window(window.getId());
        }

        if (window.getTitle() != null) {
            List<String> foundTitles = new ArrayList<>();

            for (String windowHandle: GermaniumApi.getGermanium().getWindowHandles()) {
                String pageTitle;

                try {
                    GermaniumApi.getGermanium().switchTo().window(windowHandle);
                    pageTitle = GermaniumApi.getGermanium().getTitle();

                    if (window.getTitle().equals(pageTitle)) {
                        return;
                    }
                } catch (Exception e) {
                    pageTitle = String.format("?unable-to-read-title: %s?", e.getMessage());
                }

                foundTitles.add(pageTitle);
            }

            throw new IllegalArgumentException(String.format(
                    "Unable to find a Window with title `%s` in the list of windows: [%s].",
                    window.getTitle(),
                    foundTitles.stream().collect(Collectors.joining(", "))));
        }

        throw new IllegalArgumentException(
                "When using a window, you need to either specify its `title`, either " +
                "its `id` that you can obtain from the `germanium.getWindowHandles()`.");
    }
}
