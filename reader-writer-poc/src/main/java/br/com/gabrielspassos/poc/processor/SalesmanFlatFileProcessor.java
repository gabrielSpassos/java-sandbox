package br.com.gabrielspassos.poc.processor;

import br.com.gabrielspassos.poc.model.Salesman;
import org.apache.camel.Exchange;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SalesmanFlatFileProcessor extends FlatFileProcessor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String message = getBody(exchange);
        Pattern salesmanPattern = Pattern.compile(getSalesmanRegex());
        List<Salesman> salesmenList = Stream.of(message)
                .flatMap(msg -> Arrays.stream(msg.split(getLineBreaker())))
                .map(line -> buildMatcher(line, salesmanPattern))
                .filter(Matcher::find)
                .map(this::buildSalesman)
                .collect(Collectors.toList());

        exchange.setProperty("salesmanList", salesmenList);
    }

    private Salesman buildSalesman(Matcher salesmanMatcher) {
        Salesman salesman = new Salesman();
        salesman.setCpf(salesmanMatcher.group(1));
        salesman.setName(salesmanMatcher.group(2));
        salesman.setSalary(new BigDecimal(salesmanMatcher.group(3)));
        return salesman;
    }

    private String getSalesmanRegex() {
        return propertiesReader.getProperty("flat-file.decoder.salesman-regex");
    }
}
