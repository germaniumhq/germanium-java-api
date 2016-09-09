package com.germaniumhq.germanium;

import com.germaniumhq.germanium.all.Wait;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.function.Supplier;

public class FunctionWait {
    @When("^I wait on a closure that returns a closure that returns False$")
    public void i_wait_on_a_closure_that_returns_a_closure_that_returns_False() throws Throwable {
        try {
            Wait.waitFor(2, () -> {
                return (Supplier<Boolean>) () -> {
                    return false;
                };
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
}
