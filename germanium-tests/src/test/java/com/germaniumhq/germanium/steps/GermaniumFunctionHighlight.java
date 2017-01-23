package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.all.Wait;
import com.germaniumhq.germanium.all.operations.actions.Highlight;
import com.germaniumhq.germanium.impl.ScriptLoader;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import java.util.List;

import static com.germaniumhq.germanium.all.GermaniumApi.getAttributes;
import static com.germaniumhq.germanium.all.GermaniumApi.js;
import static com.germaniumhq.germanium.all.GermaniumSelectors.Alert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GermaniumFunctionHighlight {
    @When("^I highlight the element '(.*?)'$")
    public void i_highlight_the_element_visibleDiv(String selector) throws Throwable {
        GermaniumApi.highlight(selector, Highlight.Console.NONE, 3, 1);
    }

    @Then("^the highlighted element is '(.*?)'$")
    public void the_highlighted_element_is_visibleDiv(String selector) throws Throwable {
        WebElement element = GermaniumApi.<WebElement>S(selector).element();

        Wait.waitFor(() -> {
            String backgroundColor = GermaniumApi.getStyle(element, "outlineColor");
            return Color.fromString("#00ff00").equals(Color.fromString(backgroundColor));
        });
    }

    @When("^I highlight also in the console the element '(.*?)'$")
    public void i_highlight_also_in_the_console_the_element_visibleDiv(String selector) throws Throwable {
        js(ScriptLoader.getScript("/germanium/test/console.js"));
        GermaniumApi.highlight(selector, Highlight.Console.LOG, 4, 1);
    }

    @Then("^in the log the highlighted element was notified as found$")
    public void in_the_log_the_highlighted_element_was_notified_as_found() throws Throwable {
        assertEquals(2, ((List)js("return window._lastlog_message")).size());
        assertEquals("GERMANIUM: Element with selector `#visibleDiv` was found.",
                js("return window._lastlog_message[0];"));
        WebElement element = js("return window._lastlog_message[1]");
        assertNotNull(element);
        assertEquals("visibleDiv", getAttributes(element).get("id"));
    }


    @Then("^there is an alert notifying the element as not visible$")
    public void there_is_an_alert_notifying_the_element_as_not_visible() throws Throwable {
        Wait.waitFor(Alert()::exists);
        assertEquals("GERMANIUM: Element with selector `#invisibleDiv` was found, but is not visible.",
                Alert().text());
        Alert().accept();
    }

    @Then("^in the log there is an error message notifying the element is invisible$")
    public void in_the_log_there_is_an_error_message_notifying_the_element_is_invisible() throws Throwable {
        assertEquals(2, ((List)js("return window._lasterror_message")).size());
        assertEquals("GERMANIUM: Element with selector `#invisibleDiv` was found, but is not visible.",
                js("return window._lasterror_message[0];"));
        WebElement element = js("return window._lasterror_message[1]");
        assertNotNull(element);
        assertEquals("invisibleDiv", getAttributes(element).get("id"));
    }

    @Then("^there is no alert present$")
    public void there_is_no_alert_present() throws Throwable {
        assertTrue(Alert().notExists());
    }

    @Then("^there is an alert notifying the element as non existing$")
    public void there_is_an_alert_notifying_the_element_as_non_existing() throws Throwable {
        Wait.waitFor(Alert()::exists);
        assertEquals("GERMANIUM: Unable to find element with selector `#notExistingDiv`.",
                Alert().text());
        Alert().accept();
    }

    @Then("^in the log there is an error message notifying the element as non existing$")
    public void in_the_log_there_is_an_error_message_notifying_the_element_as_non_existing() throws Throwable {
        assertEquals(1, ((List)js("return window._lasterror_message")).size());
        assertEquals("GERMANIUM: Unable to find element with selector `#notExistingDiv`.",
                js("return window._lasterror_message[0];"));

    }

    @When("^I highlight twice the element '(.*?)'$")
    public void i_highlight_twice_the_element_visibleDiv(String selector) throws Throwable {
        GermaniumApi.highlight(selector, Highlight.Console.NONE, 2, 1);
        GermaniumApi.highlight(selector, Highlight.Console.NONE, 2, 1);
        Thread.sleep(2000);
    }

    @Then("^the element highlight for the '(.*?)' is cleared correctly$")
    public void the_element_highlight_for_the_visibleDiv_is_cleared_correctly(String selector) throws Throwable {
        String outlineStyle = GermaniumApi.getStyle(selector, "outlineStyle");
        assertEquals("none", outlineStyle);
    }
}
