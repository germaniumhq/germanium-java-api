package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.germaniumhq.germanium.all.GermaniumApi.S;
import static com.germaniumhq.germanium.all.GermaniumSelectors.Text;

public class GermaniumSelectorsText {

    @When("^I look for some text: '(.*)'$")
    public void i_look_for_some_text_just_a_simple_div(String text) throws Throwable {
        WebElement element = S(Text(text)).element();

        Context.set("found_element", element);
    }

    @When("^I look for some text in multiple elements: '(.*)'$")
    public void i_look_for_some_text_in_multiple_elements_text(String text) throws Throwable {
        List<WebElement> elements = S(Text(text)).elementList();

        Context.set("found_elements", elements);
    }
}
