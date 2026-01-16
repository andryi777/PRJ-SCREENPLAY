package com.automation.tasks;

import com.automation.userinterfaces.CartPage;
import com.automation.userinterfaces.HomePage;
import com.automation.userinterfaces.LoginPage;
import com.automation.userinterfaces.ProductsPage;
import com.automation.userinterfaces.SignupPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class NavegarHaciaSeccion implements Task {

    private final String seccion;

    public NavegarHaciaSeccion(String seccion) {
        this.seccion = seccion;
    }

    public static NavegarHaciaSeccion llamada(String seccion) {
        return instrumented(NavegarHaciaSeccion.class, seccion);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Target elemento = obtenerElementoPorSeccion(seccion);
        if (elemento != null) {
            actor.attemptsTo(
                    WaitUntil.the(elemento, isVisible()).forNoMoreThan(3).seconds(),
                    Click.on(elemento)
            );
        }
    }

    private Target obtenerElementoPorSeccion(String seccion) {
        switch (seccion) {
            case "Products":
                return HomePage.PRODUCTS_LINK;
            case "Cart":
                return HomePage.CART_LINK;
            case "Continue Shopping":
                return ProductsPage.CONTINUE_SHOPPING_BUTTON;
            case "View Cart":
                return ProductsPage.VIEW_CART_BUTTON;
            case "Proceed To Checkout":
                return CartPage.PROCEED_TO_CHECKOUT;
            case "Register / Login":
                return LoginPage.REGISTER_LOGIN_LINK;
            case "Continue":
                return SignupPage.CONTINUE_BUTTON;
            default:
                return null;
        }
    }
}
