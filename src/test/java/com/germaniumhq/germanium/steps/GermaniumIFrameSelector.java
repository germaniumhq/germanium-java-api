package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.all.GermaniumIFrame;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static com.germaniumhq.germanium.all.GermaniumApi.S;
import static com.germaniumhq.germanium.all.GermaniumIFrame.runInFrame;
import static com.germaniumhq.germanium.all.GermaniumSelectors.Element;
import static com.germaniumhq.germanium.all.GermaniumSelectors.Text;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GermaniumIFrameSelector {
    @When("^I search in the default iframe for the 'Just a message in the top frame' text$")
    public void i_search_in_the_default_iframe_for_the_Just_a_message_in_the_top_frame_text() throws Throwable {
        runInFrame(() -> {
            Context.set("found_element",
                    Text("Just a message in the top frame").element());
        });
    }

    @When("^I search for the element '(.*?)' in the @iframe named '(.*?)'$")
    public void i_search_for_the_element_textInput_in_the_iframe_named_custom_iframe(String selector, String iframeName) throws Throwable {
        runInFrame(iframeName, () -> {
            Context.set("found_element",
                        S(selector).element());
        });
    }

    @Then("^I find in iframe '(.*?)' the element with id: '(.*?)'$")
    public void i_find_the_element_with_id_inputButton(String iframeName, String elementId) throws Throwable {
        GermaniumIFrame.runInFrame(iframeName, () -> {
            WebElement element = Context.get("found_element");
            assertNotNull("Element was not found. `found_element` is null " +
                    "in the context.", element);
            assertEquals(elementId, element.getAttribute("id"));
        });
    }

    @When("^I switch the iframe selector to a custom one that handles 'custom-iframe'$")
    public void i_switch_the_iframe_selector_to_a_custom_one_that_handles_custom_iframe() throws Throwable {
        Context.set("currentIFrameSelector",
                GermaniumApi.getGermanium().getIFrameSelector());

        GermaniumApi.getGermanium().setIFrameSelector((germanium, target) -> {
            germanium.switchTo().defaultContent();
            if ("custom-iframe".equals(target)) {
                germanium.switchTo()
                        .frame(Element("iframe").element());
            }
            return target;
        });
    }

    @When("^when I switch the iframe selector back to the default one$")
    public void when_I_switch_the_iframe_selector_back_to_the_default_one() throws Throwable {
        GermaniumApi.getGermanium().setIFrameSelector(
                Context.get("currentIFrameSelector")
        );
    }

}
