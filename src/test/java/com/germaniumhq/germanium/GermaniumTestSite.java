package com.germaniumhq.germanium;

import cucumber.api.java.After;
import cucumber.api.java.Before;

import static spark.Spark.*;

public class GermaniumTestSite {
    @Before
    public static void initialize() {
        port(8000);
        staticFiles.externalLocation("src/test/resources/");
        init();
    }

    @After
    public static void shutdown() {
        stop();
    }
}
