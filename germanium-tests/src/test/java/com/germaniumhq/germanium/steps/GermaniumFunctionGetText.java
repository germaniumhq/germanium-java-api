package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.locators.Locator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GermaniumFunctionGetText {
    @When("^I get the text from element '(.*?)'$")
    public void i_get_the_text_from_element_visibleTextDiv(String selector) throws Throwable {
        //e = S(selector).element(only_visible=False)
        //context.element_text = get_text(e)
        WebElement e = GermaniumApi.<WebElement>S(selector).element(
                Locator.Visibility.ALL_ELEMENTS);
        Context.set("element_text", GermaniumApi.getText(e));

    }

    @When("^I get the text for a None selector$")
    public void i_get_the_text_for_a_null_selector() {
        boolean testFailed = false;
        try {
            GermaniumApi.getText((String) null);
            testFailed = true;
        } catch (Exception e) {
            Context.set("caught_exception", e.getMessage());
        }

        assertFalse(testFailed);
    }

    @Then("^I get an exception saying the selector is not defined$")
    public void i_get_an_exception_saying_the_selector_is_not_defined() {
        assertEquals("The passed selector into getText() was null. In case " +
                        "you are using getText with waited: " +
                        "getText(waited(Element(..))) then it means the " +
                        "waited() call has failed.",
                Context.get("caught_exception"));
    }

    @Then("^I get an exception saying the selector didn't return anything$")
    public void i_get_an_exception_saying_the_selector_did_not_return() {
        assertEquals("No items, visible or invisible, matched the " +
                    "selector given for the action.",
                Context.get("caught_exception"));
    }

    @When("^I get the text for a selector that doesn't matches anything$")
    public void a_selector_doesnt_matches_anything() {
        boolean testFailed = false;
        try {
            GermaniumApi.getText("#doesNotExist");
            testFailed = true;
        } catch (Exception e) {
            Context.set("caught_exception", e.getMessage());
        }

        assertFalse(testFailed);
    }

    @Then("^the text from that element is$")
    public void the_text_from_that_element_is(String expectedText) throws Throwable {
        assertEquals(expectedText, Context.get("element_text"));
    }
}
