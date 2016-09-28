package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.locators.Locator;
import com.germaniumhq.germanium.selectors.Css;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class GermaniumSelectorsCss {
    @When("^I look for the following css selector: (.*?)$")
    public void i_look_for_the_following_css_selector_inputButton(String selector) throws Throwable {
        Locator<WebElement> locator = GermaniumApi.S(new Css(selector));
        WebElement element = locator.element();
        Context.set("found_element", element);
    }

    @Then("^I find the element with id: '(.*?)'$")
    public void i_find_the_element_with_id_inputButton(String elementId) throws Throwable {
        WebElement element = Context.get("found_element");
        assertEquals(elementId, element.getAttribute("id"));
    }
}
