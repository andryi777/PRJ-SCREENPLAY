package com.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartPage {

    public static Target productInCartByName(String productName) {
        return Target.the("producto " + productName + " en el carrito")
                .located(By.xpath("//td[@class='cart_description']//a[text()='" + productName + "']/ancestor::tr"));
    }

    public static Target productPriceByName(String productName) {
        return Target.the("precio del producto " + productName)
                .located(By.xpath("//td[@class='cart_description']//a[text()='" + productName + "']/ancestor::tr//td[@class='cart_price']/p"));
    }

    public static Target productQuantityByName(String productName) {
        return Target.the("cantidad del producto " + productName)
                .located(By.xpath("//td[@class='cart_description']//a[text()='" + productName + "']/ancestor::tr//button[@class='disabled']"));
    }

    public static Target productTotalByName(String productName) {
        return Target.the("total del producto " + productName)
                .located(By.xpath("//td[@class='cart_description']//a[text()='" + productName + "']/ancestor::tr//td[@class='cart_total']/p"));
    }

    public static final Target PROCEED_TO_CHECKOUT = Target.the("botón proceder al checkout")
            .located(By.xpath("//a[contains(text(),'Proceed To Checkout')]"));

    public static final Target CART_INFO_TABLE = Target.the("tabla de información del carrito")
            .located(By.id("cart_info_table"));
}
