package com.germaniumhq.germanium.selectors;

public class Link extends Element {
    public Link() {
        super("a");
    }

    public Link(String searchedText) {
        super("a");

        this.containsText(searchedText);
    }

    public Link hrefContains(String searchedHref) {
        this.containsAttribute("href", searchedHref);

        return this;
    }

    public Link href(String href) {
        this.exactAttribute("href", href);
        return this;
    }
}
