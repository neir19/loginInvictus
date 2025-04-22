package stepsDefinitions;

import com.invitus.model.UserModel;
import com.invitus.tasks.LoadPageTask;
import com.invitus.tasks.LoginTask;
import com.invitus.userInterfaces.LoginPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;

import static com.invitus.userInterfaces.LoginPage.labelLogueo;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginSteps extends  BaseOpen {

    UserModel model= new UserModel();
    @Entonces("el sistema muestra mensaje del home")
    public void elSistemaMuestraMensajeDelHome() {
        theActorInTheSpotlight().attemptsTo(Ensure.that(LoginPage.labelLogueo).isDisplayed());
    }

    @Dado("el usuario ingresa a invictus")
    public void elUsuarioIngresaAInvictus() {
        theActorInTheSpotlight().attemptsTo(LoadPageTask.loadingPage());
    }

    @Cuando("ingresa usuario y contraseña")
    public void ingresaUsuarioYContraseña() {
        BrowseTheWeb.as(theActorInTheSpotlight()).waitFor(2).seconds();
        theActorInTheSpotlight().attemptsTo(LoginTask.login(model.getUSER(), model.getPASSWORD()));
    }

}
