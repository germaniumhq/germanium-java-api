package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.selectors.Element;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

public class GermaniumSelectorsElement {
    @When("^I look for a '(.*?)' element with '(.*?)' text in it$")
    public void i_look_for_a_div_element_with_is_some_text_in_it(String tagName, String text) throws Throwable {
        Element selector = new Element(tagName).containsText(text);
        WebElement element = GermaniumApi.S(selector).element();

        Context.set("found_element", element);
    }

    @When("^I look for a '(.*?)' element with a '(.*?)=(.*?)' attribute$")
    public void i_look_for_a_div_element_with_a_contenteditable_true_attribute(String elementName, String attributeName, String attributeValue) throws Throwable {
        WebElement element = new Element(elementName)
                .exactAttribute(attributeName, attributeValue)
                .element();

        Context.set("found_element", element);
    }

    @When("^I look for a '(.*?)' element with class '(.*?)'$")
    public void i_look_for_a_div_element_with_class_formatted_text(String elementName, String className) throws Throwable {
        Context.set(
                "found_element",
                new Element(elementName).cssClasses(className).element()
        );
    }

    @When("^I look for a '(.*?)' element with classes '(.*?)' and '(.*?)'$")
    public void i_look_for_a_div_element_with_classes_formatted_text_and_formatted_text_extra(String elementName, String class1, String class2) throws Throwable {
        Context.set(
                "found_element",
                new Element(elementName).cssClasses(class1, class2).element()
        );
    }

    @When("^I look for a '(.*?)' element with a matching '(.*?)=(.*?)' attribute$")
    public void i_look_for_a_div_element_with_a_matching_contenteditable_ru_attribute(String elementName, String attributeName, String attributeValue) throws Throwable {
        Context.set(
                "found_element",
                new Element(elementName).containsAttribute(attributeName, attributeValue).element()
        );
    }

    @When("^I look for the (\\d+)rd div element$")
    public void i_look_for_the_rd_div_element(int elementIndex) throws Throwable {
        Context.set(
                "found_element",
                new Element("div").index(elementIndex).element()
        );
    }
}
