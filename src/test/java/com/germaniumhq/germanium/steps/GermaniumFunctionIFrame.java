package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.all.GermaniumApi;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static com.germaniumhq.germanium.all.GermaniumActions.typeKeys;
import static com.germaniumhq.germanium.all.GermaniumIFrame.runInFrame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
}
