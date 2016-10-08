package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.all.GermaniumApi;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

/*
        if iframe_name == 'iframe':
            iframe = germanium.S('iframe').element()
            germanium.switch_to_frame(iframe)
        else:
            germanium.switch_to_default_content()

 */
public class OpenBrowser {
    @Given("^I open the browser$")
    public void i_open_the_browser() throws Throwable {
        // FIXME: read the browser from the file name
        GermaniumApi.openBrowser()
                .iFrameSelector((germanium, iframeName) -> {
                    if ("iframe".equals(iframeName)) {
                        WebElement iframe = (WebElement) germanium.S("iframe").element();
                        germanium.switchTo().frame(iframe);
                    } else {
                        germanium.switchTo().defaultContent();
                    }
                })
                .browser("chrome")
                .get();
    }

    @When("^I go to '(.*?)'$")
    public void i_go_to(String url) {
        GermaniumApi.getGermanium().get(url);
    }

    @Then("^the title of the page equals '(.*?)'$")
    public void is_title_of_the_page_equals(String text) {
        assertEquals(text, GermaniumApi.getGermanium().getTitle());
    }
}
