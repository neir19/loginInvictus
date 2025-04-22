package com.invitus.tasks;

import com.invitus.userInterfaces.GenericItems;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.questions.CurrentVisibility;
import org.openqa.selenium.Keys;

import static com.invitus.userInterfaces.GenericItems.loader;
import static com.invitus.userInterfaces.LoginPage.*;

public class LoginTask implements Task {

    private String user;
    private String password;

    public LoginTask(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            boolean isLoggedIn = false;
            int loginAttempts = 0;

            while (!isLoggedIn && loginAttempts < 2) {
                // Verificar si el elemento está disponible
                isLoggedIn = CurrentVisibility.of(labelLogueo).answeredBy(actor);


                // Si ya está logueado, salimos del bucle
                if (isLoggedIn) {
                    break;
                }

                // Intentar iniciar sesión
                actor.attemptsTo(
                        Enter.keyValues(this.user).into(inputPhoneNumber),
                        SendKeys.of(Keys.CONTROL + "a").into(inputPassword),
                        Enter.keyValues(this.password).into(inputPassword),
                        Click.on(buttonLogin)
                );

                // Esperar 16 segundos para que el sistema procese el inicio de sesión
                BrowseTheWeb.as(actor).waitFor(13).seconds();

                // Verificar nuevamente si está logueado después de intentar
                isLoggedIn =CurrentVisibility.of(labelLogueo).answeredBy(actor);


                // Verificar si hay un loader en la página
                if( CurrentVisibility.of(loader).answeredBy(actor)) {
                    // Refrescar la página si el loader está presente
                    BrowseTheWeb.as(actor).getDriver().navigate().refresh();
                }

                // Incrementar el contador de intentos
                loginAttempts++;
            }
        } catch (Exception e) {
            System.out.println("error logueandose: "+ e);
        }



    }
    public static  LoginTask login(String cellphone,String password){
        return Tasks.instrumented(LoginTask.class,cellphone,password);
    }
}
