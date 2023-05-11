package testng.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        publish = true,

        features = "src/main/resources/features", glue = { "common", "stepdefinitions", "pageobjects", "Util",
        "testng.runner" },

        plugin = { "pretty", "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/cucumberreport.html"},

        monochrome = true)

public class RunCucumberTest extends AbstractTestNGCucumberTests {

}
