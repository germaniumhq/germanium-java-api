package com.germaniumhq.germanium.selectors;

import java.util.*;

public class PositionalFilterSelector extends AbstractSelector {
    private final AbstractSelector parentSelector;

    private List<AbstractSelector> leftOfFilters = new ArrayList<>();
    private List<AbstractSelector> rightOfFilters = new ArrayList<>();
    private List<AbstractSelector> aboveFilters = new ArrayList<>();
    private List<AbstractSelector> belowFilters = new ArrayList<>();

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
    public AbstractSelector rightOf(AbstractSelector... selectors) {
        rightOfFilters.addAll(Arrays.asList(selectors));

        return this;
    }

    @Override
    public AbstractSelector above(AbstractSelector... selectors) {
        aboveFilters.addAll(Arrays.asList(selectors));

        return this;
    }

    @Override
    public AbstractSelector below(AbstractSelector... selectors) {
        belowFilters.addAll(Arrays.asList(selectors));

        return this;
    }

    public AbstractSelector getParentSelector() {
        return parentSelector;
    }

    public List<AbstractSelector> getLeftOfFilters() {
        return Collections.unmodifiableList(leftOfFilters);
    }

    public List<AbstractSelector> getRightOfFilters() {
        return Collections.unmodifiableList(rightOfFilters);
    }

    public List<AbstractSelector> getAboveFilters() {
        return Collections.unmodifiableList(aboveFilters);
    }

    public List<AbstractSelector> getBelowFilters() {
        return Collections.unmodifiableList(belowFilters);
    }
}
