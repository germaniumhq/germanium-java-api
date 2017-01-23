package com.germaniumhq.germanium.selectors;

public class Input extends Element {
    public Input(String name) {
        super("input");
        this.name(name);
    }

    public Input() {
        super("input");
    }

    public Input name(String name) {
        this.exactAttribute("name", name);
        return this;
    }
}
