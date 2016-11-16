package com.germaniumhq.germanium.selectors;

import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.locators.Locator;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.List;

public abstract class AbstractSelector implements Locator<WebElement>, Selector<WebElement> {
    public abstract Collection<String> getSelectors();

    public AbstractSelector leftOf(AbstractSelector...selectors) {
        return new PositionalFilterSelector(this)
                .leftOf(selectors);
    }

    public AbstractSelector leftOf(WebElement...elements) {
        return new PositionalFilterSelector(this)
                .leftOf(elements);
    }

    public AbstractSelector leftOf(String...selectors) {
        return new PositionalFilterSelector(this)
                .leftOf(selectors);
    }

    public AbstractSelector rightOf(AbstractSelector...selectors) {
        return new PositionalFilterSelector(this)
                .rightOf(selectors);
    }

    public AbstractSelector rightOf(WebElement...elements) {
        return new PositionalFilterSelector(this)
                .rightOf(elements);
    }

    public AbstractSelector rightOf(String...selectors) {
        return new PositionalFilterSelector(this)
                .rightOf(selectors);
    }

    public AbstractSelector above(AbstractSelector...selectors) {
        return new PositionalFilterSelector(this)
                .above(selectors);
    }

    public AbstractSelector above(WebElement...elements) {
        return new PositionalFilterSelector(this)
                .above(elements);
    }

    public AbstractSelector above(String...selectors) {
        return new PositionalFilterSelector(this)
                .above(selectors);
    }

    public AbstractSelector below(AbstractSelector...selectors) {
        return new PositionalFilterSelector(this)
                .below(selectors);
    }

    public AbstractSelector below(WebElement...elements) {
        return new PositionalFilterSelector(this)
                .below(elements);
    }

    public AbstractSelector below(String...selectors) {
        return new PositionalFilterSelector(this)
                .below(selectors);
    }

    public AbstractSelector inside(AbstractSelector...selectors) {
        return new InsideFilterSelector(this)
                .inside(selectors);
    }

    public AbstractSelector inside(WebElement...elements) {
        return new InsideFilterSelector(this)
                .inside(elements);
    }

    public AbstractSelector inside(String...selectors) {
        return new InsideFilterSelector(this)
                .inside(selectors);
    }

    public AbstractSelector containing(AbstractSelector...selectors) {
        return new InsideFilterSelector(this)
                .containing(selectors);
    }

    public AbstractSelector containing(WebElement...elements) {
        return new InsideFilterSelector(this)
                .containing(elements);
    }

    public AbstractSelector containing(String...selectors) {
        return new InsideFilterSelector(this)
                .containing(selectors);
    }

    public AbstractSelector containingAll(AbstractSelector...selectors) {
        return new InsideFilterSelector(this)
                .containingAll(selectors);
    }

    public AbstractSelector containingAll(WebElement...elements) {
        return new InsideFilterSelector(this)
                .containingAll(elements);
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
        return GermaniumApi.getGermanium().<WebElement>S(this).element();
    }

    @Override
    public WebElement element(Visibility visibility) {
        return GermaniumApi.getGermanium().<WebElement>S(this).element(visibility);
    }

    @Override
    public WebElement elementList(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("An index in an array must be positive.");
        }

        List<WebElement> foundElements = this.elementList(Visibility.ONLY_VISIBLE);

        if (index >= foundElements.size()) {
            throw new IllegalArgumentException(String.format(
                    "Element with index %d was not found. The search returned " +
                    "only %d elements.", index, foundElements.size()
            ));
        }

        return foundElements.get(index);
    }

    @Override
    public List<WebElement> elementList() {
        return GermaniumApi.getGermanium().<WebElement>S(this).elementList();
    }

    @Override
    public List<WebElement> elementList(Visibility visibility) {
        return GermaniumApi.getGermanium().<WebElement>S(this).elementList(visibility);
    }

    @Override
    public boolean exists() {
        return GermaniumApi.getGermanium().<WebElement>S(this).exists();
    }

    @Override
    public boolean exists(Visibility visibility) {
        return GermaniumApi.getGermanium().<WebElement>S(this).exists(visibility);
    }

    @Override
    public boolean notExists() {
        return GermaniumApi.getGermanium().S(this).notExists();
    }

    @Override
    public boolean notExists(Visibility visibility) {
        return GermaniumApi.getGermanium().S(this).notExists(visibility);
    }

    @Override
    public String text() {
        return GermaniumApi.getGermanium().S(this).text();
    }

    @Override
    public String text(Visibility visibility) {
        return GermaniumApi.getGermanium().S(this).text(visibility);
    }

    @Override
    public WebElement get() {
        return element();
    }
}
