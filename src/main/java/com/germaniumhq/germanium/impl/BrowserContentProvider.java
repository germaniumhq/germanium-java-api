package com.germaniumhq.germanium.impl;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.locators.Locator;

import java.util.List;

/**
 * Interface used to mark that content is going to be returned
 * from a browser.
 */
public interface BrowserContentProvider<T> extends Locator<T> {
    T element();
    T element(Visibility visibility, GermaniumDriver germaniumDriver);
    T element(GermaniumDriver germanium);

    List<T> elementList();
    List<T> elementList(Visibility visibility, GermaniumDriver germaniumDriver);
    List<T> elementList(GermaniumDriver germanium);

    boolean exists();
    boolean exists(Visibility visibility, GermaniumDriver germaniumDriver);
    boolean exists(GermaniumDriver germanium);

    boolean notExists();
    boolean notExists(Visibility visibility, GermaniumDriver germaniumDriver);
    boolean notExists(GermaniumDriver germanium);

    String text();
    String text(Visibility visibility, GermaniumDriver germaniumDriver);
    String text(GermaniumDriver germanium);
}
