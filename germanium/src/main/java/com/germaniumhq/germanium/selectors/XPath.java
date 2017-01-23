package com.germaniumhq.germanium.selectors;

import java.util.Collection;
import java.util.Collections;

public class XPath extends AbstractSelector {
    private final String xpath;

    public XPath(String xpath) {
        this.xpath = "xpath:" + xpath;
    }

    @Override
    public Collection<String> getSelectors() {
        return Collections.singleton(xpath);
    }
}
