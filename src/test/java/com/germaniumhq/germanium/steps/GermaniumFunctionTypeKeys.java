package com.germaniumhq.germanium.steps;

import cucumber.api.java.en.Then;

import static com.germaniumhq.germanium.all.GermaniumSelectors.Text;
import static org.junit.Assert.assertTrue;

public class GermaniumFunctionTypeKeys {
    @Then("^there is no error message\\.$")
    public void there_is_no_error_message() throws Throwable {
        assertTrue(Text("NO ERROR").exists());
    }
}
