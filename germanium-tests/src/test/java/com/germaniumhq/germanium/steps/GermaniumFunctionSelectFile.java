package com.germaniumhq.germanium.steps;

import com.germaniumhq.germanium.all.GermaniumApi;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.germaniumhq.germanium.all.GermaniumActions.click;
import static com.germaniumhq.germanium.all.GermaniumApi.selectFile;
import static com.germaniumhq.germanium.all.GermaniumSelectors.Button;
import static com.germaniumhq.germanium.all.GermaniumSelectors.InputFile;
import static com.germaniumhq.germanium.all.operations.actions.GetText.getText;
import static org.junit.Assert.assertTrue;

public class GermaniumFunctionSelectFile {
    @When("^I upload a file using the form from the page$")
    public void i_upload_a_file_using_the_form_from_the_page() throws Throwable {
        selectFile(InputFile(),
                   "src/test/resources/features/features/steps/test-data/upload_test_file.txt");

        click(Button());
        GermaniumApi.getGermanium().waitForPageToLoad(10);
    }

    @Then("^the file is uploaded successfully$")
    public void the_file_is_uploaded_successfully() throws Throwable {
        assertTrue(String.format("Unable to find `Uploaded` in %s", getText("body")),
                getText("body").contains("Uploaded '"));
    }
}
