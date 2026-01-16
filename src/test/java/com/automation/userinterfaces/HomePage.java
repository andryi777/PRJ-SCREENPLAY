package com.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePage {

    public static final Target PRODUCTS_LINK = Target.the("enlace de productos")
            .located(By.xpath("//a[@href='/products']"));

    public static final Target CART_LINK = Target.the("enlace del carrito")
            .located(By.xpath("//a[@href='/view_cart']"));

    public static final Target HOME_VISIBLE = Target.the("indicador de página de inicio")
            .located(By.xpath("//h2[contains(text(),'Features Items')]"));

    public static final Target LOGGED_IN_AS = Target.the("usuario logueado")
            .located(By.xpath("//a[contains(text(),'Logged in as')]"));
}
