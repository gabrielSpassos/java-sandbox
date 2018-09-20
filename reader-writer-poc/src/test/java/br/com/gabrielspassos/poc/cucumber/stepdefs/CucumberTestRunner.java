package br.com.gabrielspassos.poc.cucumber.stepdefs;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "com.gabrielspassos.cliente")
public class CucumberTestRunner {
}
