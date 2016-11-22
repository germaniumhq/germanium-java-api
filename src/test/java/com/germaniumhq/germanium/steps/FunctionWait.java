package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.function.Supplier;

import static com.germaniumhq.germanium.all.GermaniumApi.S;
import static com.germaniumhq.germanium.all.Wait.waitFor;
import static com.germaniumhq.germanium.all.Wait.whileNot;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FunctionWait {
    @When("^I wait on a closure that returns a closure that returns False$")
    public void i_wait_on_a_closure_that_returns_a_closure_that_returns_False() throws Throwable {
        try {
            waitFor(2, () -> {
                return (Supplier<Boolean>) () -> false;
            });
            Context.set("wait_function_call_failed", false);
        } catch (Exception e) {
            Context.set("wait_function_call_failed", true);
        }
    }

    @Then("^the wait function call failed$")
    public void the_wait_function_call_failed() throws Throwable {
        assertTrue(Context.get("wait_function_call_failed"));
    }

    @Then("^waiting for error to happen should pass$")
    public void waiting_for_error_to_happen_should_pass() throws Throwable {
        waitFor(S("div#errorContent"));
    }

    @Then("^waiting for success to happen should fail$")
    public void waiting_for_success_to_happen_should_fail() throws Throwable {
        boolean waitThrewException = true;

        try {
            whileNot(S("div#errorContent"))
                .waitFor(S("div#successContent"));
            waitThrewException = false;
        } catch (Exception e) {
            // ignore on purpose, we check it after
        }

        if (!S("div#errorContent").exists()) {
            assertTrue(false);
        }

        if (!waitThrewException) {
            assertTrue(false);
        }
    }

    @Then("^waiting for error or success to happen should pass with array callbacks$")
    public void waiting_for_error_or_success_to_happen_should_pass_with_array_callbacks() throws Throwable {
        // in the java version, the array callback is actually the vararg itself.
        waitFor(S("div#successContent"), S("div#errorContent"));
    }

    @Then("^waiting for error or success to happen should pass with multiarg callbacks$")
    public void waiting_for_error_or_success_to_happen_should_pass_with_multiarg_callbacks() throws Throwable {
        waitFor(S("div#successContent"), S("div#errorContent"));
    }

    @When("^I wait on a while_not that returns a closure that returns False$")
    public void i_wait_on_a_while_not_that_returns_a_closure_that_returns_False() throws Throwable {
        try {
            whileNot(() -> (Supplier) () -> false)
                .waitFor(() -> true);
            Context.set("wait_function_call_failed", false);
        } catch (Exception e) {
            Context.set("wait_function_call_failed", true);
        }
    }

    @Then("^the wait function call passed$")
    public void the_wait_function_call_passed() throws Throwable {
        assertFalse(Context.get("wait_function_call_failed"));
    }

    @When("^I wait on a while_not that returns a closure that throws$")
    public void i_wait_on_a_while_not_that_returns_a_closure_that_throws() throws Throwable {
        try {
            whileNot(() -> (Supplier) () -> {
                throw new IllegalArgumentException("e");
            }).waitFor(() -> true);
            Context.set("wait_function_call_failed", false);
        } catch (Exception e) {
            Context.set("wait_function_call_failed", true);
        }

    }

    @When("^I wait with a while_not that has a CSS locator built with S should pass$")
    public void i_wait_with_a_while_not_that_has_a_CSS_locator_built_with_S_should_pass() throws Throwable {
        whileNot(S("div#errorContent"))
            .waitFor(() -> true);
    }
}
