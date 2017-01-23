package com.germaniumhq.germanium.selectors;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PositionalFilterSelector extends AbstractSelector {
    private final AbstractSelector parentSelector;

    private List<Object> leftOfFilters = new ArrayList<>();
    private List<Object> rightOfFilters = new ArrayList<>();
    private List<Object> aboveFilters = new ArrayList<>();
    private List<Object> belowFilters = new ArrayList<>();

    public PositionalFilterSelector(AbstractSelector parentSelector) {
        this.parentSelector = parentSelector;
    }

    @Override
    public Collection<String> getSelectors() {
        throw new IllegalArgumentException("This is a positional filter.");
    }

    @Override
    public AbstractSelector leftOf(AbstractSelector... selectors) {
        leftOfFilters.addAll(Arrays.asList(selectors));

        return this;
    }

    @Override
    public AbstractSelector leftOf(WebElement ... elements) {
        leftOfFilters.addAll(Arrays.asList(elements));

        return this;
    }

    @Override
    public AbstractSelector leftOf(String ... elements) {
        leftOfFilters.addAll(Arrays.asList(elements));

        return this;
    }

    @Override
    public AbstractSelector rightOf(AbstractSelector... selectors) {
        rightOfFilters.addAll(Arrays.asList(selectors));

        return this;
    }

    @Override
    public AbstractSelector rightOf(WebElement ... elements) {
        rightOfFilters.addAll(Arrays.asList(elements));

        return this;
    }

    @Override
    public AbstractSelector rightOf(String ... elements) {
        rightOfFilters.addAll(Arrays.asList(elements));

        return this;
    }

    @Override
    public AbstractSelector above(AbstractSelector... selectors) {
        aboveFilters.addAll(Arrays.asList(selectors));

        return this;
    }

    @Override
    public AbstractSelector above(WebElement ... elements) {
        aboveFilters.addAll(Arrays.asList(elements));

        return this;
    }

    @Override
    public AbstractSelector above(String ... elements) {
        aboveFilters.addAll(Arrays.asList(elements));

        return this;
    }

    @Override
    public AbstractSelector below(AbstractSelector... selectors) {
        belowFilters.addAll(Arrays.asList(selectors));

        return this;
    }

    @Override
    public AbstractSelector below(WebElement ... elements) {
        belowFilters.addAll(Arrays.asList(elements));

        return this;
    }

    @Override
    public AbstractSelector below(String ... elements) {
        belowFilters.addAll(Arrays.asList(elements));

        return this;
    }

    public AbstractSelector getParentSelector() {
        return parentSelector;
    }

    public List<Object> getLeftOfFilters() {
        return Collections.unmodifiableList(leftOfFilters);
    }

    public List<Object> getRightOfFilters() {
        return Collections.unmodifiableList(rightOfFilters);
    }

    public List<Object> getAboveFilters() {
        return Collections.unmodifiableList(aboveFilters);
    }

    public List<Object> getBelowFilters() {
        return Collections.unmodifiableList(belowFilters);
    }
}
