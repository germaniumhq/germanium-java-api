package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.locators.Locator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GermaniumFunctionGetAttributes {
    @When("^I get the #myElement element attributes$")
    public void i_get_the_myElement_element_attributes() throws Throwable {
        Context.set("found_attributes", GermaniumApi.getAttributes("#myElement", Locator.Visibility.ALL_ELEMENTS));
    }

    @Then("^there are (\\d+) attributes$")
    public void there_are_attributes(int attributeCount) throws Throwable {
        Map<String, String> attributes = Context.get("found_attributes");

        assertEquals(attributeCount, attributes.size());
    }

    @Then("^the attribute '(.*?)' is '(.*?)'$")
    public void the_attribute_id_is_myElement(String attributeName, String attributeValue) throws Throwable {
        Map<String, String> attributes = Context.get("found_attributes");

        assertEquals(attributeValue, attributes.get(attributeName));
    }


    @Then("^the attribute '(.*?)' contains '(.*?)'$")
    public void the_attribute_style_contains_black(String attributeName, String attributeValue) throws Throwable {
        Map<String, String> attributes = Context.get("found_attributes");

        assertNotNull(attributes.get(attributeName));
        assertTrue(attributes.get(attributeName).contains(attributeValue));
    }
}
