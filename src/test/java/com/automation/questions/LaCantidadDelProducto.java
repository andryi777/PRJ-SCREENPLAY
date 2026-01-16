package com.automation.questions;

import com.automation.userinterfaces.CartPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class LaCantidadDelProducto implements Question<String> {

    private final String productName;

    public LaCantidadDelProducto(String productName) {
        this.productName = productName;
    }

    public static LaCantidadDelProducto conNombre(String productName) {
        return new LaCantidadDelProducto(productName);
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(CartPage.productQuantityByName(productName)).answeredBy(actor);
    }
}
