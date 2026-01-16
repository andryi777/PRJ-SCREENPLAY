package com.automation.tasks;

import com.automation.data.TestData;
import com.automation.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RealizarElPago implements Task {

    private final String comment;

    public RealizarElPago(String comment) {
        this.comment = comment;
    }

    public static RealizarElPago conComentario(String comment) {
        return instrumented(RealizarElPago.class, comment);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                // Agregar comentario
                Enter.theValue(comment).into(CheckoutPage.COMMENT_TEXTAREA),
                Click.on(CheckoutPage.PLACE_ORDER_BUTTON),

                // Ingresar datos de pago
                WaitUntil.the(CheckoutPage.NAME_ON_CARD, isVisible()).forNoMoreThan(5).seconds(),
                Enter.theValue(TestData.Pago.NOMBRE_TARJETA).into(CheckoutPage.NAME_ON_CARD),
                Enter.theValue(TestData.Pago.NUMERO_TARJETA).into(CheckoutPage.CARD_NUMBER),
                Enter.theValue(TestData.Pago.CVC).into(CheckoutPage.CVC),
                Enter.theValue(TestData.Pago.MES_EXPIRACION).into(CheckoutPage.EXPIRY_MONTH),
                Enter.theValue(TestData.Pago.ANIO_EXPIRACION).into(CheckoutPage.EXPIRY_YEAR),
                Click.on(CheckoutPage.PAY_AND_CONFIRM_BUTTON)
        );
    }
}
