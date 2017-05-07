package com.germaniumhq.germanium.all.operations.wdbuilder;

import com.germaniumhq.germanium.all.operations.wdbuilder.query.BrowserSpecification;
import com.germaniumhq.germanium.all.operations.wdbuilder.query.UriParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static com.germaniumhq.germanium.all.operations.OpenBrowser.throwUnknownBrowser;

public class RemoteWebDriverQueryBuilder {
    /**
     * Constructs the WebDriver instance from the match of the browser.
     * For this expression:
     *
     * ---
     * browser_name?wdurl=http://path/to/webdriver&version=11
     * ---
     *
     * The match should have two groups, one for the browser name, and the other for the query parameters.
     *
     * @param browserString
     * @return
     */
    public static WebDriver webDriver(String browserString) {
        DesiredCapabilities remoteCapabilites;
        BrowserSpecification browserSpecification = new UriParser().parse(browserString);

        String remoteBrowser = browserSpecification.getBrowserName();

        if ("firefox".equalsIgnoreCase(remoteBrowser) || "ff".equalsIgnoreCase(remoteBrowser)) {
            remoteCapabilites = DesiredCapabilities.firefox();
            remoteCapabilites.setCapability("unexpectedAlertBehaviour", "ignore");
        } else if ("chrome".equalsIgnoreCase(remoteBrowser)) {
            remoteCapabilites = DesiredCapabilities.chrome();
        } else if ("ie".equalsIgnoreCase(remoteBrowser)) {
            remoteCapabilites = DesiredCapabilities.internetExplorer();
            remoteCapabilites.setCapability("requireWindowFocus", true);
        } else if ("edge".equalsIgnoreCase(remoteBrowser)) {
            remoteCapabilites = DesiredCapabilities.edge();
        } else {
            return throwUnknownBrowser(remoteBrowser, browserString);
        }

        for (Map.Entry<String, String> capabiltity: browserSpecification.getDesiredCapabilities().entrySet()) {
            remoteCapabilites.setCapability(capabiltity.getKey(), capabiltity.getValue());
        }

        String remoteUrlString = browserSpecification.getUrl();

        return new RemoteWebDriver(new HttpCommandExecutor(getUrl(remoteUrlString)), remoteCapabilites);
    }

    private static URL getUrl(String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Unable to parse URL for browser: " + urlString, e);
        }
    }

    public static boolean matches(String browser) {
        return UriParser.REMOTE_QUERY_PATTERN.matcher(browser).matches();
    }
}
