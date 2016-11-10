package com.germaniumhq.germanium.selectors;

public class InputFile extends Element {
    public InputFile(String name) {
        super("input");

        this.name(name);
    }

    public InputFile() {
        super("input");

        this.extraXPath("[@type='file']");
    }

    public InputFile name(String name) {
        this.exactAttribute("name", name);
        return this;
    }
}
