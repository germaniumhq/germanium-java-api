package com.germaniumhq.germanium.impl;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Loads a script from the classpath.
 */
public class ScriptLoader {
    private final static Logger log = Logger.getLogger(ScriptLoader.class);

    /**
     * Loads the given script from the classpath. This will
     * call ScriptLoader.class.getResourceAsStream, so you
     * need to use absolute paths to fetch the scripts.
     *
     * @param name
     * @return
     */
    public static String getScript(String name) {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                     ScriptLoader.class.getResourceAsStream(name),
                     "utf-8"
                ))) {

            return bufferedReader.lines().collect(Collectors.joining("\n"));

        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to read script: " + name, e);
        }
    }
}
