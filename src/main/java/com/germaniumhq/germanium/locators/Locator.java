package com.germaniumhq.germanium.locators;

import java.util.List;

/**
 * A locator is code bounded to a browser instance
 * that can retrieve content from that browser (WebElements,
 * Text, Alerts)
 *
 * @param <T>
 */
public interface Locator<T> {
    public enum Visibility {
        ONLY_VISIBLE,
        ALL_ELEMENTS
    }

    T element();

    T element(Visibility visibility);

    List<T> elementList();

    List<T> elementList(Visibility visibility);

    T elementList(int index);

    boolean exists();

    boolean exists(Visibility visibility);

    boolean notExists();

    boolean notExists(Visibility visibility);

    String text();

    String text(Visibility visibility);
}
