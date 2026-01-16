package com.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutPage {

    public static final Target DELIVERY_ADDRESS = Target.the("dirección de entrega")
            .located(By.id("address_delivery"));

    public static final Target BILLING_ADDRESS = Target.the("dirección de facturación")
            .located(By.id("address_invoice"));

    public static final Target COMMENT_TEXTAREA = Target.the("área de comentario")
            .located(By.name("message"));

    public static final Target PLACE_ORDER_BUTTON = Target.the("botón realizar pedido")
            .located(By.xpath("//a[contains(text(),'Place Order')]"));

    public static final Target NAME_ON_CARD = Target.the("nombre en la tarjeta")
            .located(By.name("name_on_card"));

    public static final Target CARD_NUMBER = Target.the("número de tarjeta")
            .located(By.name("card_number"));

    public static final Target CVC = Target.the("CVC")
            .located(By.name("cvc"));

    public static final Target EXPIRY_MONTH = Target.the("mes de expiración")
            .located(By.name("expiry_month"));

    public static final Target EXPIRY_YEAR = Target.the("año de expiración")
            .located(By.name("expiry_year"));

    public static final Target PAY_AND_CONFIRM_BUTTON = Target.the("botón pagar y confirmar")
            .located(By.id("submit"));

    public static final Target ORDER_PLACED_MESSAGE = Target.the("mensaje de pedido realizado")
            .located(By.xpath("//p[contains(text(),'Congratulations')]"));
}
