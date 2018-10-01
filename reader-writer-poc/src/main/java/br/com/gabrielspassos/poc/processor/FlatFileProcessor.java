package br.com.gabrielspassos.poc.processor;

import br.com.gabrielspassos.poc.config.PropertiesReader;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class FlatFileProcessor implements Processor {

    public PropertiesReader propertiesReader;


    public FlatFileProcessor() {
        this.propertiesReader = PropertiesReader.getInstance();
    }

    public String getBody(Exchange exchange) {
        return exchange.getIn().getBody(String.class);
    }

    public Matcher buildMatcher(String message, Pattern pattern) {
        return pattern.matcher(message);
    }

    public String getLineBreaker() {
        return propertiesReader.getProperty("flat-file.decoder.line-breaker");
    }
}
