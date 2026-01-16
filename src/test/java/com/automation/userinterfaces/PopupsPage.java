package com.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PopupsPage {

    public static final Target AD_POSITION_BOX = Target.the("caja de anuncio")
            .located(By.id("ad_position_box"));

    public static final Target DISMISS_BUTTON = Target.the("botón cerrar anuncio")
            .located(By.id("dismiss-button"));

    public static final Target GOOGLE_ADS_IFRAME = Target.the("iframe de Google Ads")
            .located(By.cssSelector("iframe[id^='aswift'], iframe[id*='google_ads']"));
}
