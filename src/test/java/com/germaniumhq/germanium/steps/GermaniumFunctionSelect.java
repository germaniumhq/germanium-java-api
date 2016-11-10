package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.all.GermaniumApi;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GermaniumFunctionSelect {
    @When("^I select in the first select the entry with text '(.*?)'$")
    public void i_select_in_the_first_select_the_entry_with_text_A(String text) throws Throwable {
        GermaniumApi.select("#firstSelect", text);
    }

    @Then("^the value in the first select is '(.*?)'$")
    public void the_value_in_the_first_select_is_a_value(String value) throws Throwable {
        assertEquals(value, GermaniumApi.getValue("#firstSelect"));
    }

    @When("^I select in the first select the entry with value '(.*?)'$")
    public void i_select_in_the_first_select_the_entry_with_value_a_value(String value) throws Throwable {
        GermaniumApi.selectByValue("#firstSelect", value);
    }

    @When("^I select in the first select the entry with index (\\d+)$")
    public void i_select_in_the_first_select_the_entry_with_index(int index) throws Throwable {
        GermaniumApi.selectByIndex("#firstSelect", index);
    }

    @When("^I select in the multiline select the entries with texts B1 and B4$")
    public void i_select_in_the_multiline_select_the_entries_with_texts_B1_and_B4() throws Throwable {
        GermaniumApi.select("#multilineSelect", "B1", "B4");
    }

    @Then("^the values in the multiline select are 'b1value' and 'b4value'$")
    public void the_values_in_the_multiline_select_are_b_value_and_b_value() throws Throwable {
        List<String> allValues = GermaniumApi.getValueAll("#multilineSelect");
        assertEquals(Arrays.asList("b1value", "b4value"), allValues);
    }

    @When("^I select in the multiline select the entries with values b2value, b4value and b6value$")
    public void i_select_in_the_multiline_select_the_entries_with_values_b2_value_b4_value_and_b6_value() throws Throwable {
        GermaniumApi.selectByValue("#multilineSelect", "b2value", "b4value", "b6value");
    }

    @Then("^the values in the multiline select are 'b2value', 'b4value' and 'b6value'$")
    public void the_values_in_the_multiline_select_are_b2_value_b4_value_and_b6_value() throws Throwable {
        List<String> allValues = GermaniumApi.getValueAll("#multilineSelect");
        assertEquals(Arrays.asList("b2value", "b4value", "b6value"), allValues);
    }

    @When("^I select in the multiline select the entries with indexes 1, 3 and 5$")
    public void i_select_in_the_multiline_select_the_entries_with_indexes_and() throws Throwable {
        GermaniumApi.selectByIndex("#multilineSelect", 1, 3, 5);
    }

    @Then("^the values in the multiline select are 'b1value', 'b3value' and 'b5value'$")
    public void the_values_in_the_multiline_select_are_b1_value_b3_value_and_b5_value() throws Throwable {
        GermaniumApi.selectByValue("#multilineSelect", "b1value", "b3value", "b5value");
    }

    @When("^I deselect in the multiline select the entries with indexes 3 and 5$")
    public void i_deselect_in_the_multiline_select_the_entries_with_indexes_and() throws Throwable {
        GermaniumApi.deselectByIndex("#multilineSelect", 3, 5);
    }

    @When("^I select in the multiline select the entries with indexes 4$")
    public void i_select_in_the_multiline_select_the_entries_with_indexes() throws Throwable {
        GermaniumApi.selectByIndex("#multilineSelect", 4);
    }
}
