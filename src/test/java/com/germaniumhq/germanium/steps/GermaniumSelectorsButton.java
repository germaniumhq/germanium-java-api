package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.selectors.Button;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertNull;

public class GermaniumSelectorsButton {
    @When("^I look for a button with the text: '(.*?)'$")
    public void i_look_for_a_button_with_the_text(String text) throws Throwable {
        WebElement element = new Button().searchText(text).element();
        Context.set("found_element", element);
    }

    @When("^I look for a button with the exact text: '(.*?)'$")
    public void i_look_for_a_button_with_the_exact_text_Input_Button(String text) throws Throwable {
        WebElement element = new Button().text(text).element();
        Context.set("found_element", element);
    }

    @When("^I look for a button with the name: '(.*?)'$")
    public void i_look_for_a_button_with_the_name_inputButton(String name) throws Throwable {
        WebElement element = new Button().name(name).element();
        Context.set("found_element", element);
    }

    @Then("^there is no element found$")
    public void there_is_no_element_found() throws Throwable {
        assertNull(Context.get("found_element"));
    }


}
