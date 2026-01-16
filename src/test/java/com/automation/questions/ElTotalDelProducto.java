package com.automation.questions;

import com.automation.userinterfaces.CartPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class ElTotalDelProducto implements Question<String> {

    private final String productName;

    public ElTotalDelProducto(String productName) {
        this.productName = productName;
    }

    public static ElTotalDelProducto conNombre(String productName) {
        return new ElTotalDelProducto(productName);
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(CartPage.productTotalByName(productName)).answeredBy(actor).trim();
    }
}
