package com.germaniumhq.germanium;

import com.germaniumhq.germanium.all.GermaniumApi;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.resource.FileResourceManager;
import io.undertow.server.handlers.resource.ResourceHandler;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GermaniumTestSite {
    public static final int DEFAULT_PORT = 8000;
    public static boolean serverRunning;

    @Before
    public static void initialize() {
        if (!serverRunning) {
            serverRunning = true;
            Undertow server = Undertow.builder()
                    .addHttpListener(readPort(), "0.0.0.0")
                .setHandler(getPathHandler("src/test/resources/"))
                .build();

            server.start();
        }
    }

    private static HttpHandler getPathHandler(String path) {
        return new PathHandler()
                .addExactPath("/upload", new HttpHandler() {
                    @Override
                    public void handleRequest(HttpServerExchange exchange) throws Exception {
                        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
                        String body = "<html><body>Uploaded '%s'.</body></html>";

                        exchange.getRequestReceiver().receiveFullBytes((e, bytes) -> {
                            List<FileItem> fileUpload = parseRequest(diskFileItemFactory, e, bytes);

                            e.getResponseSender().send(String.format(body, fileUpload.get(0).getName()), Charset.forName("utf-8"));
                        });

                    }
                })
                .addPrefixPath("/", new ResourceHandler(new FileResourceManager(new File(path), 0, true, new String[]{})));
    }

    private static List<FileItem> parseRequest(DiskFileItemFactory diskFileItemFactory, final HttpServerExchange e, final byte[] bytes) {
        try {
            return new ServletFileUpload(diskFileItemFactory)
                    .parseRequest(new RequestContext() {
                        @Override
                        public String getCharacterEncoding() {
                            return null;
                        }

                        @Override
                        public String getContentType() {
                            return e.getRequestHeaders().get("ContentType").get(0);
                        }

                        @Override
                        public int getContentLength() {
                            return (int) e.getRequestContentLength();
                        }

                        @Override
                        public InputStream getInputStream() throws IOException {
                            return new ByteArrayInputStream(bytes);
                        }
                    });
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to parse upload", ex);
        }
    }

    /**
     * Read the port directly from the TEST_HOST if it's set.
     * @return
     */
    private static int readPort() {
        String testHost = System.getenv("TEST_HOST");

        if (testHost == null) {
            return DEFAULT_PORT;
        }

        Matcher matcher = Pattern.compile("^.*?:(\\d+)$").matcher(testHost);

        if (!matcher.matches()) {
            return DEFAULT_PORT;
        }

        return Integer.parseInt(matcher.group(1));
    }

    @After
    public static void shutdown() {
        if (System.getenv("TEST_REUSE_BROWSER") != null) {
            return;
        }

        GermaniumApi.closeBrowser();
    }
}
