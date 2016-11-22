package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.selectors.Window;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.germaniumhq.germanium.all.GermaniumIFrame.useWindow;
import static com.germaniumhq.germanium.all.GermaniumSelectors.Css;
import static com.germaniumhq.germanium.all.GermaniumSelectors.Element;
import static com.germaniumhq.germanium.all.Wait.waitFor;

public class GermaniumFunctionWindow {
    @When("^I select the window with the title '(.*?)'$")
    public void i_select_the_window_with_the_title_inputs_window(String title) throws Throwable {
        waitFor(new Window().title(title));
        useWindow(title);
        waitFor(Css("input.name1"));
    }

    @When("^I select the default window$")
    public void i_select_the_default_window() throws Throwable {
        useWindow("Window Page");
    }

    @Then("^I wait for the text in '#name(\\d+)Target' to be 'name(\\d+) text'$")
    public void i_wait_for_the_text_in_name_Target_to_be_name_text(int arg1, int arg2) throws Throwable {
        waitFor(Element("div").id("name1Target").exactText("name1 text"));
    }
}
