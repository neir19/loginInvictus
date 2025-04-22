import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "stepsDefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@loginExitoso",
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json"}
        

)

public class Runner {

}
