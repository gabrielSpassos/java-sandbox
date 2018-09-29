package br.com.gabrielspassos.poc.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "com.gabrielspassos.cliente")
@ContextConfiguration(loader = SpringBootContextLoader.class)
public class CucumberTestRunner {
}
