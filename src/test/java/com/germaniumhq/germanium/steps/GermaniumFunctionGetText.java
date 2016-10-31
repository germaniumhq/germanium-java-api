package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.locators.Locator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class GermaniumFunctionGetText {
    @When("^I get the text from element '(.*?)'$")
    public void i_get_the_text_from_element_visibleTextDiv(String selector) throws Throwable {
        //e = S(selector).element(only_visible=False)
        //context.element_text = get_text(e)
        WebElement e = GermaniumApi.<WebElement>S(selector).element(
                Locator.Visibility.ALL_ELEMENTS);
        Context.set("element_text", GermaniumApi.getText(e));

    }

    @Then("^the text from that element is$")
    public void the_text_from_that_element_is(String expectedText) throws Throwable {
        assertEquals(expectedText, Context.get("element_text"));
    }
}
