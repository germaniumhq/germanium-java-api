package com.germaniumhq.germanium.all;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.locators.Locator;
import com.germaniumhq.germanium.selectors.Selector;

/**
 * The full static Germanium API.
 */
public class GermaniumApi {
    public static void openBrowser(String browser) {
    }

    public static GermaniumDriver getGermanium() {
        throw new IllegalArgumentException("Not implemented");
    }

    public static <T> Locator<T> S(Selector<T> selector) {
    }

    public static <T> Locator<T> S(Selector<T> selector, GermaniumDriver germanium) {
        return germanium.S(selector);
    }

    public static <T> Locator<T> S(String selector) {
    }

    public static <T> Locator<T> S(String selector, GermaniumDriver germanium) {
    }
}
