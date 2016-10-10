package com.germaniumhq.germanium.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.germaniumhq.germanium.all.GermaniumActions.typeKeys;
import static com.germaniumhq.germanium.all.GermaniumSelectors.Alert;
import static com.germaniumhq.germanium.all.GermaniumSelectors.Element;
import static com.germaniumhq.germanium.all.Wait.waitFor;
import static org.junit.Assert.assertEquals;

public class GermaniumAlertAlert {

    @Then("^there is an alert that exists$")
    public void there_is_an_alert_that_exists() throws Throwable {
        waitFor(Alert()::exists);
    }

    @Then("^the text of the alert is \"([^\"]*)\"$")
    public void the_text_of_the_alert_is(String arg1) throws Throwable {
        assertEquals(arg1, Alert().text());
    }

    @Then("^the text of the page is$")
    public void the_text_of_the_page_is(String text) throws Throwable {
        assertEquals(text,
                Element("body").element().getText());
    }

    @Then("^I close the alert dialog by cancel$")
    public void i_close_the_alert_dialog_by_cancel() throws Throwable {
        Alert().dismiss();
    }

    @When("^I write into the alert '(.*?)'$")
    public void i_write_into_the_alert_prompt_input(String text) throws Throwable {
        typeKeys(text, Alert());
    }

    @When("^I close the alert dialog by ok$")
    public void i_close_the_alert_dialog_by_ok() throws Throwable {
        Alert().accept();
    }

    @When("^I write into the alert using Alert\\(\\)\\.send_keys 'prompt input'$")
    public void i_write_into_the_alert_using_Alert_send_keys_prompt_input() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I write into the alert using type_keys\\(\\.\\., 'alert'\\) 'prompt input'$")
    public void i_write_into_the_alert_using_type_keys_alert_prompt_input() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I type_keys 'prompt input'$")
    public void i_type_keys_prompt_input() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
