package com.automation.tasks;

import com.automation.interactions.CerrarPopupsYAnuncios;
import com.automation.userinterfaces.HomePage;
import com.automation.userinterfaces.ProductsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.MoveMouse;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AgregarProductosAlCarrito implements Task {

    private static final String PRIMER_PRODUCTO = "Blue Top";
    private static final String SEGUNDO_PRODUCTO = "Men Tshirt";

    public static AgregarProductosAlCarrito losDosPrimeros() {
        return instrumented(AgregarProductosAlCarrito.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(HomePage.PRODUCTS_LINK)
        );

        // Agregar primer producto
        agregarProducto(actor, PRIMER_PRODUCTO);
        actor.attemptsTo(
                WaitUntil.the(ProductsPage.CONTINUE_SHOPPING_BUTTON, isClickable()).forNoMoreThan(3).seconds(),
                Click.on(ProductsPage.CONTINUE_SHOPPING_BUTTON)
        );

        // Agregar segundo producto
        agregarProducto(actor, SEGUNDO_PRODUCTO);
        actor.attemptsTo(
                WaitUntil.the(ProductsPage.VIEW_CART_BUTTON, isVisible()).forNoMoreThan(3).seconds(),
                Click.on(ProductsPage.VIEW_CART_BUTTON)
        );
    }

    private <T extends Actor> void agregarProducto(T actor, String nombreProducto) {
        actor.attemptsTo(CerrarPopupsYAnuncios.siAparecen());

        Target producto = ProductsPage.productByName(nombreProducto);
        Target botonAgregar = ProductsPage.addToCartByProductName(nombreProducto);
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement productoElement = producto.resolveFor(actor);
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", productoElement);

        actor.attemptsTo(
                MoveMouse.to(producto),
                WaitUntil.the(botonAgregar, isVisible()).forNoMoreThan(3).seconds()
        );

        WebElement boton = botonAgregar.resolveFor(actor);
        js.executeScript("arguments[0].click();", boton);
    }
}
