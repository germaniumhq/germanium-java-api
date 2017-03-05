package com.germaniumhq.germanium.all.operations.wdbuilder.query;


import java.util.LinkedHashMap;
import java.util.Map;

public class BrowserSpecification {
    private String browserName;
    private String url;
    private Map<String, String> desiredCapabilities = new LinkedHashMap<>();
    private Map<String, String> requiredCapabilities = new LinkedHashMap<>();

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getDesiredCapabilities() {
        return desiredCapabilities;
    }

    public void setDesiredCapabilities(Map<String, String> desiredCapabilities) {
        this.desiredCapabilities = desiredCapabilities;
    }

    public Map<String, String> getRequiredCapabilities() {
        return requiredCapabilities;
    }

    public void setRequiredCapabilities(Map<String, String> requiredCapabilities) {
        this.requiredCapabilities = requiredCapabilities;
    }
}
