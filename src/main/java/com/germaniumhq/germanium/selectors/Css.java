package com.germaniumhq.germanium.selectors;

import java.util.Collection;
import java.util.Collections;

public class Css extends AbstractSelector {
    private String selector;

    public Css(String selector) {
        this.selector = selector;
    }

    @Override
    public Collection<String> getSelectors() {
        return Collections.singleton("css:" + selector);
    }
}
