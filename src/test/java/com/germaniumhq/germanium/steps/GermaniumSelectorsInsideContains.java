package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.locators.Locator;
import com.germaniumhq.germanium.selectors.AbstractSelector;
import com.germaniumhq.germanium.selectors.Css;
import com.germaniumhq.germanium.selectors.Element;
import com.germaniumhq.germanium.selectors.Input;
import com.germaniumhq.germanium.selectors.InputText;
import com.germaniumhq.germanium.selectors.Text;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.*;

public class GermaniumSelectorsInsideContains {

    @When("^I search for an InputText inside the div with id inputTextContainer$")
    public void i_search_for_an_InputText_inside_the_div_with_id_inputTextContainer() throws Throwable {
        AbstractSelector selector = new InputText().inside(
                new Element("div").id("inputTextContainer")
        );

        WebElement element = selector.element();

        assertNotNull(element);

        Context.set("found_element", element);
    }

    @When("^I search using CSS for an input inside the div#inputTextContainer$")
    public void i_search_using_CSS_for_an_input_inside_the_div_inputTextContainer() throws Throwable {
        AbstractSelector selector = new Css("input").inside("div#inputTextContainer");
        WebElement element = GermaniumApi.S(selector).element();

        assertNotNull(element);

        Context.set("found_element", element);
    }

    @When("^I search for a div inside a JS selector$")
    public void i_search_for_a_div_inside_a_JS_selector() throws Throwable {
        Context.set("found_element",
                new Element("div")
                    .inside("js:return [document.body];")
                .element());
    }

    @When("^I search for a div containing an InputText$")
    public void i_search_for_a_div_containing_an_InputText() throws Throwable {
        AbstractSelector selector = new Element("div").containing(new InputText("inputTextPlaceholder"));
        WebElement element = GermaniumApi.S(selector).element();

        assertNotNull(element);

        Context.set("found_element", element);
    }

    @When("^I search using CSS for a div containing an InputText$")
    public void i_search_using_CSS_for_a_div_containing_an_InputText() throws Throwable {
        AbstractSelector selector = new Css("div").containing("#inputText");
        WebElement element = GermaniumApi.S(selector).element();

        assertNotNull(element);

        Context.set("found_element", element);
    }

    @When("^I search for a div containing a JS selector$")
    public void i_search_for_a_div_containing_a_JS_selector() throws Throwable {
        AbstractSelector selector = new Element("div").containing("js:return [document.getElementById('inputText')];");
        Context.set("found_element", selector.element());
    }

    @When("^I search for all the (.*?)s without children$")
    public void i_search_for_all_the_divs_without_children(String tagName) throws Throwable {
        AbstractSelector selector = new Element(tagName).withoutChildren();

        Context.set("found_element_list", selector.elementList(Locator.Visibility.ALL_ELEMENTS));
    }

    @Then("^I only get the div with id #decoyDiv$")
    public void i_only_get_the_div_with_id_decoyDiv() throws Throwable {
        List<WebElement> foundElementList = Context.get("found_element_list");
        assertNotNull(foundElementList);
        assertEquals(1, foundElementList.size());
        assertEquals("The element found is not the same element",
                GermaniumApi.S("#decoyDiv").element(Locator.Visibility.ALL_ELEMENTS),
                foundElementList.get(0));
    }

    @Then("^I get no elements returned$")
    public void i_get_no_elements_returned() throws Throwable {
        assertTrue(Context.<List<WebElement>>get("found_element_list").isEmpty());
    }

    @When("^I search for the first input in the second div$")
    public void i_search_for_the_first_input_in_the_second_div() throws Throwable {
        AbstractSelector selector = new Element("input").index(1)
                    .inside(new Element("div").index(2));

        WebElement element = selector.element();

        assertNotNull(element);

        Context.set("found_element", element);
    }

    @When("^I search for a div that contains both an input and a span$")
    public void i_search_for_a_div_that_contains_both_an_input_and_a_span() throws Throwable {
        AbstractSelector selector = new Element("div").containingAll(
                new Input(), new Text("only with content")
        );

        WebElement element = selector.element();

        assertNotNull(element);

        Context.set("found_element", element);
    }

    @When("^I search for a div that contains a span, that contains a text '(.*?)'$")
    public void i_search_for_a_div_that_contains_a_span_that_contains_a_text_missing_text(String text) throws Throwable {
        AbstractSelector selector = new Element("div").containing(
            new Css("span").containing(
                new Text(text)
            )
        );

        Context.set("found_element", selector.element());
        Context.set("found_element_list", selector.elementList());
    }

    @When("^I search for a div that contains a br$")
    public void i_search_for_a_div_that_contains_a_br() throws Throwable {
        AbstractSelector selector = new Element("div").containing("br");
        Context.set("found_element", selector.element());
    }

    @When("^I search for an InputText inside the div element with id inputTextContainer$")
    public void i_search_for_an_InputText_inside_the_div_element_with_id_inputTextContainer() throws Throwable {
        WebElement container_div_element = new Element("div")
                .id("inputTextContainer")
                .element();
        AbstractSelector selector = new InputText().inside(container_div_element);
        WebElement element = GermaniumApi.S(selector).element();

        assertNotNull(element);

        Context.set("found_element", element);
    }
}