package com.automation.tasks;

import com.automation.userinterfaces.CartPage;
import com.automation.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ProcederAlCheckout implements Task {

    public static ProcederAlCheckout desdeElCarrito() {
        return instrumented(ProcederAlCheckout.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CartPage.PROCEED_TO_CHECKOUT, isVisible()).forNoMoreThan(3).seconds(),
                Click.on(CartPage.PROCEED_TO_CHECKOUT),
                WaitUntil.the(CheckoutPage.DELIVERY_ADDRESS, isVisible()).forNoMoreThan(5).seconds()
        );
    }
}
