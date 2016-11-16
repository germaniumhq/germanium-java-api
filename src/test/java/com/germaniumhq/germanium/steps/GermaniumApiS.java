package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.locators.Locator;
import com.germaniumhq.germanium.locators.StaticElementLocator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static com.germaniumhq.germanium.all.GermaniumApi.S;
import static com.germaniumhq.germanium.all.GermaniumSelectors.Css;
import static org.junit.Assert.*;

public class GermaniumApiS {
    @Then("^I search using S for (.*)$")
    public void i_search_using_S(String selector) {
        S(selector).exists();
    }


    @Then("^the selector '(.*?)' exists somewhere$")
    public void the_selector_exists_somewhere(String selector) throws Throwable {
        assertTrue(S(selector).exists(Locator.Visibility.ALL_ELEMENTS));
    }

    @Then("^the selector '(.*?)' exists and is visible$")
    public void the_selector_exists_and_is_visible(String selector) throws Throwable {
        assertTrue(S(selector).exists());
    }

    @Then("^the selector '(.*?)' doesn't exists as visible$")
    public void the_selector_doesn_t_exists_as_visible(String selector) throws Throwable {
        assertFalse(S(selector).exists());
    }

    @When("^I search using a nested locator for '(.*?)'$")
    public void i_search_using_a_nested_locator_for_selector(String selector) throws Throwable {
        // In Java, since we have a type system, we don't actually use
        // nested selectors anywhere.
        WebElement element = GermaniumApi.<WebElement>S(selector).element();
        Context.set("found_element", element);
    }

    @Then("^the selector '(.*?)' doesn't exists at all$")
    public void the_missing_selector_doesn_t_exists_at_all(String selector) throws Throwable {
        assertFalse(S(selector).exists(Locator.Visibility.ALL_ELEMENTS));
    }

    @When("^I search using a callable that returns a CssSelector '(.*?)'$")
    public void i_search_using_a_callable_that_returns_a_CssSelector(String selector) throws Throwable {
        WebElement element = GermaniumApi.S(() -> Css(selector)).element();

        Context.set("found_element", element);
    }

    @When("^I create a StaticElementLocator with a single element: #outsideTextFlowedInput$")
    public void i_create_a_StaticElementLocator_with_a_single_element_outsideTextFlowedInput() throws Throwable {
        WebElement element = Css("#outsideTextFlowedInput").element();
        Context.set("static_element_locator", new StaticElementLocator(element));
    }

    @Then("^the StaticElementLocator has one element$")
    public void the_StaticElementLocator_has_one_element() throws Throwable {
        WebElement element = ((Locator<WebElement>)Context.get("static_element_locator")).element();
        assertNotNull(element);
    }

    @Then("^the StaticElementLocator has no elements anymore$")
    public void the_StaticElementLocator_has_no_elements_anymore() throws Throwable {
        WebElement element = ((Locator<WebElement>)Context.get("static_element_locator")).element();
        assertNull(element);
    }

    @When("^I search for the 3rd element that is an 'input'$")
    public void i_search_for_the_rd_element_that_is_an_input() throws Throwable {
        WebElement element = GermaniumApi.<WebElement>S("input").elementList(2);

        Context.set("found_element", element);
    }
}
