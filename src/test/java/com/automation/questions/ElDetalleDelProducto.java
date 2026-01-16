package com.automation.questions;

import com.automation.userinterfaces.ProductDetailPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class ElDetalleDelProducto implements Question<Boolean> {

    public static ElDetalleDelProducto esVisible() {
        return new ElDetalleDelProducto();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(ProductDetailPage.QUANTITY_INPUT).answeredBy(actor);
    }
}
