package com.germaniumhq.germanium;

/**
 * A JavaScript exception that was caught by Germanium.
 */
public class JavaScriptException extends RuntimeException {
    private Object name;
    private Object message;

    public JavaScriptException(Object name, Object message) {
        this.name = name;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return String.format("JavaScriptException: %s - %s", name ,message);
    }
}
