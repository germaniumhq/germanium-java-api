package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.selectors.AnyOfSelector;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static com.germaniumhq.germanium.all.GermaniumSelectors.AnyOfSelector;
import static com.germaniumhq.germanium.all.GermaniumSelectors.Element;

public class GermaniumSelectorsAnyOf {
    @When("^I look for a custom button selector with the text: '(.*?)'$")
    public void i_look_for_a_div_element_with_is_some_text_in_it(String expectedText) throws Throwable {

        AnyOfSelector selector = AnyOfSelector(
                Element("button").exactText(expectedText),
                Element("input").exactAttribute("type", "button").exactAttribute("value", expectedText),
                Element("input").exactAttribute("type", "submit").exactAttribute("value", expectedText)
        );

        WebElement element = selector.element();

        Context.set("found_element", element);
    }
}
