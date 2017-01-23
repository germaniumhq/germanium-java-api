package com.germaniumhq.germanium.all.operations.actions;

import com.germaniumhq.germanium.all.GermaniumApi;
import org.openqa.selenium.WebElement;

public class Deselect {
    public static void deselect(Object selector, String ... text) {
        WebElement element = GermaniumApi.getGermanium().<WebElement>S(selector).element();
        org.openqa.selenium.support.ui.Select s = new org.openqa.selenium.support.ui.Select(element);

        if (text.length == 0) {
            s.deselectAll();
            return;
        }

        for (String singleText: text) {
            s.deselectByVisibleText(singleText);
        }
    }

    public static void deselectByIndex(Object selector, int ... index) {
        WebElement element = GermaniumApi.getGermanium().<WebElement>S(selector).element();
        org.openqa.selenium.support.ui.Select s = new org.openqa.selenium.support.ui.Select(element);

        if (index.length == 0) {
            s.deselectAll();
            return;
        }

        for (int singleIndex: index) {
            s.deselectByIndex(singleIndex);
        }
    }

    public static void deselectByValue(Object selector, String ... value) {
        WebElement element = GermaniumApi.getGermanium().<WebElement>S(selector).element();
        org.openqa.selenium.support.ui.Select s = new org.openqa.selenium.support.ui.Select(element);

        if (value.length == 0) {
            s.deselectAll();
            return;
        }

        for (String singleValue: value) {
            s.deselectByValue(singleValue);
        }
    }
}
