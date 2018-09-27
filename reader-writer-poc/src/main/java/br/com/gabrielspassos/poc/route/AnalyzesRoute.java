package br.com.gabrielspassos.poc.route;

import br.com.gabrielspassos.poc.model.Relatory;
import br.com.gabrielspassos.poc.model.Result;
import br.com.gabrielspassos.poc.model.Sale;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import java.util.List;

public class AnalyzesRoute extends RouteBuilder {

    @Override
    public void configure() {
        onException(Exception.class)
                .maximumRedeliveries(3)
                .handled(true)
                .log("Tem um erro aqui");

        from("direct:analysesRelatory")
                .routeId("analysesRelatory")
                .process(this::calculateCustumersNumber)
                .process(this::calculateSellersNumber)
                .process(this::getMostExpensiveSale)
                .process(this::getWorstSalesmanName)
                .process(this::buildResult)
                .to("direct:writer")
                .end();
    }

    private void calculateCustumersNumber(Exchange exchange) {
        Relatory relatory = getRelatory(exchange);
        Integer custumersNumber = relatory.getCustomers().size();
        exchange.setProperty("custumersNumber", custumersNumber);
    }

    private void calculateSellersNumber(Exchange exchange) {
        Relatory relatory = getRelatory(exchange);
        Integer sellersNumber = relatory.getSalesmens().size();
        exchange.setProperty("sellersNumber", sellersNumber);
    }

    private void getMostExpensiveSale(Exchange exchange) {
        Relatory relatory = getRelatory(exchange);
        List<Sale> saleList = relatory.getSales();
        Double mostExpensiveSaleCost = saleList.stream()
                .map(this::getSaleCost)
                .max(Double::compare)
                .get();

        Sale mostExpensiveSale = getSaleByTotalCost(mostExpensiveSaleCost, saleList);
        exchange.setProperty("idMostExpensiveSale", mostExpensiveSale.getId());
    }

    private void getWorstSalesmanName(Exchange exchange) {
        Relatory relatory = getRelatory(exchange);
        List<Sale> saleList = relatory.getSales();
        Double cheapestSale = saleList.stream()
                .map(this::getSaleCost)
                .min(Double::compare)
                .get();

        Sale sale = getSaleByTotalCost(cheapestSale, saleList);
        exchange.setProperty("worstSalesmanName", sale.getSalesmanName());
    }

    private void buildResult(Exchange exchange) {
        Integer custumersNumber = exchange.getProperty("custumersNumber", Integer.class);
        Integer sellersNumber = exchange.getProperty("sellersNumber", Integer.class);
        Long idMostExpensiveSale = exchange.getProperty("idMostExpensiveSale", Long.class);
        String worstSalesmanName = exchange.getProperty("worstSalesmanName", String.class);

        Result result = new Result(custumersNumber, sellersNumber, idMostExpensiveSale, worstSalesmanName);
        exchange.getIn().setBody(result, Result.class);
    }

    private Sale getSaleByTotalCost(Double cost, List<Sale> sales) {
        return sales.stream()
                .filter(sale -> getSaleCost(sale).equals(cost))
                .findFirst()
                .get();
    }

    private Double getSaleCost(Sale sale) {
        return sale.getItems().stream()
                .map(item -> calculateCost(item.getQuantity(), item.getPrice()))
                .reduce((itemCost, total) -> total += itemCost)
                .get();
    }

    private Double calculateCost(Integer quantity, Double price) {
        return quantity * price;
    }

    private Relatory getRelatory(Exchange exchange) {
        return exchange.getProperty("relatory", Relatory.class);
    }
}
