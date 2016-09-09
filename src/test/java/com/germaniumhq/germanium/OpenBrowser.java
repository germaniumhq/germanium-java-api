package com.germaniumhq.germanium;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OpenBrowser {
    @Given("^I open the browser$")
    public void i_open_the_browser() throws Throwable {
    }

    @When("^I go to '(.*?)'$")
    public void i_go_to(String url) {

    }

    @Then("^the title of the page equals '(.*?)'$")
    public void is_title_of_the_page_equals(String text) {
    }
}
