package com.invitus.tasks;

import com.invitus.DB.QuerriesInvictus;
import com.invitus.Utils.SeparateStringForSpace;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.model.environment.SystemEnvironmentVariables;

import static com.invitus.userInterfaces.LoginPage.LabelIUD;

public class LoadPageTask implements Task {


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(SystemEnvironmentVariables.createEnvironmentVariables().getProperty("url_home_page")));
        String LabelText= LabelIUD.resolveFor(actor).getText();
        String[] uid= SeparateStringForSpace.separateString(LabelText);
        actor.attemptsTo(QuerriesInvictus.updateUID(uid[1]));
        actor.attemptsTo(Open.url(SystemEnvironmentVariables.createEnvironmentVariables().getProperty("url_home_page")));
    }
    public static LoadPageTask  loadingPage(){
        return Tasks.instrumented(LoadPageTask.class);
    }
}
