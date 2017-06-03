package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.all.GermaniumSelectors;
import com.germaniumhq.germanium.points.Box;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.germaniumhq.germanium.all.GermaniumActions.click;
import static com.germaniumhq.germanium.all.GermaniumSelectors.Box;
import static com.germaniumhq.germanium.all.GermaniumSelectors.Css;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
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

    @When("^I try to get the box positions for a selector that doesn't matches$")
    public void i_try_to_get_the_box_positions_for_a_selector_that_doesn_t_matches() throws Throwable {
        try {
            GermaniumSelectors.Box("unknownelement").getBox();
        } catch (Exception e) {
            Context.set("exception_message", e.getMessage());
            return;
        }

        assertTrue("The unknown element box finding should have thrown an exception.",
                false);
    }

    @Then("^I get an exception spelling out that my selector didn't matched$")
    public void i_get_an_exception_spelling_out_that_my_selector_didn_t_matched() throws Throwable {
        String message = Context.get("exception_message");
        assertNotNull(message);
        assertTrue(message.contains("The passed selector (unknownelement) for " +
                "finding the bounding box didn't matched"));
    }

    @Then("I get the box positions for the first two rows")
    public void i_get_the_boxes() {
        Context.set("first_box", Box(Css("#row11")).getBox());
        Context.set("second_box", Box(Css("#row21")).getBox());
    }

    @Then("the positions of the 2 boxes are different")
    public void the_positions_are_the_same() {
        Box box1 = Context.get("first_box");
        Box box2 = Context.get("second_box");

        assertNotEquals(box1.top(), box2.top());
    }
}
