package com.germaniumhq.germanium.selectors;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.impl.BrowserContentProvider;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.List;

public abstract class AbstractSelector implements BrowserContentProvider<WebElement>, Selector<WebElement> {
    public abstract Collection<String> getSelectors();

    public AbstractSelector leftOf(AbstractSelector...selectors) {
        return new PositionalFilterSelector(this)
                .leftOf(selectors);
    }

    public AbstractSelector rightOf(AbstractSelector...selectors) {
        return new PositionalFilterSelector(this)
                .rightOf(selectors);
    }

    public AbstractSelector above(AbstractSelector...selectors) {
        return new PositionalFilterSelector(this)
                .above(selectors);
    }

    public AbstractSelector below(AbstractSelector...selectors) {
        return new PositionalFilterSelector(this)
                .below(selectors);
    }

    public AbstractSelector inside(AbstractSelector...selectors) {
        return new InsideFilterSelector(this)
                .inside(selectors);
    }

    public AbstractSelector inside(WebElement...selectors) {
        return new InsideFilterSelector(this)
                .inside(selectors);
    }

    public AbstractSelector inside(String...selectors) {
        return new InsideFilterSelector(this)
                .inside(selectors);
    }

    public AbstractSelector containing(AbstractSelector...selectors) {
        return new InsideFilterSelector(this)
                .containing(selectors);
    }

    public AbstractSelector containing(WebElement...selectors) {
        return new InsideFilterSelector(this)
                .containing(selectors);
    }

    public AbstractSelector containing(String...selectors) {
        return new InsideFilterSelector(this)
                .containing(selectors);
    }

    public AbstractSelector containingAll(AbstractSelector...selectors) {
        return new InsideFilterSelector(this)
                .containingAll(selectors);
    }

    public AbstractSelector containingAll(WebElement...selectors) {
        return new InsideFilterSelector(this)
                .containingAll(selectors);
    }

    public AbstractSelector containingAll(String...selectors) {
        return new InsideFilterSelector(this)
                .containingAll(selectors);
    }

    public AbstractSelector withoutChildren() {
        return new InsideFilterSelector(this)
                .withoutChildren();
    }

    @Override
    public WebElement element() {
        return this.element(Visibility.ONLY_VISIBLE, GermaniumApi.getGermanium());
    }

    @Override
    public WebElement element(Visibility visibility) {
        return this.element(visibility, GermaniumApi.getGermanium());
    }

    @Override
    public WebElement element(GermaniumDriver germanium) {
        return this.element(Visibility.ONLY_VISIBLE, germanium);
    }

    @Override
    public WebElement element(Visibility visibility, GermaniumDriver germaniumDriver) {
        return (WebElement) germaniumDriver.S(this).element(visibility);
    }

    @Override
    public List<WebElement> elementList() {
        return this.elementList(Visibility.ONLY_VISIBLE, GermaniumApi.getGermanium());
    }

    @Override
    public List<WebElement> elementList(GermaniumDriver germanium) {
        return this.elementList(Visibility.ONLY_VISIBLE, germanium);
    }

    @Override
    public List<WebElement> elementList(Visibility visibility) {
        return this.elementList(visibility, GermaniumApi.getGermanium());
    }

    @Override
    public List<WebElement> elementList(Visibility visibility, GermaniumDriver germaniumDriver) {
        return germaniumDriver.<WebElement>S(this).elementList(visibility);
    }

    @Override
    public boolean exists() {
        return this.exists(Visibility.ONLY_VISIBLE, GermaniumApi.getGermanium());
    }

    @Override
    public boolean exists(Visibility visibility) {
        return this.exists(visibility, GermaniumApi.getGermanium());
    }

    @Override
    public boolean exists(GermaniumDriver germanium) {
        return this.exists(Visibility.ONLY_VISIBLE, germanium);
    }

    @Override
    public boolean exists(Visibility visibility, GermaniumDriver germaniumDriver) {
        return germaniumDriver.S(this).exists(visibility);
    }

    @Override
    public boolean notExists() {
        return this.notExists(Visibility.ONLY_VISIBLE, GermaniumApi.getGermanium());
    }

    @Override
    public boolean notExists(Visibility visibility) {
        return this.notExists(visibility, GermaniumApi.getGermanium());
    }

    @Override
    public boolean notExists(GermaniumDriver germanium) {
        return this.notExists(Visibility.ONLY_VISIBLE, germanium);
    }

    @Override
    public boolean notExists(Visibility visibility, GermaniumDriver germaniumDriver) {
        return germaniumDriver.S(this).notExists(visibility);
    }

    @Override
    public String text() {
        return this.text(Visibility.ONLY_VISIBLE, GermaniumApi.getGermanium());
    }

    @Override
    public String text(Visibility visibility) {
        return this.text(visibility, GermaniumApi.getGermanium());
    }

    @Override
    public String text(GermaniumDriver germanium) {
        return this.text(Visibility.ONLY_VISIBLE, germanium);
    }

    @Override
    public String text(Visibility visibility, GermaniumDriver germaniumDriver) {
        return germaniumDriver.S(this).text(visibility);
    }

    @Override
    public List<WebElement> get() {
        return elementList();
    }
}
