package com.germaniumhq.germanium.selectors;

import java.util.Collection;

public class Text extends AbstractSelector {
    private String searchedText;
    private boolean exactMatch;
    private boolean trimText;

    public Text(String text) {
        this.searchedText = text;
    }

    public Text exactMatch() {
        this.exactMatch = true;
        return this;
    }

    public Text trimText() {
        this.trimText = true;
        return this;
    }

    @Override
    public Collection<String> getSelectors() {
        throw new IllegalStateException(
                "Not implemented. A locator should be constructed " +
                "for it. If you just called Text(...) it's a bug in " +
                "Germanium, and please report it.");
    }

    public String getSearchedText() {
        return searchedText;
    }

    public boolean isExactMatch() {
        return exactMatch;
    }

    public boolean isTrimText() {
        return trimText;
    }
}
