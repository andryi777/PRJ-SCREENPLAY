package com.automation.tasks;

import com.automation.userinterfaces.CartPage;
import com.automation.userinterfaces.HomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IrAlCarrito implements Task {

    public static IrAlCarrito desdeElMenu() {
        return instrumented(IrAlCarrito.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(HomePage.CART_LINK),
                WaitUntil.the(CartPage.CART_INFO_TABLE, isVisible()).forNoMoreThan(5).seconds()
        );
    }
}
