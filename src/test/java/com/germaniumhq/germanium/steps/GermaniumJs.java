package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.germaniumhq.germanium.all.GermaniumApi.js;
import static org.junit.Assert.*;

public class GermaniumJs {
    @When("^I execute js with one parameter '(.*?)'$")
    public void i_execute_js_with_one_parameter_jsparameter(String parameter, String code) throws Throwable {
        js(code, parameter);
    }

    @Then("^nothing happens$")
    public void nothing_happens() throws Throwable {
    }

    @When("^I execute js without any parameters$")
    public void i_execute_js_without_any_parameters(String code) throws Throwable {
        Context.set("jsEvaluationResult", js(code));
    }

    @Then("^I got two elements, one with id textInput, and the other with id anotherTextInput\\.$")
    public void i_got_two_elements_one_with_id_textInput_and_the_other_with_id_anotherTextInput() throws Throwable {
        List items = Context.get("jsEvaluationResult");
        assertNotNull(items);
        assertEquals(2, items.size());
        assertTrue(items.get(0) instanceof WebElement);
        assertTrue(items.get(0) instanceof WebElement);
        assertEquals("textInput", ((WebElement)items.get(0)).getAttribute("id"));
        assertEquals("anotherTextInput", ((WebElement)items.get(1)).getAttribute("id"));
    }
}
