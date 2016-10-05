package com.germaniumhq.germanium.selectors;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class InsideFilterSelector extends AbstractSelector {
    private final AbstractSelector parentSelector;

    private List<Object> insideFilters = new ArrayList<>();
    private List<Object> containingFilters = new ArrayList<>();
    private List<Object> containingAllFilters = new ArrayList<>();

    private boolean withoutChildrenElements = false;


    public InsideFilterSelector(AbstractSelector selector) {
        this.parentSelector = selector;
    }

    @Override
    public Collection<String> getSelectors() {
        throw new IllegalArgumentException("Filtering selector");
    }

    public AbstractSelector inside(AbstractSelector ... selectors) {
        this.insideFilters.addAll(Arrays.asList(selectors) );
        
        return this;
    }

    public AbstractSelector inside(WebElement... elements) {
        this.insideFilters.addAll(Arrays.asList(elements));

        return this;
    }

    public AbstractSelector inside(String ... selectors) {
        this.insideFilters.addAll(Arrays.asList(selectors));

        return this;
    }

    public AbstractSelector containing(AbstractSelector ... selectors) {
        this.containingFilters.addAll(Arrays.asList(selectors) );

        return this;
    }

    public AbstractSelector containing(WebElement ... elements) {
        this.containingFilters.addAll(Arrays.asList(elements));

        return this;
    }

    public AbstractSelector containing(String ... elements) {
        this.containingFilters.addAll(Arrays.asList(elements));

        return this;
    }

    public AbstractSelector containingAll(AbstractSelector ... selectors) {
        this.containingAllFilters.addAll(Arrays.asList(selectors) );

        return this;
    }

    public AbstractSelector containingAll(WebElement ... elements) {
        this.containingAllFilters.addAll(Arrays.asList(elements));

        return this;
    }

    public AbstractSelector containingAll(String ... elements) {
        this.containingAllFilters.addAll(Arrays.asList(elements));

        return this;
    }

    public AbstractSelector withoutChildren() {
        this.withoutChildrenElements = true;

        return this;
    }

    public AbstractSelector getParentSelector() {
        return parentSelector;
    }

    public List<Object> getInsideFilters() {
        return Collections.unmodifiableList(insideFilters);
    }

    public List<Object> getContainingFilters() {
        return Collections.unmodifiableList(containingFilters);
    }

    public List<Object> getContainingAllFilters() {
        return Collections.unmodifiableList(containingAllFilters);
    }

    public boolean isWithoutChildrenElements() {
        return withoutChildrenElements;
    }
}
