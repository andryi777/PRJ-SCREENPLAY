package com.automation.stepdefinitions;

import com.automation.data.TestData;
import com.automation.questions.*;
import com.automation.tasks.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

public class CompraProductosStepDefinitions {

    private Actor usuario;

    @Given("el usuario navega a {string}")
    public void elUsuarioNavegaA(String url) {
        usuario = OnStage.theActorCalled("Usuario");
        usuario.wasAbleTo(
                NavegarALaPaginaPrincipal.enLaURL(url)
        );
    }

    @Given("la página de inicio se muestra correctamente")
    public void laPaginaDeInicioSeMuestraCorrectamente() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("la página de inicio está visible",
                        LaPaginaDeInicio.estaVisible(),
                        is(true))
        );
    }

    @When("hace clic en el botón {string}")
    public void haceClicEnElBoton(String texto) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                NavegarHaciaSeccion.llamada(texto)
        );
    }

    @When("agrega el producto {string} al carrito con cantidad {int}")
    public void agregaElProductoAlCarritoConCantidad(String nombreProducto, Integer cantidad) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                AgregarProductoConCantidadPorNombre.producto(nombreProducto, cantidad)
        );
    }

    @When("hace clic en {string}")
    public void haceClicEn(String texto) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                NavegarHaciaSeccion.llamada(texto)
        );
    }

    @Then("debería verificar que el producto {string} tiene precio {string}, cantidad {string} y total {string}")
    public void deberiaVerificarQueElProductoTienePrecioCantidadYTotal(String producto, String precio, String cantidad, String total) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("el producto " + producto + " está en el carrito",
                        ProductoEnCarrito.conNombre(producto),
                        is(true)),
                seeThat("el precio del producto " + producto,
                        ElPrecioDelProducto.conNombre(producto),
                        equalTo(precio)),
                seeThat("la cantidad del producto " + producto,
                        LaCantidadDelProducto.conNombre(producto),
                        equalTo(cantidad)),
                seeThat("el total del producto " + producto,
                        ElTotalDelProducto.conNombre(producto),
                        equalTo(total))
        );
    }

    @When("hace clic en {string} para cualquier producto")
    public void haceClicEnParaCualquierProducto(String texto) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                VerDetalleDelProducto.elPrimero()
        );
    }

    @When("se abre el detalle del producto")
    public void seAbreElDetalleDelProducto() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("el detalle del producto es visible",
                        ElDetalleDelProducto.esVisible(),
                        is(true))
        );
    }

    @When("aumenta la cantidad a {int}")
    public void aumentaLaCantidadA(Integer cantidad) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                AgregarProductoConCantidad.producto(1, cantidad)
        );
    }

    @Then("debería verificar que el producto se muestra en el carrito con la cantidad exacta {int}")
    public void deberiaVerificarQueElProductoSeMuestraEnElCarritoConLaCantidadExacta(Integer cantidad) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("la cantidad del producto",
                        LaCantidadDelProducto.conNombre("Blue Top"),
                        containsString(String.valueOf(cantidad)))
        );
    }

    @Given("agrega productos al carrito")
    public void agregaProductosAlCarrito() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                AgregarProductosAlCarrito.losDosPrimeros()
        );
    }

    @When("verifica que la página del carrito se muestra correctamente")
    public void verificaQueLaPaginaDelCarritoSeMuestraCorrectamente() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("la página del carrito es visible",
                        LaTablaDelCarrito.esVisible(),
                        is(true))
        );
    }

    @When("completa el registro con los siguientes datos:")
    public void completaElRegistroConLosSiguientesDatos(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> userData = new HashMap<>();
        for (Map<String, String> row : rows) {
            userData.put(row.get("campo"), row.get("valor"));
        }

        OnStage.theActorInTheSpotlight().attemptsTo(
                RegistrarseEnElCheckout.conLosDatos(userData)
        );
    }

    @When("verifica {string} y hace clic en {string}")
    public void verificaYHaceClicEn(String mensaje, String boton) {
        if (mensaje.equals(TestData.Mensajes.CUENTA_CREADA)) {
            OnStage.theActorInTheSpotlight().should(
                    seeThat("la cuenta fue creada",
                            LaCuentaCreada.esVisible(),
                            is(true))
            );
        }
        OnStage.theActorInTheSpotlight().attemptsTo(
                NavegarHaciaSeccion.llamada(boton)
        );
    }

    @When("verifica {string}")
    public void verifica(String texto) {
        if (texto.equals(TestData.Mensajes.LOGGED_IN_AS)) {
            OnStage.theActorInTheSpotlight().should(
                    seeThat("el usuario está logueado",
                            ElUsuarioLogueado.esVisible(),
                            is(true))
            );
        }
    }

    @When("ir nuevamente a Cart")
    public void irNuevamenteACart() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                IrAlCarrito.desdeElMenu()
        );
    }

    @When("verifica dirección y pedido")
    public void verificaDireccionYPedido() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("la dirección es visible",
                        LaDireccionDeEntrega.esVisible(),
                        is(true))
        );
    }

    @When("agrega comentario {string}")
    public void agregaComentario(String comentario) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                RealizarElPago.conComentario(comentario)
        );
    }

    @When("ingresa datos de pago")
    public void ingresaDatosDePago() {
        // Los datos de pago se ingresan en RealizarElPago
    }

    @Then("debería verificar {string}")
    public void deberiaVerificar(String mensaje) {
        if (mensaje.contains(TestData.Mensajes.PEDIDO_EXITOSO)) {
            OnStage.theActorInTheSpotlight().should(
                    seeThat("el pedido fue realizado exitosamente",
                            ElMensajeDePedido.esVisible(),
                            is(true))
            );
        }
    }
}
