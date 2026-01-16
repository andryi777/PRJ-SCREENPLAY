package com.automation.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/compra_productos.feature",
        glue = "com.automation.stepdefinitions",
        tags = "@examen",
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class ExamenTestRunner {
}
