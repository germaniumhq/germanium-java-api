package com.germaniumhq.germanium.locators;

import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.selectors.Window;
import org.openqa.selenium.NoSuchWindowException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WindowLocator implements Locator<WebDriverWindow> {
    private final Window selector;

    public WindowLocator(Window selector) {
        this.selector = selector;
    }

    @Override
    public WebDriverWindow element() {
        List<WebDriverWindow> elements = elementList();

        if (!elements.isEmpty()) {
            return elements.get(0);
        }

        return null;
    }

    @Override
    public WebDriverWindow element(Visibility visibility) {
        return element();
    }

    @Override
    public List<WebDriverWindow> elementList() {
        if (this.selector != null && this.selector.getId() != null) {
            if (GermaniumApi.getWebDriver().getWindowHandles().contains(this.selector.getId())) {
                return Collections.emptyList();
            }

            String currentHandle = GermaniumApi.getWebDriver().getWindowHandle();
            GermaniumApi.getGermanium().switchTo().window(this.selector.getId());

            WebDriverWindow result = new WebDriverWindow(
                    this.selector.getId(),
                    GermaniumApi.getGermanium().getTitle()
            );

            GermaniumApi.getGermanium().switchTo().window(currentHandle);

            return Collections.singletonList(result);
        }

        List<WebDriverWindow> result = new ArrayList<>();

        String currentHandle = GermaniumApi.getWebDriver().getWindowHandle();

        for (String handle: GermaniumApi.getGermanium().getWindowHandles()) {
            GermaniumApi.getGermanium().switchTo().window(handle);
            String windowTitle = GermaniumApi.getGermanium().getTitle();

            // if the selector title is None, it means we need to find
            // all the opened windows, since the id is already checked
            // on the selector as being null.
            if (selector == null || selector.getTitle() == null) {
                result.add(new WebDriverWindow(
                        handle,
                        windowTitle
                ));
                continue;
            }

            if (windowTitle.equals(this.selector.getTitle())) {
                result.add(new WebDriverWindow(handle, windowTitle));
            }
        }

        try {
            GermaniumApi.getGermanium().switchTo().window(currentHandle);
        } catch (NoSuchWindowException e) {
            // The current window was closed while reading the list of opened
            // windows. We'll switch to the default window.
            GermaniumApi.getGermanium().switchTo().defaultContent();
        }

        return result;
    }

    @Override
    public List<WebDriverWindow> elementList(Visibility visibility) {
        return elementList();
    }

    @Override
    public WebDriverWindow elementList(int index) {
        if (index < 0) {
            throw new IllegalArgumentException(String.format(
                    "The index inside a list must be a positive number, not %d.", index));
        }

        List<WebDriverWindow> windows = elementList();

        if (index >= windows.size()) {
            throw new IllegalArgumentException(String.format(
                    "The element with index %d was requested, but only %d elements " +
                            "were available in the result.",
                    index,
                    windows.size()
            ));
        }

        return windows.get(index);
    }

    @Override
    public boolean exists() {
        return element() != null;
    }

    @Override
    public boolean exists(Visibility visibility) {
        return element() != null;
    }

    @Override
    public boolean notExists() {
        return element() == null;
    }

    @Override
    public boolean notExists(Visibility visibility) {
        return element() == null;
    }

    @Override
    public String text() {
        return element().getTitle();
    }

    @Override
    public String text(Visibility visibility) {
        return element().getTitle();
    }

    @Override
    public WebDriverWindow get() {
        return element();
    }
}
