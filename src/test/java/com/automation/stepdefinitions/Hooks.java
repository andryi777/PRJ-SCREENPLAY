package com.automation.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.openqa.selenium.WebDriver;

/**
 * Hooks de Cucumber para setup y teardown de escenarios.
 * Centraliza la configuración del stage y limpieza de recursos.
 */
public class Hooks {

    @Before
    public void setUpStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @After
    public void tearDown() {
        if (OnStage.theActorInTheSpotlight() != null) {
            try {
                WebDriver driver = BrowseTheWeb.as(OnStage.theActorInTheSpotlight()).getDriver();
                if (driver != null) {
                    driver.quit();
                }
            } catch (Exception ignored) {
                // El driver puede no existir si el escenario falló antes de inicializarlo
            }
        }
        OnStage.drawTheCurtain();
    }
}
