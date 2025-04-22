package com.invitus.userInterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class GenericItems {
    public static Target loader = Target.the("loader").located(By.xpath("//*[@class='b-spinner__bounce b-spinner__bounce--one']"));
}
