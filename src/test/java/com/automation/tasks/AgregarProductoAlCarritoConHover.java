package com.automation.tasks;

import com.automation.interactions.CerrarPopupsYAnuncios;
import com.automation.userinterfaces.ProductsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.MoveMouse;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AgregarProductoAlCarritoConHover implements Task {

    private final String nombreProducto;

    public AgregarProductoAlCarritoConHover(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public static AgregarProductoAlCarritoConHover elProducto(String nombreProducto) {
        return instrumented(AgregarProductoAlCarritoConHover.class, nombreProducto);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Cerrar anuncios que puedan bloquear
        actor.attemptsTo(CerrarPopupsYAnuncios.siAparecen());

        Target producto = ProductsPage.productByName(nombreProducto);
        Target botonAgregar = ProductsPage.addToCartByProductName(nombreProducto);
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll y hover con JavaScript (más rápido)
        WebElement productoElement = producto.resolveFor(actor);
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", productoElement);

        actor.attemptsTo(
                MoveMouse.to(producto),
                WaitUntil.the(botonAgregar, isVisible()).forNoMoreThan(3).seconds()
        );

        // Click con JavaScript (más robusto y rápido)
        WebElement boton = botonAgregar.resolveFor(actor);
        js.executeScript("arguments[0].click();", boton);
    }
}
