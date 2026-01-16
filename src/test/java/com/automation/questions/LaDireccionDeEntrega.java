package com.automation.questions;

import com.automation.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class LaDireccionDeEntrega implements Question<Boolean> {

    public static LaDireccionDeEntrega esVisible() {
        return new LaDireccionDeEntrega();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(CheckoutPage.DELIVERY_ADDRESS).answeredBy(actor);
    }
}
