package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.selectors.AbstractSelector;
import com.germaniumhq.germanium.selectors.Text;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.germaniumhq.germanium.all.GermaniumApi.S;
import static com.germaniumhq.germanium.all.GermaniumSelectors.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GermaniumSelectorsPositional {
    Map<String, Integer> indexes;

    public GermaniumSelectorsPositional() {
        indexes = new HashMap<>();

        indexes.put("first", 0);
        indexes.put("second", 1);
        indexes.put("third", 2);
        indexes.put("fourth", 3);
        indexes.put("fifth", 4);
        indexes.put("sixth", 5);
    }

    @When("^I search for an InputText above the row containing text \"([^\"]*)\"$")
    public void i_search_for_an_InputText_above_the_row_containing_text(String text) throws Throwable {
        AbstractSelector selector = InputText().above(Element("tr").containsText(text));
        WebElement element = S(selector).element();

        assertNotNull(element);
        Context.set("found_element", element);
    }

    @When("^I search for an InputText below the row containing text \"([^\"]*)\"$")
    public void i_search_for_an_InputText_below_the_row_containing_text(String text) throws Throwable {
        AbstractSelector selector = InputText().below(Element("tr").containsText(text));
        WebElement element = S(selector).element();

        assertNotNull(element);
        Context.set("found_element", element);
    }

    @When("^I search for an Input left of the text \"([^\"]*)\"$")
    public void i_search_for_an_Input_left_of_the_text(String text) throws Throwable {
        AbstractSelector selector = Input().leftOf(Text(text));
        WebElement element = S(selector).element();

        assertNotNull(element);

        Context.set("found_element", element);
    }

    @When("^I search for an Input right of the text \"([^\"]*)\"$")
    public void i_search_for_an_Input_right_of_the_text(String text) throws Throwable {
        AbstractSelector selector = Input().rightOf(Text(text));
        WebElement element = S(selector).element();

        assertNotNull(element);

        Context.set("found_element", element);
    }

    @When("^I search for an table cell right of the text \"([^\"]*)\"$")
    public void i_search_for_an_table_cell_right_of_the_text(String text) throws Throwable {
        AbstractSelector selector = Element("td").rightOf(Text(text));
        WebElement element = S(selector).element();

        assertNotNull(element);

        Context.set("found_element", element);
    }

    @When("^I search for all the input texts right of the text \"([^\"]*)\"$")
    public void i_search_for_all_the_input_texts_right_of_the_text(String text) throws Throwable {
        Context.set("found_elements", InputText().rightOf(Text(text)).elementList());
    }

    @Then("^I find (\\d+) elements that match$")
    public void i_find_elements_that_match(int itemCOunt) throws Throwable {
        List elements = Context.get("found_elements");
        assertNotNull(elements);
        assertEquals(itemCOunt, elements.size());
    }

    @When("^I search for the (.*?) input text right of the text \"([^\"]*)\"$")
    public void i_search_for_the_first_input_text_right_of_the_text(String index, String text) throws Throwable {
        WebElement selector = InputText().rightOf(Text(text)).elementList().get(indexes.get(index));
        WebElement element = S(selector).element();

        Context.set("found_element", element);
    }

    @When("^I search for all the input texts above the text \"([^\"]*)\"$")
    public void i_search_for_all_the_input_texts_above_the_text(String text) throws Throwable {
        Context.set("found_elements", InputText().above(Text(text)).elementList());
    }

    @When("^I search for the (.*?) input text above the text \"([^\"]*)\"$")
    public void i_search_for_the_first_input_text_above_the_text(String index, String text) throws Throwable {
        WebElement selector = InputText().above(Text(text)).elementList().get(indexes.get(index));
        WebElement element = S(selector).element();

        Context.set("found_element", element);
    }

    @When("^I search for all the elements left of all the elements$")
    public void i_search_for_all_the_elements_left_of_all_the_elements() throws Throwable {
        Context.set("found_elements", Element("*").leftOf(Element("*")).elementList());
    }

    @When("^I search for an Input left of the text element \"([^\"]*)\"$")
    public void i_search_for_an_Input_left_of_the_text_element(String text) throws Throwable {
        Text text_element = Text(text);
        AbstractSelector selector = Input().leftOf(text_element);
        WebElement element = S(selector).element();

        assertNotNull(element);

        Context.set("found_element", element);
    }
}
