package com.automation.tasks;

import com.automation.data.TestData;
import com.automation.userinterfaces.LoginPage;
import com.automation.userinterfaces.SignupPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RegistrarseEnElCheckout implements Task {

    private final Map<String, String> userData;

    public RegistrarseEnElCheckout(Map<String, String> userData) {
        this.userData = userData;
    }

    public static RegistrarseEnElCheckout conLosDatos(Map<String, String> userData) {
        return instrumented(RegistrarseEnElCheckout.class, userData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String email = userData.get("email").replace("{timestamp}", timestamp);
        String[] nombreCompleto = userData.get("nombre").split(" ");
        String firstName = nombreCompleto[0];
        String lastName = nombreCompleto.length > 1 ? nombreCompleto[1] : "";

        actor.attemptsTo(
                // Ir a registro
                WaitUntil.the(LoginPage.SIGNUP_NAME, isVisible()).forNoMoreThan(5).seconds(),
                Enter.theValue(userData.get("nombre")).into(LoginPage.SIGNUP_NAME),
                Enter.theValue(email).into(LoginPage.SIGNUP_EMAIL),
                Click.on(LoginPage.SIGNUP_BUTTON),

                // Llenar formulario de registro
                WaitUntil.the(SignupPage.GENDER_MR, isVisible()).forNoMoreThan(5).seconds(),
                Click.on(SignupPage.GENDER_MR),
                Enter.theValue(userData.get("password")).into(SignupPage.PASSWORD),
                Enter.theValue(firstName).into(SignupPage.FIRST_NAME),
                Enter.theValue(lastName).into(SignupPage.LAST_NAME),
                Enter.theValue(TestData.Usuario.DIRECCION_DEFAULT).into(SignupPage.ADDRESS),
                SelectFromOptions.byVisibleText(TestData.Usuario.PAIS_DEFAULT).from(SignupPage.COUNTRY),
                Enter.theValue(TestData.Usuario.ESTADO_DEFAULT).into(SignupPage.STATE),
                Enter.theValue(TestData.Usuario.CIUDAD_DEFAULT).into(SignupPage.CITY),
                Enter.theValue(TestData.Usuario.CODIGO_POSTAL_DEFAULT).into(SignupPage.ZIPCODE),
                Enter.theValue(TestData.Usuario.TELEFONO_DEFAULT).into(SignupPage.MOBILE_NUMBER),
                Click.on(SignupPage.CREATE_ACCOUNT_BUTTON)
        );

        actor.remember("userEmail", email);
    }
}
