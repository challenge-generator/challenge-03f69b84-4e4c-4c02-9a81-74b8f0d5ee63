package com.fintech.pagos.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.fintech.pagos.steps"},
    tags = "@pagos",
    plugin = {"pretty", "json:target/cucumber-reports/cucumber.json"},
    monochrome = true
)
public class RunCucumberTest {
}