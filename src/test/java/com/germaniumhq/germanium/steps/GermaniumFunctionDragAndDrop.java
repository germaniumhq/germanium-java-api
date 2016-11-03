package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.all.GermaniumActions;
import com.germaniumhq.germanium.locators.Locator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.germaniumhq.germanium.all.GermaniumSelectors.Css;
import static org.junit.Assert.assertEquals;

public class GermaniumFunctionDragAndDrop {
    @When("^I drag and drop from the #startDiv to the #endDiv$")
    public void i_drag_and_drop_from_the_startDiv_to_the_endDiv() throws Throwable {
        GermaniumActions.dragAndDrop("#startDiv", "#endDiv");
    }

    @Then("^the drag and drop events correspond$")
    public void the_drag_and_drop_events_correspond() throws Throwable {
        assertEquals("startDiv mousedown\nendDiv mouseup",
                Css("#messages").text(Locator.Visibility.ALL_ELEMENTS).trim());

    }
}
