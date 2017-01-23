package com.germaniumhq.germanium;

import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton class to provide Cucumber contexts.
 */
public class Context {
    private static Map<String, Object> map = new HashMap<>();

    @Before
    public static void beforeScenario() {
        map.clear();
    }

    public static <T> T get(String key) {
        return (T) map.get(key);
    }

    public static <T> void set(String key, T value) {
        map.put(key, value);
    }

    public static <T> void remove(String key) {
        map.remove(key);
    }
}
