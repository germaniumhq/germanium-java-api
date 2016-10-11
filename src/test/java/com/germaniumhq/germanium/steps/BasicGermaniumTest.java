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
import static org.junit.Assert.assertEquals;

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

    @When("^in the locator (.*?) I type_keys '(.*?)' with (\\d+)ms delay$")
    public void in_the_locator_textInput_I_type_keys_abc_with_ms_delay(String selector, String keys, int delayInMs) throws Throwable {
        typeKeys(keys, S(selector), 0.2f);
    }

}
