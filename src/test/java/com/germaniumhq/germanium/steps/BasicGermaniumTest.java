package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.all.GermaniumApi;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;

import static com.germaniumhq.germanium.all.GermaniumActions.click;
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
}
