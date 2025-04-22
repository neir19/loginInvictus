package com.invitus.userInterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {
    public static final Target inputPhoneNumber=Target.the("campo numero de celular").located(By.id("phoneNumber"));
    public static final Target inputPassword=Target.the("campo contraseña").located(By.id("password"));
    public static final Target buttonLogin=Target.the("botón ingresar ").located(By.xpath("//*[ text() ='Ingresar']"));
    public static final Target labelInicio = Target.the("titulo de logueo").located(By.xpath("//h1[text()=' ¡Bienvenido a Invictus! ']"));
    public static  final Target labelLogueo= Target.the("label de logueo").located(By.xpath("//h2[contains(text(),'Bienvenido a tu espacio')]"));


    public static final Target LabelIUD= Target.the("campo del uid").located(By.xpath("//*[contains(text(),'UID')]"));
    //public static final Target LabelIUD= Target.the("campo del uid").located(By.xpath("//p[contains(text(),'UID')]"));

    


}
