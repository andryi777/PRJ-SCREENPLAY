package com.automation.questions;

import com.automation.userinterfaces.CartPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class ElPrecioDelProducto implements Question<String> {

    private final String productName;

    public ElPrecioDelProducto(String productName) {
        this.productName = productName;
    }

    public static ElPrecioDelProducto conNombre(String productName) {
        return new ElPrecioDelProducto(productName);
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(CartPage.productPriceByName(productName)).answeredBy(actor).trim();
    }
}
