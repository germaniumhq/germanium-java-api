package com.germaniumhq.germanium.all.operations.actions;

import com.germaniumhq.germanium.all.GermaniumApi;
import org.openqa.selenium.WebElement;

public class Select {
    public static void select(Object selector, String ... text) {
        WebElement element = GermaniumApi.getGermanium().<WebElement>S(selector).element();
        org.openqa.selenium.support.ui.Select s = new org.openqa.selenium.support.ui.Select(element);

        for (String singleText: text) {
            s.selectByVisibleText(singleText);
        }
    }

    public static void selectByIndex(Object selector, int ... index) {
        WebElement element = GermaniumApi.getGermanium().<WebElement>S(selector).element();
        org.openqa.selenium.support.ui.Select s = new org.openqa.selenium.support.ui.Select(element);

        for (int singleIndex: index) {
            s.selectByIndex(singleIndex);
        }
    }

    public static void selectByValue(Object selector, String ... value) {
        WebElement element = GermaniumApi.getGermanium().<WebElement>S(selector).element();
        org.openqa.selenium.support.ui.Select s = new org.openqa.selenium.support.ui.Select(element);

        for (String singleValue: value) {
            s.selectByValue(singleValue);
        }
    }
}
