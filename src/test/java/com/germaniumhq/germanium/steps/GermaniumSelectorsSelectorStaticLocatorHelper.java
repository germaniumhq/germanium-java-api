package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.germaniumhq.germanium.all.GermaniumSelectors.*;
import static org.junit.Assert.*;

public class GermaniumSelectorsSelectorStaticLocatorHelper {
    @When("^I search using selectors for an InputText above the text \"([^\"]*)\"$")
    public void i_search_using_selectors_for_an_InputText_above_the_text(String text) throws Throwable {
        WebElement element = InputText().above(Text(text)).element();

        assertNotNull(element);

        Context.set("found_element", element);
    }

    @When("^I search using selectors for all InputText elements$")
    public void i_search_using_selectors_for_all_InputText_elements() throws Throwable {
        Context.set("found_elements", InputText().elementList());
    }

    @Then("^I find (\\d+) text elements that match$")
    public void i_find_text_elements_that_match(int count) throws Throwable {
        List elements = Context.get("found_elements");

        assertEquals(count, elements.size());
    }

    @When("^I search using selectors if an InputText above the text \"([^\"]*)\" exists$")
    public void i_search_using_selectors_if_an_InputText_above_the_text_exists(String arg1) throws Throwable {
        Context.set("does_element_exist",
                InputText().above(Text("Surname")).exists());
    }

    @Then("^yes, it exists$")
    public void yes_it_exists() throws Throwable {
        boolean doesElementExists = Context.get("does_element_exist");
        assertTrue(doesElementExists);
    }

    @When("^I search using selectors if an Image above the text \"([^\"]*)\" exists$")
    public void i_search_using_selectors_if_an_Image_above_the_text_exists(String arg1) throws Throwable {
        Context.set("does_element_exist",
                Image().above(Text("Surname")).exists());
    }

    @Then("^no, it doesn't exists$")
    public void no_it_doesn_t_exists() throws Throwable {
        boolean doesElementExists = Context.get("does_element_exist");
        assertFalse(doesElementExists);
    }

    @When("^I search using a Css selector for the (\\d+)rd 'input'$")
    public void i_search_using_a_Css_selector_for_the_rd_input(int arg1) throws Throwable {
        Context.set("found_element", Css("input").elementList(3));
    }
}
