package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.all.GermaniumApi;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static com.germaniumhq.germanium.all.GermaniumActions.typeKeys;
import static com.germaniumhq.germanium.all.GermaniumIFrame.runInFrame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GermaniumFunctionIFrame {
    @When("^I type into iframe in (.*?) the following text: (.*)$")
    public void i_type_into_iframe_in_input_textInput_the_following_text_input_test(String selector, String text) throws Throwable {
        runInFrame("iframe", () -> {
            typeKeys(text, selector);
        });
    }

    @Then("^in the iframe the value for the (.*?) is '(.*?)'$")
    public void in_the_iframe_the_value_for_the_input_textInput_is_input_test(String locator, String text) throws Throwable {
        runInFrame("iframe", () -> {
            WebElement element = GermaniumApi.<WebElement>S(locator).element();

            assertNotNull(element);
            assertEquals(text, element.getAttribute("value"));
        });
    }

    @When("^I try to access the iframe named '(.*?)' that is not by default defined$")
    public void i_try_to_acess_the_iframe_that_is_not_defined(String name) {
        try {
            runInFrame(name, () -> {});
        } catch (Exception e) {
            Context.set("exception_message", e.getMessage());
            return;
        }

        assertTrue("The runInFrame should have thrown an exception since the " +
                "iframe should not be defined.", false);
    }

    @Then("^the exception message contains the text '(.*?)'$")
    public void the_exception_contains_the_text(String text) {
        String exceptionMessage = Context.get("exception_message");

        assertNotNull(exceptionMessage);
        assertTrue(exceptionMessage.contains(text));
    }
}
