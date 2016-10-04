package com.germaniumhq.germanium.selectors;

import java.util.Arrays;
import java.util.Collection;

public class Button extends AbstractSelector {
    private String searchText;
    private String text;
    private String name;

    public Button searchText(String searchText) {
        if (text != null) {
            throw new IllegalArgumentException("You can't have both a searched text and an exact text match");
        }

        this.searchText = searchText;
        return this;
    }

    public Button text(String text) {
        if (searchText != null) {
            throw new IllegalArgumentException("You can't have both a searched text and an exact text match");
        }

        this.text = text;
        return this;
    }

    public Button name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Collection<String> getSelectors() {
        StringBuilder buttonSelector = new StringBuilder("xpath:.//button");
        StringBuilder inputSelector = new StringBuilder("xpath:.//input[@type='button' or @type='submit']");

        if (name != null) {
            buttonSelector.append(String.format("[@name='%s']", name));
            inputSelector.append(String.format("[@name='%s']", name));
        }

        if (text != null) {
            buttonSelector.append(String.format("[string(.)='%s']", text));
            inputSelector.append(String.format("[@value='%s']", text));
        }

        if (searchText != null) {
            buttonSelector.append(String.format("[contains(normalize-space(string()), \"%s\")]", searchText));
            inputSelector.append(String.format("[contains(@value, \"%s\")]", searchText));
        }

        return Arrays.asList(
                inputSelector.toString(),
                buttonSelector.toString()
        );
    }
}
