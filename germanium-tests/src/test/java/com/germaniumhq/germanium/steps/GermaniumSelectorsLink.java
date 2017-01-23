package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.selectors.Link;
import cucumber.api.java.en.When;

public class GermaniumSelectorsLink {
    @When("^I look for a link with some text: '(.*?)'$")
    public void i_look_for_a_link_with_some_text_some_text(String text) throws Throwable {
        Context.set("found_element", new Link(text).element());
    }

    @When("^I look for a link with exactly the text: '(.*?)'$")
    public void i_look_for_a_link_with_exactly_the_text_some_text(String text) throws Throwable {
        Context.set("found_element", new Link().exactText(text).element());
    }

    @When("^I look for a link with the href: '(.*?)'$")
    public void i_look_for_a_link_with_the_href_http_ciplogic_com(String href) throws Throwable {
        Context.set("found_element", new Link().href(href).element());
    }

    @When("^I look for a link with the href containing: '(.*?)'$")
    public void i_look_for_a_link_with_the_href_containing_ciplogic(String hrefContains) throws Throwable {
        Context.set("found_element", new Link().hrefContains(hrefContains).element());
    }
}
