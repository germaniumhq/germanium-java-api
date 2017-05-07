package com.germaniumhq.germanium.all.operations.wdbuilder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.germaniumhq.germanium.all.operations.OpenBrowser.throwUnknownBrowser;

/**
 * Constructs a WebDriver from a String that was specified like this:
 *
 * ---
 * browser_name:http://path/to/webdriver
 * ---
 */
public class RemoteWebDriverUrlOnlyBuilder {
    public static final Pattern REMOTE_ADDRESS = Pattern.compile("^(\\w+?):(.*?)$");

    /**
     * Constructs the WebDriver instance from the match of the browser.
     * For this expression:
     *
     * ---
     * browser_name:http://path/to/webdriver
     * ---
     *
     * The match should have two groups, one for the browser name, and the other for the URL.
     *
     * @param remoteMatch
     * @return
     */
    public static WebDriver webDriver(Matcher remoteMatch) {
        DesiredCapabilities remoteCapabilites;
        String remoteBrowser = remoteMatch.group(1);

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
            return throwUnknownBrowser(remoteBrowser, remoteMatch.group(0));
        }

        String remoteUrlString = remoteMatch.group(2);

        return new RemoteWebDriver(new HttpCommandExecutor(getUrl(remoteUrlString)), remoteCapabilites);
    }

    private static URL getUrl(String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Unable to parse URL for browser: " + urlString, e);
        }
    }

}
