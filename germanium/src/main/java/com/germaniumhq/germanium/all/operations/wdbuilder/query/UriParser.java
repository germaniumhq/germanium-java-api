package com.germaniumhq.germanium.all.operations.wdbuilder.query;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse a given URI into something that we can feed inside `RemoteWebDriverQueryBuilder`.
 */
public class UriParser {
    public static final Pattern REMOTE_QUERY_PATTERN = Pattern.compile("^(\\w+?)\\?(.*?)$");
    public static final String WD_URL = "wdurl";

    public BrowserSpecification parse(String browser) {
        Matcher matcher = REMOTE_QUERY_PATTERN.matcher(browser);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("UriParser called on a non URI.");
        }

        BrowserSpecification result = new BrowserSpecification();

        result.setBrowserName(matcher.group(1));

        for (String keyValuePair : matcher.group(2).split("&")) {
            if (keyValuePair.indexOf('=') < 0) {
                result.getDesiredCapabilities().put(decode(keyValuePair), "true");
                continue;
            }

            String key = keyValuePair.substring(0, keyValuePair.indexOf('='));
            String value = keyValuePair.substring(keyValuePair.indexOf('=') + 1);

            if (WD_URL.equalsIgnoreCase(key)) {
                result.setUrl(value);
                continue;
            }

            result.getDesiredCapabilities().put(key, value);
        }

        if (result.getUrl() == null) {
            throw new IllegalArgumentException(String.format(
                    "Unable to create a remote browser from: `%s`. In order to create a " +
                    "browser, the `%s` must be specified in the query parameters.",
                    browser,
                    WD_URL
            ));
        }

        return result;
    }

    private String decode(String value) {
        try {
            return URLDecoder.decode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unable to decode: " + value, e);
        }
    }
}
