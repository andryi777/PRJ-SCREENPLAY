package com.automation.questions;

import com.automation.userinterfaces.HomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class LaPaginaDeInicio implements Question<Boolean> {

    public static LaPaginaDeInicio estaVisible() {
        return new LaPaginaDeInicio();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(HomePage.HOME_VISIBLE).answeredBy(actor);
    }
}
