package com.germaniumhq.germanium.steps;

import cucumber.api.java.en.Then;

import static com.germaniumhq.germanium.all.GermaniumActions.click;
import static com.germaniumhq.germanium.all.GermaniumApi.S;
import static com.germaniumhq.germanium.all.Wait.waited;

public class GermaniumFunctionWaited {
    @Then("^I click on waited '(.*?)'$")
    public void i_click_on_waited_selector(String selector) throws Throwable {
        click(waited(S(selector)));
    }
}
