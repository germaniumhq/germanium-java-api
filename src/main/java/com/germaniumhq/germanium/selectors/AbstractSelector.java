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

    public AbstractSelector containing(AbstractSelector...selectors) {
        return new InsideFilterSelector(this)
                .containing(selectors);
    }

    public AbstractSelector containingAll(AbstractSelector...selectors) {
        return new InsideFilterSelector(this)
                .containingAll(selectors);
    }

    public AbstractSelector withoutChildren() {
        return new InsideFilterSelector(this)
                .withoutChildren();
    }

    @Override
    public WebElement element() {
        return this.element(GermaniumApi.getGermanium());
    }

    @Override
    public WebElement element(GermaniumDriver germanium) {
        return GermaniumApi.S(this, germanium).element();
    }

    @Override
    public List<WebElement> elementList() {
        return this.elementList(GermaniumApi.getGermanium());
    }

    @Override
    public List<WebElement> elementList(GermaniumDriver germanium) {
        return GermaniumApi.S(this, germanium).elementList();
    }

    @Override
    public boolean exists() {
        return this.exists(GermaniumApi.getGermanium());
    }

    @Override
    public boolean exists(GermaniumDriver germanium) {
        return GermaniumApi.S(this, germanium).exists();
    }

    @Override
    public boolean notExists() {
        return this.notExists(GermaniumApi.getGermanium());
    }

    @Override
    public boolean notExists(GermaniumDriver germanium) {
        return GermaniumApi.S(this, germanium).notExists();
    }

    @Override
    public String text() {
        return this.text(GermaniumApi.getGermanium());
    }

    @Override
    public String text(GermaniumDriver germanium) {
        return GermaniumApi.S(this, germanium).text();
    }
}
