package com.automation.questions;

import com.automation.userinterfaces.CartPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class LaTablaDelCarrito implements Question<Boolean> {

    public static LaTablaDelCarrito esVisible() {
        return new LaTablaDelCarrito();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(CartPage.CART_INFO_TABLE).answeredBy(actor);
    }
}
