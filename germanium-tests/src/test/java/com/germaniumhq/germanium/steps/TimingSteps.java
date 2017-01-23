package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class TimingSteps {
    @When("^I start timing the passed time$")
    public void i_start_timing_the_passed_time() throws Throwable {
        Context.set("start_time", System.currentTimeMillis());
    }

    @Then("^the passed time is less than ten seconds$")
    public void the_passed_time_is_less_than_ten_seconds() throws Throwable {
        Long initialTime = Context.get("start_time");
        assertTrue(System.currentTimeMillis() - initialTime < 10000L);
    }
}
