package com.germaniumhq.germanium.all.operations;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.IFrameSelector;
import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.all.operations.wdbuilder.LocalWebDriverBuilder;
import com.germaniumhq.germanium.all.operations.wdbuilder.RemoteWebDriverQueryBuilder;
import com.germaniumhq.germanium.all.operations.wdbuilder.RemoteWebDriverUrlOnlyBuilder;
import com.germaniumhq.germanium.iframe.DefaultIFrameSelector;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;

import static com.germaniumhq.germanium.all.operations.wdbuilder.RemoteWebDriverUrlOnlyBuilder.REMOTE_ADDRESS;

public class OpenBrowser {
    private String browser = "firefox";
    private WebDriver webDriver;
    private IFrameSelector iFrameSelector = new DefaultIFrameSelector();
    private String screenshotFolder = "screenshots";
    private List<String> supportScripts = new ArrayList<>();
    private float timeout = 60f;

    public OpenBrowser browser(String browser) {
        this.browser = browser;
        return this;
    }

    public OpenBrowser webDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
        return this;
    }

    public OpenBrowser iFrameSelector(IFrameSelector iFrameSelector) {
        this.iFrameSelector = iFrameSelector;
        return this;
    }

    public OpenBrowser iFrameSelector(BiConsumer<GermaniumDriver, String> iFrameSelector) {
        this.iFrameSelector = (germanium, iFrameName) -> {
            iFrameSelector.accept(germanium, iFrameName);
            return iFrameName;
        };

        return this;
    }

    public OpenBrowser screenshotFolder(String screenshotFolder) {
        this.screenshotFolder = screenshotFolder;
        return this;
    }

    public OpenBrowser withClasspathScript(String ... scripts) {
        supportScripts.addAll(Arrays.asList(scripts));
        return this;
    }

    public OpenBrowser timeout(float timeoutInSeconds) {
        this.timeout = timeoutInSeconds;
        return this;
    }

    /**
     * Open the given browser.
     *
     * @return
     */
    public GermaniumDriver get() {
        if (GermaniumApi.getGermanium() != null) {
            throw new IllegalStateException("A browser already runs. Close it first with GermaniumApi.closeBrowser().");
        }

        Matcher remoteMatch = REMOTE_ADDRESS.matcher(browser);

        WebDriver webDriver = null;

        if (this.webDriver != null) {
            webDriver = this.webDriver;
        } else if (RemoteWebDriverQueryBuilder.matches(browser)) {
            webDriver = RemoteWebDriverQueryBuilder.webDriver(browser);
        } else if (remoteMatch.matches()) {
            webDriver = RemoteWebDriverUrlOnlyBuilder.webDriver(remoteMatch);
        } else {
            webDriver = LocalWebDriverBuilder.webDriver(browser);
        }

        GermaniumDriver germanium = new GermaniumDriver(
                webDriver,
                iFrameSelector,
                screenshotFolder,
                supportScripts);

        GermaniumApi.setGermanium(germanium);

        return germanium;
    }

    public static GermaniumDriver throwUnknownBrowser(String browser) {
        throw new IllegalArgumentException(String.format(
                "Unknown browser: %s, only firefox, " +
                    "chrome and ie are supported." ,
                browser
        ));
    }
}
