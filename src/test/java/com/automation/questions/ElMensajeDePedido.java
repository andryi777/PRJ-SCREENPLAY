package com.automation.questions;

import com.automation.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class ElMensajeDePedido implements Question<Boolean> {

    public static ElMensajeDePedido esVisible() {
        return new ElMensajeDePedido();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(CheckoutPage.ORDER_PLACED_MESSAGE).answeredBy(actor);
    }
}
