package com.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductDetailPage {

    public static final Target QUANTITY_INPUT = Target.the("campo de cantidad")
            .located(By.id("quantity"));

    public static final Target ADD_TO_CART_BUTTON = Target.the("botón agregar al carrito")
            .located(By.xpath("//button[@type='button' and contains(@class,'cart')]"));

    public static final Target PRODUCT_NAME = Target.the("nombre del producto")
            .located(By.xpath("//div[@class='product-information']//h2"));

    public static final Target PRODUCT_PRICE = Target.the("precio del producto")
            .located(By.xpath("//div[@class='product-information']//span/span"));
}
