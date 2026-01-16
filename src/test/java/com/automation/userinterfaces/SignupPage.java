package com.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class SignupPage {

    public static final Target GENDER_MR = Target.the("título Sr.")
            .located(By.id("id_gender1"));

    public static final Target PASSWORD = Target.the("contraseña")
            .located(By.id("password"));

    public static final Target FIRST_NAME = Target.the("primer nombre")
            .located(By.id("first_name"));

    public static final Target LAST_NAME = Target.the("apellido")
            .located(By.id("last_name"));

    public static final Target ADDRESS = Target.the("dirección")
            .located(By.id("address1"));

    public static final Target COUNTRY = Target.the("país")
            .located(By.id("country"));

    public static final Target STATE = Target.the("estado")
            .located(By.id("state"));

    public static final Target CITY = Target.the("ciudad")
            .located(By.id("city"));

    public static final Target ZIPCODE = Target.the("código postal")
            .located(By.id("zipcode"));

    public static final Target MOBILE_NUMBER = Target.the("número móvil")
            .located(By.id("mobile_number"));

    public static final Target CREATE_ACCOUNT_BUTTON = Target.the("botón crear cuenta")
            .located(By.xpath("//button[@data-qa='create-account']"));

    public static final Target ACCOUNT_CREATED = Target.the("cuenta creada")
            .located(By.xpath("//h2[@data-qa='account-created']"));

    public static final Target CONTINUE_BUTTON = Target.the("botón continuar")
            .located(By.xpath("//a[@data-qa='continue-button']"));
}
