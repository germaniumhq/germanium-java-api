package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.all.GermaniumActions;
import cucumber.api.java.en.When;

public class GermaniumFunctionClick {
    @When("^I right click on (.*)$")
    public void i_right_click_on(String selector) {
        GermaniumActions.rightClick(selector);
    }

    @When("^I doubleclick on (.*)$")
    public void i_double_click_on(String selector) {
        GermaniumActions.doubleClick(selector);
    }

    @When("^I mouse over on (.*)$")
    public void i_mouse_over_on(String selector) {
        GermaniumActions.hover(selector);
    }
}
