package com.germaniumhq.germanium.all.operations.wdbuilder.query;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UriParserTest {
    @Test
    public void testUriParsing() {
        BrowserSpecification result = new UriParser().parse("ie?version=8&data=true&wdurl=http://192.168.0.2:4444/wd/hub");

        assertNotNull(result);
        assertEquals("ie", result.getBrowserName());
        assertEquals("8", result.getDesiredCapabilities().get("version"));
        assertEquals("true", result.getDesiredCapabilities().get("data"));
        assertEquals("http://192.168.0.2:4444/wd/hub", result.getUrl());
        assertNull(result.getDesiredCapabilities().get("wdurl"));
    }

}