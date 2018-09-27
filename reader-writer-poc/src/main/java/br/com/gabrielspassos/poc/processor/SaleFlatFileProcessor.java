package br.com.gabrielspassos.poc.processor;

import br.com.gabrielspassos.poc.model.Item;
import br.com.gabrielspassos.poc.model.Sale;
import org.apache.camel.Exchange;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SaleFlatFileProcessor extends FlatFileProcessor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String message = getBody(exchange);
        Pattern salePattern = Pattern.compile(getSalesRegex());
        List<Sale> saleList = Stream.of(message)
                .flatMap(msg -> Arrays.stream(msg.split(getLineBreaker())))
                .map(line -> buildMatcher(line, salePattern))
                .filter(Matcher::find)
                .map(this::buildSale)
                .collect(Collectors.toList());
        exchange.setProperty("saleList", saleList);
    }

    private Sale buildSale(Matcher saleMatcher) {
        Sale sale = new Sale();
        sale.setId(Long.valueOf(saleMatcher.group(1)));
        sale.setItems(buildItensList(saleMatcher.group(2)));
        sale.setSalesmanName(saleMatcher.group(3));
        return sale;
    }

    private List<Item> buildItensList(String itemMessage) {
        Pattern itemPattern = Pattern.compile(getItemRegex());
        return Stream.of(itemMessage)
                .flatMap(msg -> Arrays.stream(msg.split(getItemLineBreaker())))
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

    private String getSalesRegex() {
        return propertiesReader.getProperty("flat-file.decoder.sales-regex");
    }

    private String getItemLineBreaker() {
        return propertiesReader.getProperty("flat-file.decoder.item.line-breaker");
    }

    private String getItemRegex() {
        return propertiesReader.getProperty("flat-file.decoder.item-regex");
    }
}
