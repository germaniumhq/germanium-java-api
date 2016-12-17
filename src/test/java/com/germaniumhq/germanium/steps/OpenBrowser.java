package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.all.GermaniumApi;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class OpenBrowser {
    public static final Pattern URL_MATCHER = Pattern.compile("^(https?://)(.*?)(/.*)$");

    @Given("^I open the browser$")
    public void i_open_the_browser() throws Throwable {
        String browser = System.getenv("TEST_BROWSER");

        if (browser == null) {
            browser = "chrome";
        }

        GermaniumApi.openBrowser(browser)
                .iFrameSelector((germanium, iframeName) -> {
                    if ("iframe".equals(iframeName)) {
                        WebElement iframe = (WebElement) germanium.S("iframe").element();
                        germanium.switchTo().frame(iframe);
                    } else {
                        germanium.switchTo().defaultContent();
                    }
                })
                .get();
    }

    @When("^I go to '(.*?)'$")
    public void i_go_to(String page) {
        String testHost = System.getenv("TEST_HOST");

        if (testHost != null) {
            Matcher pagMatcher = URL_MATCHER.matcher(page);

            if (!pagMatcher.matches()) {
                throw new IllegalArgumentException(String.format(
                        "URL matcher couldn't match on the URL to open: %s, " +
                        "even though the TEST_HOST was specified via the " +
                        "environment variable as '%s'.",
                        page,
                        testHost));
            }

            page = pagMatcher.group(1) +
                    testHost +
                    pagMatcher.group(3);
        }

        GermaniumApi.getGermanium().get(page);
    }

    @Then("^the title of the page equals '(.*?)'$")
    public void is_title_of_the_page_equals(String text) {
        assertEquals(text, GermaniumApi.getGermanium().getTitle());
    }
}
