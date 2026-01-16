package com.automation.tasks;

import com.automation.userinterfaces.HomePage;
import com.automation.userinterfaces.ProductDetailPage;
import com.automation.userinterfaces.ProductsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class VerDetalleDelProducto implements Task {

    private final int numeroProducto;

    public VerDetalleDelProducto(int numeroProducto) {
        this.numeroProducto = numeroProducto;
    }

    public static VerDetalleDelProducto numero(int numeroProducto) {
        return instrumented(VerDetalleDelProducto.class, numeroProducto);
    }

    public static VerDetalleDelProducto elPrimero() {
        return instrumented(VerDetalleDelProducto.class, 1);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(HomePage.PRODUCTS_LINK),
                WaitUntil.the(ProductsPage.viewProductButton(numeroProducto), isVisible()).forNoMoreThan(3).seconds(),
                Click.on(ProductsPage.viewProductButton(numeroProducto)),
                WaitUntil.the(ProductDetailPage.QUANTITY_INPUT, isVisible()).forNoMoreThan(3).seconds()
        );
    }
}
