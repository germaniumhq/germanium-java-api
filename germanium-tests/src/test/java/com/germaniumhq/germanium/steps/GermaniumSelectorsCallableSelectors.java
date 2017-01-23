package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.selectors.Element;
import com.germaniumhq.germanium.selectors.InputText;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

public class GermaniumSelectorsCallableSelectors {
    @When("^I search for a div element using the InputText class as parameter$")
    public void i_search_for_a_div_element_using_the_InputText_class_as_parameter() throws Throwable {
        WebElement element = new Element("div").id("inputTextContainer")
                .containing(new InputText())
                .element();

        Context.set("found_element", element);
    }
}
