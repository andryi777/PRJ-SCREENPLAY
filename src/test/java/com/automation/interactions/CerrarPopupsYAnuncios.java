package com.automation.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CerrarPopupsYAnuncios implements Interaction {

    public static CerrarPopupsYAnuncios siAparecen() {
        return instrumented(CerrarPopupsYAnuncios.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Intentar cerrar popup interno del sitio
        try {
            WebElement adBox = driver.findElement(By.id("ad_position_box"));
            if (adBox.isDisplayed()) {
                WebElement dismissBtn = driver.findElement(By.id("dismiss-button"));
                dismissBtn.click();
            }
        } catch (Exception ignored) {
        }

        // Ocultar iframes de Google Ads que bloquean la interacción
        try {
            List<WebElement> adIframes = driver.findElements(
                    By.cssSelector("iframe[id^='aswift'], iframe[id*='google_ads'], ins.adsbygoogle, #google_ads_frame1")
            );
            for (WebElement iframe : adIframes) {
                js.executeScript("arguments[0].style.display='none';", iframe);
            }

            // También ocultar contenedores de anuncios
            List<WebElement> adContainers = driver.findElements(
                    By.cssSelector(".adsbygoogle, [id*='google_ads'], [class*='ad-container']")
            );
            for (WebElement container : adContainers) {
                js.executeScript("arguments[0].style.display='none';", container);
            }
        } catch (Exception ignored) {
        }
    }
}
