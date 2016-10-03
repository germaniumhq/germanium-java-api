package com.germaniumhq.germanium.selectors;

import java.util.Collection;
import java.util.Collections;

public class JsSelector extends AbstractSelector {
    private final String selector;

    public JsSelector(String code) {
        this.selector = "js:" + code;
    }

    @Override
    public Collection<String> getSelectors() {
        return Collections.singletonList(selector);
    }
}
