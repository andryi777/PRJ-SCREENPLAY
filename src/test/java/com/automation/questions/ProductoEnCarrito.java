package com.automation.questions;

import com.automation.userinterfaces.CartPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class ProductoEnCarrito implements Question<Boolean> {

    private final String productName;

    public ProductoEnCarrito(String productName) {
        this.productName = productName;
    }

    public static ProductoEnCarrito conNombre(String productName) {
        return new ProductoEnCarrito(productName);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(CartPage.productInCartByName(productName)).answeredBy(actor);
    }
}
