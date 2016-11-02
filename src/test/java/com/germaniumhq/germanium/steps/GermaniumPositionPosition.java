package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.all.GermaniumApi;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.germaniumhq.germanium.all.GermaniumActions.click;
import static com.germaniumhq.germanium.all.GermaniumSelectors.Box;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GermaniumPositionPosition {
    @When("^I click on the exact element of '(.*?)'$")
    public void i_click_on_the_exact_element(String selector) {
        click(selector);
    }

    @When("^I click on the center of '(.*?)'$")
    public void i_click_on_center(String selector) {
        click(Box(selector).center());
    }

    @When("^I click on the top left of '(.*?)'$")
    public void i_click_on_top_left_corner(String selector) {
        click(Box(selector).topLeft());
    }

    @When("^I click on the top center of '(.*?)'$")
    public void i_click_on_top_center(String selector) {
        click(Box(selector).topCenter());
    }

    @When("^I click on the top right of '(.*?)'$")
    public void i_click_on_top_right_corner(String selector) {
        click(Box(selector).topRight());
    }

    @When("^I click on the middle left of '(.*?)'$")
    public void i_click_on_the_middle_left(String selector) {
        click(Box(selector).middleLeft());
    }

    @When("^I click on the middle right of '(.*?)'$")
    public void i_click_on_middle_right_corner(String selector) {
        click(Box(selector).middleRight());
    }

    @When("^I click on the bottom left of '(.*?)'$")
    public void i_click_on_bottom_left_corner(String selector) {
        click(Box(selector).bottomLeft());
    }

    @When("^I click on the bottom center of '(.*?)'$")
    public void i_click_on_bottom_center_corner(String selector) {
        click(Box(selector).bottomCenter());
    }

    @When("^I click on the bottom right of '(.*?)'$")
    public void i_click_on_bottom_right(String selector) {
        click(Box(selector).bottomRight());
    }

    @Then("^the text of the '(.*?)' is '(.*?)'$")
    public void verify_text(String selector, String expected_text) {
        if ("inline x: 149 y: 100".equals(expected_text) ||
            "absolute x: 149 y: 100".equals(expected_text)) {
            String expected_text_150 = expected_text.replace("149", "150");

            assertTrue(expected_text.equals(GermaniumApi.getText(selector)) ||
                    expected_text_150.equals(GermaniumApi.getText(selector)));

            return;
        }

        assertEquals(expected_text, GermaniumApi.getText(selector));
    }
}
