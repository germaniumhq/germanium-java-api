package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.Context;
import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.selectors.XPath;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

public class GermaniumSelectorsXPath {
    @When("^I look for the following xpath selector: (.*?)$")
    public void i_look_for_the_following_xpath_selector_id_inputButton(String xpath) throws Throwable {
        WebElement element = GermaniumApi.S(new XPath(xpath)).element();
        Context.set("found_element", element);
    }
}
