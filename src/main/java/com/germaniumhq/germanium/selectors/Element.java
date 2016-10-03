package com.germaniumhq.germanium.selectors;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Element extends AbstractSelector {
    private String tagName;

    private Integer index;
    private String id;
    private String exactText;
    private String containsText;
    private Set<String> cssClasses = new HashSet<>();
    private Map<String, String> exactAttributes = new HashMap<>();
    private Map<String, String> containsAttributes = new HashMap<>();
    private String extraXPath = "";

    public Element(String tagName) {
        this.tagName = tagName;
    }

    public Element index(int index) {
        this.index = index;
        return this;
    }

    public Element id(String id) {
        this.id = id;
        return this;
    }

    public Element exactText(String exactText) {
        this.exactText = exactText;
        return this;
    }

    public Element containsText(String containsText) {
        this.containsText = containsText;
        return this;
    }

    public Element cssClasses(String ... cssClasses) {
        for (String cssClass : cssClasses) {
            for (String css: cssClass.split("\\s+")) {
                if (css.isEmpty()) {
                    continue;
                }

                this.cssClasses.add(css);
            }
        }

        return this;
    }

    public Element exactAttributes(Map<String, String> exactAttributes) {
        this.exactAttributes.putAll(exactAttributes);
        return this;
    }

    public Element containsAttributes(Map<String, String> containsAttributes) {
        this.containsAttributes.putAll(containsAttributes);
        return this;
    }

    public Element extraXPath(String ... extraXPath) {
        for (String xpath : extraXPath) {
            this.exactText += xpath;
        }

        return this;
    }


    public Element exactAttribute(String attributeName, String attributeValue) {
        this.exactAttributes.put(attributeName, attributeValue);
        return this;
    }

    public Element containsAttribute(String attributeName, String attributeValue) {
        this.containsAttributes.put(attributeName, attributeValue);
        return this;
    }

    @Override
    public Collection<String> getSelectors() {
        if (this.id != null && !this.id.isEmpty()) {
            exactAttributes.put("id", id);
        }

        if (this.exactText != null && this.containsText != null) {
            throw new IllegalStateException("Having the exact text to be matched, " +
                    "and a partial text to be searched is not supported.");
        }

        StringBuilder xpathLocator = new StringBuilder(".//").append(tagName);

        if (containsText != null) {
            xpathLocator.append(String.format("[contains(normalize-space(string()), '%s')]", containsText));
        }

        if (exactText != null) {
            xpathLocator.append(String.format("[string() = '%s']", exactText));
        }

        for (String cssClass : cssClasses) {
            xpathLocator.append(String.format("[contains(concat(\" \", @class, \" \"), \" %s \")]", cssClass));
        }

        for (Map.Entry<String, String> exactAttribute : exactAttributes.entrySet()) {
            xpathLocator.append(String.format("[@%s = \"%s\"]",
                    exactAttribute.getKey(),
                    exactAttribute.getValue()));
        }

        for (Map.Entry<String, String> containsAttribute : containsAttributes.entrySet()) {
            xpathLocator.append(String.format("[contains(normalize-space(@%s), '%s')]",
                    containsAttribute.getKey(),
                    containsAttribute.getValue()));
        }

        if (extraXPath != null) {
            xpathLocator.append(extraXPath);
        }

        if (index != null) {
            xpathLocator.insert(0, "(")
                    .append(")[")
                    .append(index)
                    .append("]");
        }

        xpathLocator.insert(0, "xpath:");

        return Collections.singletonList(xpathLocator.toString());
    }
}
