package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.function.Supplier;

import static com.germaniumhq.germanium.all.GermaniumApi.S;
import static com.germaniumhq.germanium.all.Wait.waitFor;

public class FunctionWait {
    @When("^I wait on a closure that returns a closure that returns False$")
    public void i_wait_on_a_closure_that_returns_a_closure_that_returns_False() throws Throwable {
        try {
            waitFor(2, () -> {
                return (Supplier<Boolean>) () -> false;
            });
        } catch (Exception e) {
            Context.set("exception", e);
        }
    }

    @Then("^the wait function call failed$")
    public void the_wait_function_call_failed() throws Throwable {
        if (!(Context.get("exception") instanceof Exception)) {
            throw new IllegalStateException("No exception was caught.");
        }
    }

    @Then("^waiting for error to happen should pass$")
    public void waiting_for_error_to_happen_should_pass() throws Throwable {
        waitFor(S("div#errorContent"));
    }

    @Then("^waiting for success to happen should fail$")
    public void waiting_for_success_to_happen_should_fail() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^waiting for error or success to happen should pass with array callbacks$")
    public void waiting_for_error_or_success_to_happen_should_pass_with_array_callbacks() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^waiting for error or success to happen should pass with multiarg callbacks$")
    public void waiting_for_error_or_success_to_happen_should_pass_with_multiarg_callbacks() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I wait on a while_not that returns a closure that returns False$")
    public void i_wait_on_a_while_not_that_returns_a_closure_that_returns_False() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the wait function call passed$")
    public void the_wait_function_call_passed() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I wait on a while_not that returns a closure that throws$")
    public void i_wait_on_a_while_not_that_returns_a_closure_that_throws() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
