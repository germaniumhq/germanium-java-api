package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.all.operations.ChildNodes;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.*;

public class GermaniumFunctionChildNodes {
    @When("^I get the child nodes for the element '(.*?)'$")
    public void i_get_the_child_nodes_for_the_element_id(String id) throws Throwable {
        List<WebElement> elements = GermaniumApi.childNodes("#" + id, ChildNodes.ElementType.ALL_NODES);
        Context.set("found_element_list", elements);
    }

    @Then("^I get 5 child nodes: 3 text nodes, a div \\(#childDiv\\) and a span \\(#childSpan\\)$")
    public void i_get_child_nodes_text_nodes_a_div_childDiv_and_a_span_childSpan() throws Throwable {
        List elements = Context.get("found_element_list");

        assertNotNull(elements);
        assertEquals(5, elements.size());
        assertNotNull(elements.get(1) instanceof WebElement);
        assertEquals("div", ((WebElement)elements.get(1)).getTagName());
        assertNotNull(elements.get(3) instanceof WebElement);
        assertEquals("span", ((WebElement)elements.get(3)).getTagName());
    }

    @Then("^I get back an empty list as child nodes$")
    public void i_get_back_an_empty_list_as_child_nodes() throws Throwable {
        List elements = Context.get("found_element_list");
        assertNotNull(elements);
        assertTrue(elements.isEmpty());
    }

    @Then("^I get 2 child nodes: a div \\(#childDiv\\) and a span \\(#childSpan\\)$")
    public void i_get_child_nodes_a_div_childDiv_and_a_span_childSpan() throws Throwable {
        List elements = Context.get("found_element_list");

        assertNotNull(elements);
        assertEquals(2, elements.size());
        assertNotNull(elements.get(0) instanceof WebElement);
        assertEquals("div", ((WebElement)elements.get(0)).getTagName());
        assertNotNull(elements.get(1) instanceof WebElement);
        assertEquals("span", ((WebElement)elements.get(1)).getTagName());
    }

    @When("^I get only the child elements for the element '(.*?)'$")
    public void i_get_only_the_child_elements_for_the_element_id(String id) throws Throwable {
        List<WebElement> elements = GermaniumApi.childNodes("#" + id);
        Context.set("found_element_list", elements);
    }
}
