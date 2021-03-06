package com.germaniumhq.germanium.selectors;

import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.locators.Locator;

import java.util.List;

import static com.germaniumhq.germanium.all.GermaniumApi.getGermanium;
import static com.germaniumhq.germanium.all.Wait.waitFor;

public class Alert implements Selector<org.openqa.selenium.Alert>, Locator<org.openqa.selenium.Alert> {
    @Override
    public org.openqa.selenium.Alert element() {
        return GermaniumApi.<org.openqa.selenium.Alert>resolveS(this).element();
    }

    @Override
    public org.openqa.selenium.Alert element(Visibility visibility) {
        return GermaniumApi.<org.openqa.selenium.Alert>resolveS(this).element();
    }

    @Override
    public List<org.openqa.selenium.Alert> elementList(Visibility visibility) {
        return GermaniumApi.<org.openqa.selenium.Alert>resolveS(this).elementList();
    }

    @Override
    public boolean exists(Visibility visibility) {
        return GermaniumApi.S(this).exists();
    }

    @Override
    public boolean notExists(Visibility visibility) {
        return GermaniumApi.S(this).notExists();
    }

    @Override
    public String text(Visibility visibility) {
        return GermaniumApi.S(this).text();
    }

    @Override
    public org.openqa.selenium.Alert elementList(int index) {
        return GermaniumApi.<org.openqa.selenium.Alert>resolveS(this)
                .elementList(index);
    }

    @Override
    public List<org.openqa.selenium.Alert> elementList() {
        return GermaniumApi.<org.openqa.selenium.Alert>resolveS(this)
                .elementList();
    }

    @Override
    public boolean exists() {
        return GermaniumApi.<org.openqa.selenium.Alert>S(this).exists();
    }

    @Override
    public boolean notExists() {
        return GermaniumApi.<org.openqa.selenium.Alert>S(this).notExists();
    }

    @Override
    public String text() {
        return GermaniumApi.<org.openqa.selenium.Alert>S(this).text();
    }

    @Override
    public org.openqa.selenium.Alert get() {
        return element();
    }

    public void accept() {
        org.openqa.selenium.Alert alert = this.element();

        waitFor(() -> {
            alert.accept();
            return true;
        });

        getGermanium().setLastAlert(null);
        waitFor(this::notExists);
    }

    public void dismiss() {
        org.openqa.selenium.Alert alert = this.element();

        waitFor(() -> {
            alert.dismiss();
            return true;
        });

        getGermanium().setLastAlert(null);
        waitFor(this::notExists);
    }

    public void sendKeys(String keys) {
        this.element().sendKeys(keys);
    }
}
