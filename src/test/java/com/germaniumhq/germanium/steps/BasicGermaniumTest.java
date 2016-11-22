package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.all.GermaniumApi;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static com.germaniumhq.germanium.all.GermaniumActions.click;
import static com.germaniumhq.germanium.all.GermaniumActions.typeKeys;
import static com.germaniumhq.germanium.all.GermaniumApi.S;
import static com.germaniumhq.germanium.all.GermaniumApi.getValue;
import static com.germaniumhq.germanium.all.GermaniumSelectors.Element;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BasicGermaniumTest {
    @Given("^I click on '(.*?)'$")
    public void i_click_on_input_textInput(String selector) throws Throwable {
        click(selector);
    }

    @Then("^the value for the (.*?) is '(.*?)'$")
    public void the_value_for_the_input_textInput_is_input_test(String selector, String text) throws Throwable {
        WebElement element = GermaniumApi.<WebElement>S(selector).element();
        assertEquals(text, getValue(element));
    }

    @When("^in the selector (.*?) I type_keys '(.*?)'$")
    public void in_the_selector_I_type_keys_(String selector, String keys) throws Throwable {
        typeKeys(keys, selector);
    }

    @When("^in the locator (.*?) I type_keys '(.*?)'$")
    public void in_the_locator_I_type_keys_(String selector, String keys) throws Throwable {
        typeKeys(keys, S(selector));
    }

    @Then("^I type '(.*?)' into (.*)$")
    public void i_type_input_test_into_input(String keys, String selector) throws Throwable {
        typeKeys(keys, selector);
    }


    @When("^in the locator (.*?) I type_keys '(.*?)' with (\\d+)ms delay$")
    public void in_the_locator_textInput_I_type_keys_abc_with_ms_delay(String selector, String keys, int delayInMs) throws Throwable {
        typeKeys(keys, S(selector), 0.2f);
    }


    @Then("^the text of the page doesn't contain '(.*?)'$")
    public void the_text_of_the_page_doesn_t_contain_button_clicked(String searchedText) throws Throwable {
        assertFalse(Element("body").text().contains(searchedText));
    }


    @Then("^the text of the page contains '(.*?)'$")
    public void the_text_of_the_page_contains_text(String searchedText) throws Throwable {
        assertTrue(Element("body").text().contains(searchedText));
    }
}
