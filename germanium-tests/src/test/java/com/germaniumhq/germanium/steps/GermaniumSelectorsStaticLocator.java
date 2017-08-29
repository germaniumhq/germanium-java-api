package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.locators.Locator;
import com.germaniumhq.germanium.selectors.AbstractSelector;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static com.germaniumhq.germanium.all.GermaniumApi.S;
import static com.germaniumhq.germanium.all.GermaniumSelectors.Element;
import static com.germaniumhq.germanium.all.GermaniumSelectors.StaticElement;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GermaniumSelectorsStaticLocator {
    @When("^I have a static selector for the '(.*?)'$")
    public void i_have_a_static_selector_for_the_staticElement(String selector) throws Throwable {
        Context.set("static_element_selector", StaticElement(S(selector).element()));
    }


    @Then("^the static element locator returns the same element as '(.*?)'$")
    public void the_static_element_locator_returns_the_same_element_as_staticElement(String selector) throws Throwable {
        WebElement found_element = S(Context.<AbstractSelector>get("static_element_selector")).element();
        WebElement target_element = S(selector).element();

        assertNotNull(found_element);
        assertEquals(found_element, target_element);
    }


    @When("^I have a static selector for all the '(.*?)' elements, including the invisible$")
    public void i_have_a_static_selector_for_all_the_group_div_elements_including_the_invisible(String selector) {
        Context.set("static_element_selector",
                    StaticElement(S(selector).elementList(Locator.Visibility.ALL_ELEMENTS)));
    }


    @When("^search the static selector$")
    public void search_the_static_selector() throws Throwable {
        Locator<WebElement> elementList = Context.get("static_element_selector");
        Context.set("found_elements", elementList.elementList());
    }


    @When("^I search the static selector inside a table$")
    public void search_static_selector_inside_a_table() {
        Context.set("found_element", null);
        Context.set("exception", null);

        try {
            AbstractSelector staticSelector = Context.get("static_element_selector");
            AbstractSelector insideSelector = staticSelector.inside(Element("table"));

            Context.set("found_element", insideSelector.element());
        } catch (Exception e) {
            e.printStackTrace();
            Context.set("exception", e);
        }
    }
}
