package stepsDefinitions;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class BaseOpen {

    @Managed(driver="chrome")
    public static WebDriver driver;
    public static String actor= "user";

    public BaseOpen(){
        OnStage.setTheStage(new OnlineCast());
        theActorCalled(actor).can(BrowseTheWeb.with(driver));
    }

}
