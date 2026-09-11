package org.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",
glue = "org.stepDefination", monochrome = true,
plugin = {"html:reports/cucumber.html"}, tags = "@ErrorValidation")
public class PurchaseOrderNGRunner extends AbstractTestNGCucumberTests {
}
