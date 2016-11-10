package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.all.GermaniumApi;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.Color;

import static org.junit.Assert.assertEquals;

/**
 * Gets the style of a specific element or selector.
 */
public class GermaniumFunctionGetStyle {
    @When("^the '(.*?)' style from element '(.*?)' is '(.*?)'$")
    public void check_style(String styleName, String selector, String expectedValue) {
        assertEquals(expectedValue, GermaniumApi.getStyle(selector, styleName));
    }

    @When("^the '(.*?)' style color from element '(.*?)' is '(.*?)'$")
    public void check_color(String styleName, String selector, String expectedValue) {
        assertEquals(Color.fromString(expectedValue),
                     Color.fromString(GermaniumApi.getStyle(selector, styleName)));
    }
}
