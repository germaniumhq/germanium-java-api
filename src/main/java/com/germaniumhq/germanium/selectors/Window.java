package com.germaniumhq.germanium.selectors;

import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.locators.Locator;
import com.germaniumhq.germanium.locators.WebDriverWindow;

import java.util.List;

/**
 * A selector that matches windows that are given by WebDriver,
 * either by their title, either by their saved ID.
 */
public class Window implements Selector<WebDriverWindow>, Locator<WebDriverWindow> {
    private String id;
    private String title;

    public Window id(String id) {
        this.id = id;
        return this;
    }

    public Window title(String title) {
        this.title = title;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public WebDriverWindow element() {
        return GermaniumApi.<WebDriverWindow>resolveS(this).element();
    }

    @Override
    public WebDriverWindow element(Visibility visibility) {
        return GermaniumApi.<WebDriverWindow>resolveS(this).element(visibility);
    }

    @Override
    public List<WebDriverWindow> elementList() {
        return GermaniumApi.<WebDriverWindow>resolveS(this).elementList();
    }

    @Override
    public List<WebDriverWindow> elementList(Visibility visibility) {
        return GermaniumApi.<WebDriverWindow>resolveS(this).elementList(visibility);
    }

    @Override
    public WebDriverWindow elementList(int index) {
        return GermaniumApi.<WebDriverWindow>resolveS(this).elementList(index);
    }

    @Override
    public boolean exists() {
        return GermaniumApi.<WebDriverWindow>resolveS(this).exists();
    }

    @Override
    public boolean exists(Visibility visibility) {
        return GermaniumApi.<WebDriverWindow>resolveS(this).exists(visibility);
    }

    @Override
    public boolean notExists() {
        return GermaniumApi.<WebDriverWindow>resolveS(this).notExists();
    }

    @Override
    public boolean notExists(Visibility visibility) {
        return GermaniumApi.<WebDriverWindow>resolveS(this).notExists(visibility);
    }

    @Override
    public String text() {
        return GermaniumApi.<WebDriverWindow>resolveS(this).text();
    }

    @Override
    public String text(Visibility visibility) {
        return GermaniumApi.<WebDriverWindow>resolveS(this).text(visibility);
    }

    @Override
    public WebDriverWindow get() {
        return GermaniumApi.<WebDriverWindow>resolveS(this).get();
    }
}
