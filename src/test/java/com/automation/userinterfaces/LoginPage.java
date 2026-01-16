package com.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {

    public static final Target SIGNUP_NAME = Target.the("nombre para registro")
            .located(By.name("name"));

    public static final Target SIGNUP_EMAIL = Target.the("email para registro")
            .located(By.xpath("//input[@data-qa='signup-email']"));

    public static final Target SIGNUP_BUTTON = Target.the("botón de registro")
            .located(By.xpath("//button[@data-qa='signup-button']"));

    public static final Target REGISTER_LOGIN_LINK = Target.the("enlace Register/Login")
            .located(By.xpath("//a[@href='/login']"));
}
