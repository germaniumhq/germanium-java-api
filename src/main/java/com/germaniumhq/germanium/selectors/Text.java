package com.germaniumhq.germanium.selectors;

import com.germaniumhq.germanium.impl.ScriptLoader;

import java.util.Collection;
import java.util.Collections;

public class Text extends AbstractSelector {
    private final String selector;

    public Text(String text) {
        String template = ScriptLoader.getScript("/germanium/selectors/text.js.template");
        this.selector = String.format(template, text.replace("\"", "\\\""));
    }

    @Override
    public Collection<String> getSelectors() {
        return Collections.singleton(selector);
    }
}
