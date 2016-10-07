package com.germaniumhq.germanium.selectors;

public class Image extends Element {
    public Image(String alt) {
        super("img");
        this.alt(alt);
    }

    public Image() {
        super("img");
    }

    public Image alt(String alt) {
        return (Image) this.exactAttribute("alt", alt);
    }
}
