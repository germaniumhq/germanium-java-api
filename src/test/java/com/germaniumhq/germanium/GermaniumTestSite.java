package com.germaniumhq.germanium;

import com.germaniumhq.germanium.all.GermaniumApi;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import static spark.Spark.*;

public class GermaniumTestSite {
    public static boolean serverRunning;

    @Before
    public static void initialize() {
        if (!serverRunning) {
            serverRunning = true;
            port(8000);
            staticFiles.externalLocation("src/test/resources/");
            init();
        }
    }

    @After
    public static void shutdown() {
//        stop();
        GermaniumApi.closeBrowser();
    }
}
