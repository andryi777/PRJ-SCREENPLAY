package com.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductsPage {

    public static final Target CONTINUE_SHOPPING_BUTTON = Target.the("botón continuar comprando")
            .located(By.xpath("//button[contains(text(),'Continue Shopping')]"));

    public static final Target VIEW_CART_BUTTON = Target.the("botón ver carrito")
            .located(By.xpath("//u[text()='View Cart']"));

    public static Target viewProductButton(int productNumber) {
        return Target.the("botón ver producto " + productNumber)
                .located(By.xpath("(//a[contains(@href,'/product_details/')])[" + productNumber + "]"));
    }

    public static Target productByName(String productName) {
        return Target.the("producto " + productName)
                .located(By.xpath("//div[@class='productinfo text-center']/p[text()='" + productName + "']/ancestor::div[@class='product-image-wrapper']"));
    }

    public static Target addToCartByProductName(String productName) {
        return Target.the("agregar " + productName + " al carrito")
                .located(By.xpath("//div[@class='productinfo text-center']/p[text()='" + productName + "']/ancestor::div[@class='product-image-wrapper']//a[contains(@class,'add-to-cart')]"));
    }

    public static Target viewProductByName(String productName) {
        return Target.the("ver detalle de " + productName)
                .located(By.xpath("//div[@class='productinfo text-center']/p[text()='" + productName + "']/ancestor::div[@class='product-image-wrapper']//a[contains(text(),'View Product')]"));
    }
}
