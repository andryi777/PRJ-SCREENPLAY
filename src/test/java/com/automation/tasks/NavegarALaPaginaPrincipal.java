package com.automation.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class NavegarALaPaginaPrincipal implements Task {

    private final String url;

    public NavegarALaPaginaPrincipal(String url) {
        this.url = url;
    }

    public static NavegarALaPaginaPrincipal enLaURL(String url) {
        return instrumented(NavegarALaPaginaPrincipal.class, url);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(url)
        );
    }
}
