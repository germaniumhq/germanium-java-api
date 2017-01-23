package com.germaniumhq.germanium.impl;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ScriptLoaderTest {
    @Test
    public void testLoading() {
        assertNotNull(ScriptLoader.getScript("/germanium/test.js"));
    }
}
