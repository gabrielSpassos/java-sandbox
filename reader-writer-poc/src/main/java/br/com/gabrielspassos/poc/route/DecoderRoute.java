package br.com.gabrielspassos.poc.route;

import br.com.gabrielspassos.poc.model.*;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DecoderRoute extends RouteBuilder {

    private static final String SALESMAN_REGEX = "001ç([0-9]+)ç([ a-zA-Z á]+)ç([-+]?[0-9]*\\.?[0-9]*)";
    private static final String CUSTOMER_REGEX = "002ç([0-9]+)ç([ a-zA-Z á]+)ç([ a-zA-Z á]+)";
    private static final String SALE_REGEX = "003ç([0-9]+)ç(.*)ç(.*)";
    private static final String ITEM_REGEX = "([-+]?[0-9]*\\.?[0-9]*)-([-+]?[0-9]*\\.?[0-9]*)-([-+]?[0-9]*\\.?[0-9]*)";

    @Override
    public void configure() {
        from("file://data/in/?fileName=relatory.dat&charset=utf-8&noop=true&delete=true")
                .routeId("decoder")
                .to("file://data/processed/?fileName=relatory.${date:now:yyyy-MM-dd}.dat&charset=utf-8")
                .convertBodyTo(String.class)
                .process(this::createSalesmanList)
                .process(this::createCustomerList)
                .process(this::createSaleList)
                .process(this::buildRelatory)
                .to("direct:analysesRelatory")
                .end();
    }

    private void createSalesmanList(Exchange exchange) {
        String message = getBody(exchange);
        Pattern salesmanPattern = Pattern.compile(SALESMAN_REGEX);
        List<Salesman> salesmenList = Stream.of(message)
                .flatMap(msg -> Arrays.stream(msg.split("\n")))
                .map(line -> buildMatcher(line, salesmanPattern))
                .filter(Matcher::find)
                .map(this::buildSalesman)
                .collect(Collectors.toList());

        exchange.setProperty("salesmanList", salesmenList);
    }

    private void createCustomerList(Exchange exchange) {
        String message = getBody(exchange);
        Pattern customerPattern = Pattern.compile(CUSTOMER_REGEX);
        List<Customer> customerList = Stream.of(message)
                .flatMap(msg -> Arrays.stream(msg.split("\n")))
                .map(line -> buildMatcher(line, customerPattern))
                .filter(Matcher::find)
                .map(this::buildCustomer)
                .collect(Collectors.toList());
        exchange.setProperty("customerList", customerList);
    }

    private void createSaleList(Exchange exchange) {
        String message = getBody(exchange);
        Pattern salePattern = Pattern.compile(SALE_REGEX);
        List<Sale> saleList = Stream.of(message)
                .flatMap(msg -> Arrays.stream(msg.split("\n")))
                .map(line -> buildMatcher(line, salePattern))
                .filter(Matcher::find)
                .map(this::buildSale)
                .collect(Collectors.toList());
        exchange.setProperty("saleList", saleList);
    }

    private String getBody(Exchange exchange) {
        return exchange.getIn().getBody(String.class);
    }

    private Matcher buildMatcher(String message, Pattern pattern) {
        return pattern.matcher(message);
    }

    private Salesman buildSalesman(Matcher salesmanMatcher) {
        Salesman salesman = new Salesman();
        salesman.setCpf(salesmanMatcher.group(1));
        salesman.setName(salesmanMatcher.group(2));
        salesman.setSalary(new BigDecimal(salesmanMatcher.group(3)));
        return salesman;
    }

    private Customer buildCustomer(Matcher customerMatcher) {
        Customer customer = new Customer();
        customer.setCnpj(customerMatcher.group(1));
        customer.setName(customerMatcher.group(2));
        customer.setBusinessArea(customerMatcher.group(3));
        return customer;
    }

    private Sale buildSale(Matcher saleMatcher) {
        Sale sale = new Sale();
        sale.setId(Long.valueOf(saleMatcher.group(1)));
        sale.setItems(buildItensList(saleMatcher.group(2)));
        sale.setSalesmanName(saleMatcher.group(3));
        return sale;
    }

    private List<Item> buildItensList(String itemMessage) {
        Pattern itemPattern = Pattern.compile(ITEM_REGEX);
        return Stream.of(itemMessage)
                .flatMap(msg -> Arrays.stream(msg.split(",")))
                .map(line -> buildMatcher(line, itemPattern))
                .filter(Matcher::find)
                .map(this::buildItem)
                .collect(Collectors.toList());
    }

    private Item buildItem(Matcher itemMatcher) {
        Item item = new Item();
        item.setId(Long.valueOf(itemMatcher.group(1)));
        item.setQuantity(Integer.valueOf(itemMatcher.group(2)));
        item.setPrice(Double.valueOf(itemMatcher.group(3)));
        return item;
    }

    private void buildRelatory(Exchange exchange) {
        List<Sale> saleList = exchange.getProperty("saleList", List.class);
        List<Salesman> salesmanList = exchange.getProperty("salesmanList", List.class);
        List<Customer> customerList = exchange.getProperty("customerList", List.class);

        Relatory relatory = new Relatory();
        relatory.setSalesmens(salesmanList);
        relatory.setSales(saleList);
        relatory.setCustomers(customerList);

        exchange.setProperty("relatory", relatory);
    }
}
