package com.germaniumhq.germanium.selectors;

public class CheckBox extends Input {
    public CheckBox() {
        this.exactAttribute("type", "checkbox");
    }

    public CheckBox(String name) {
        super(name);
        this.exactAttribute("type", "checkbox");
    }
}
