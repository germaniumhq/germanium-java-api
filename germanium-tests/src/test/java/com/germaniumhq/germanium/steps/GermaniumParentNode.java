package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.all.GermaniumApi;
import cucumber.api.java.en.When;

public class GermaniumParentNode {
    @When("^I get the parent node of the element with id 'childDiv'$")
    public void i_get_the_parent_node_of_the_element_with_id_childDiv() throws Throwable {
        Context.set("found_element", GermaniumApi.parentNode("#childDiv"));
    }
}
