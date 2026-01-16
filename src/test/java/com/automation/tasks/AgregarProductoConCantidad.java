package com.automation.tasks;

import com.automation.userinterfaces.HomePage;
import com.automation.userinterfaces.ProductDetailPage;
import com.automation.userinterfaces.ProductsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AgregarProductoConCantidad implements Task {

    private final int productNumber;
    private final int quantity;

    public AgregarProductoConCantidad(int productNumber, int quantity) {
        this.productNumber = productNumber;
        this.quantity = quantity;
    }

    public static AgregarProductoConCantidad producto(int productNumber, int quantity) {
        return instrumented(AgregarProductoConCantidad.class, productNumber, quantity);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(HomePage.PRODUCTS_LINK),
                WaitUntil.the(ProductsPage.viewProductButton(productNumber), isVisible()).forNoMoreThan(5).seconds(),
                Click.on(ProductsPage.viewProductButton(productNumber)),
                WaitUntil.the(ProductDetailPage.QUANTITY_INPUT, isVisible()).forNoMoreThan(5).seconds(),
                Clear.field(ProductDetailPage.QUANTITY_INPUT),
                Enter.theValue(String.valueOf(quantity)).into(ProductDetailPage.QUANTITY_INPUT),
                Click.on(ProductDetailPage.ADD_TO_CART_BUTTON),
                WaitUntil.the(ProductsPage.VIEW_CART_BUTTON, isVisible()).forNoMoreThan(3).seconds(),
                Click.on(ProductsPage.VIEW_CART_BUTTON)
        );
    }
}
