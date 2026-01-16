package com.automation.questions;

import com.automation.userinterfaces.HomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class ElUsuarioLogueado implements Question<Boolean> {

    public static ElUsuarioLogueado esVisible() {
        return new ElUsuarioLogueado();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(HomePage.LOGGED_IN_AS).answeredBy(actor);
    }
}
