import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * This will run all the feature files that are found in the
 * `src/test/resources/features` folder.
 */
@RunWith(Cucumber.class)
@CucumberOptions(strict = true, snippets = SnippetType.UNDERSCORE, glue = "com.germaniumhq.germanium")
public class CucumberTest {
}
