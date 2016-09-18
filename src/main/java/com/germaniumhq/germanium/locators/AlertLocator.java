package com.germaniumhq.germanium.locators;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.impl.AlertChecker;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

import java.util.Collections;
import java.util.List;

public class AlertLocator implements Locator<Alert> {
    private GermaniumDriver germanium;

    public AlertLocator(GermaniumDriver germanium) {
        this.germanium = germanium;
    }

    @Override
    public Alert element() {
        return element(Visibility.ALL_ELEMENTS);
    }

    @Override
    public Alert element(Visibility visibility) {
        try {
            return germanium.switchTo().alert();
        } catch (NoAlertPresentException e) {
            return null;
        }
    }

    @Override
    public List<Alert> elementList() {
        return this.elementList(Visibility.ALL_ELEMENTS);
    }

    @Override
    public List<Alert> elementList(Visibility visibility) {
        Alert result = element(visibility);

        if (result != null) {
            return Collections.singletonList(result);
        }

        return Collections.emptyList();
    }

    @Override
    public boolean exists() {
        return exists(Visibility.ALL_ELEMENTS);
    }

    @Override
    public boolean exists(Visibility visibility) {
        return new AlertChecker(germanium).isAlertExisting();
    }

    @Override
    public boolean notExists() {
        return !exists();
    }

    @Override
    public boolean notExists(Visibility visibility) {
        return !exists(visibility);
    }

    @Override
    public String text() {
        return germanium.switchTo().alert().getText();
    }

    @Override
    public String text(Visibility visibility) {
        return germanium.switchTo().alert().getText();
    }

    @Override
    public List<Alert> get() {
        return null;
    }
}
