package com.germaniumhq.germanium.selectors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputText extends Element {
    public InputText() {
        this(null);
    }

    public InputText(String placeHolder) {
        super("input");

        List<String> items = Arrays.<String>asList(
                "date",
                "datetime",
                "datetime-local",
                "email",
                "month",
                "number",
                "password",
                "search",
                "tel",
                "text",
                "time",
                "url",
                "week"
        );

        String typeCondition = items.stream()
                .map(it -> String.format("@type='%s' or", it))
                .collect(Collectors.joining(" "));

        String extraXPath = String.format("[%s not(@type)]", typeCondition);

        if (placeHolder != null) {
            extraXPath += String.format("[@placeholder = '%s']", placeHolder);
        }

        this.extraXPath(extraXPath);
    }
}
