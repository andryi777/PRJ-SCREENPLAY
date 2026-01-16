package com.automation.tasks;

import com.automation.interactions.CerrarPopupsYAnuncios;
import com.automation.userinterfaces.ProductDetailPage;
import com.automation.userinterfaces.ProductsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.MoveMouse;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AgregarProductoConCantidadPorNombre implements Task {

    private final String nombreProducto;
    private final int cantidad;

    public AgregarProductoConCantidadPorNombre(String nombreProducto, int cantidad) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
    }

    public static AgregarProductoConCantidadPorNombre producto(String nombreProducto, int cantidad) {
        return instrumented(AgregarProductoConCantidadPorNombre.class, nombreProducto, cantidad);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(CerrarPopupsYAnuncios.siAparecen());

        Target producto = ProductsPage.productByName(nombreProducto);
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll al producto
        WebElement productoElement = producto.resolveFor(actor);
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", productoElement);

        // Hover sobre el producto para mostrar "View Product"
        actor.attemptsTo(
                MoveMouse.to(producto),
                WaitUntil.the(ProductsPage.viewProductByName(nombreProducto), isVisible()).forNoMoreThan(3).seconds()
        );

        // Click en "View Product" para ir al detalle
        WebElement viewProductBtn = ProductsPage.viewProductByName(nombreProducto).resolveFor(actor);
        js.executeScript("arguments[0].click();", viewProductBtn);

        // Esperar a que cargue el detalle y modificar cantidad
        actor.attemptsTo(
                WaitUntil.the(ProductDetailPage.QUANTITY_INPUT, isVisible()).forNoMoreThan(5).seconds(),
                Clear.field(ProductDetailPage.QUANTITY_INPUT),
                Enter.theValue(String.valueOf(cantidad)).into(ProductDetailPage.QUANTITY_INPUT),
                Click.on(ProductDetailPage.ADD_TO_CART_BUTTON)
        );
    }
}
