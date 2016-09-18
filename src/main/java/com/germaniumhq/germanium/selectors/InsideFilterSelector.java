package com.germaniumhq.germanium.selectors;

import java.util.*;

public class InsideFilterSelector extends AbstractSelector {
    private final AbstractSelector parentSelector;

    private List<AbstractSelector> insideFilters = new ArrayList<>();
    private List<AbstractSelector> containingFilters = new ArrayList<>();
    private List<AbstractSelector> containingAllFilters = new ArrayList<>();

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

    public AbstractSelector containing(AbstractSelector ... selectors) {
        this.containingFilters.addAll(Arrays.asList(selectors) );

        return this;
    }

    public AbstractSelector containingAll(AbstractSelector ... selectors) {
        this.containingAllFilters.addAll(Arrays.asList(selectors) );

        return this;
    }

    public AbstractSelector withoutChildren() {
        this.withoutChildrenElements = true;

        return this;
    }

    public AbstractSelector getParentSelector() {
        return parentSelector;
    }

    public List<AbstractSelector> getInsideFilters() {
        return Collections.unmodifiableList(insideFilters);
    }

    public List<AbstractSelector> getContainingFilters() {
        return Collections.unmodifiableList(containingFilters);
    }

    public List<AbstractSelector> getContainingAllFilters() {
        return Collections.unmodifiableList(containingAllFilters);
    }

    public boolean isWithoutChildrenElements() {
        return withoutChildrenElements;
    }
}
