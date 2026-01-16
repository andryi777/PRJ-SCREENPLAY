package com.automation.questions;

import com.automation.userinterfaces.SignupPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class LaCuentaCreada implements Question<Boolean> {

    public static LaCuentaCreada esVisible() {
        return new LaCuentaCreada();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(SignupPage.ACCOUNT_CREATED).answeredBy(actor);
    }
}
