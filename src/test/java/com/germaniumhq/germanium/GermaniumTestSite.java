package com.germaniumhq.germanium;

import com.germaniumhq.germanium.all.GermaniumApi;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.util.List;

import static spark.Spark.init;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class GermaniumTestSite {
    public static boolean serverRunning;

    @Before
    public static void initialize() {
        if (!serverRunning) {
            serverRunning = true;
            port(8000);
            staticFiles.externalLocation("src/test/resources/");

            post("/upload", (request, response) -> {
                // super small files are stored in memory, so we don't
                // neet do configure a temporary folder.
                DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
                String body = "<html><body>Uploaded '%s'.</body></html>";

                List<FileItem> fileUpload = new ServletFileUpload(diskFileItemFactory)
                        .parseRequest(request.raw());

                return String.format(body, fileUpload.get(0).getName());
            });

            init();
        }
    }

    @After
    public static void shutdown() {
//        stop();
        GermaniumApi.closeBrowser();
    }
}
