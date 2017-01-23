package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.selectors.AbstractSelector;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static com.germaniumhq.germanium.all.GermaniumApi.S;
import static com.germaniumhq.germanium.all.GermaniumSelectors.*;
import static org.junit.Assert.assertNotNull;

public class GermaniumSelectorsTableRow {
    @When("^I search for a TableRow with a CheckBox left of text \"([^\"]*)\"$")
    public void i_search_for_a_TableRow_with_a_CheckBox_left_of_text(String arg1) throws Throwable {

        AbstractSelector selector = Element("tr")
            .containingAll(Element("td").containsText("Surname"))
            .containingAll(CheckBox());

        WebElement element = S(selector).element();

        assertNotNull(element);

        Context.set("found_element", element);
    }

    @When("^I search for a TableRow with a Button that has label '(.*?)'$")
    public void i_search_for_a_TableRow_with_a_Button_that_has_label_edit(String label) throws Throwable {
        AbstractSelector selector = Element("tr")
                .containing(Button("edit"));

        WebElement element = S(selector).element();

        assertNotNull(element);

        Context.set("found_element", element);
    }

    @When("^I search for a TableRow with a custom XPath that is (.*?)$")
    public void i_search_for_a_TableRow_with_a_custom_XPath_that_is_input_id_nameInput(String customXPath) throws Throwable {
        AbstractSelector selector = Element("tr")
            .containing(XPath(customXPath));
        WebElement element = S(selector).element();

        assertNotNull(element);

        Context.set("found_element", element);
    }
}
