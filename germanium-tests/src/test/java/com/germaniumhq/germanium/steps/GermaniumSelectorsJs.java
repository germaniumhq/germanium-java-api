package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.locators.Locator;
import com.germaniumhq.germanium.selectors.JsSelector;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class GermaniumSelectorsJs {
    @When("^I look for the following js selector: (.*?)$")
    public void i_look_for_the_following_js_selector_return_document_getElementById_inputButton(String code) throws Throwable {
        try {
            WebElement element = new JsSelector(code).element();

            Context.set("found_element", element);
            Context.remove("exception");
        } catch (Exception e) {
            Context.set("exception", e);
            Context.remove("found_element");
        }

    }

    @Then("^it throws an exception$")
    public void it_throws_an_exception() throws Throwable {
        assertNotNull(Context.get("exception"));
    }

    @When("^I look for the following js single element selector$")
    public void i_look_for_the_following_js_single_element_selector(String code) throws Throwable {
        try {
            WebElement element = new JsSelector(code).element(
                    Locator.Visibility.ALL_ELEMENTS);

            Context.set("found_element", element);
            Context.remove("exception");
        } catch (Exception e) {
            Context.set("exception", e);
            Context.remove("found_element");
        }
    }

    @Then("^I find no element$")
    public void i_find_no_element() throws Throwable {
        assertNull(Context.get("found_element"));
    }

}
