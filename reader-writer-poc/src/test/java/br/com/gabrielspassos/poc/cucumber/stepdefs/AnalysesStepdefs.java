package br.com.gabrielspassos.poc.cucumber.stepdefs;

import br.com.gabrielspassos.poc.model.*;
import br.com.gabrielspassos.poc.route.AnalyzesRoute;
import cucumber.api.java8.En;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class AnalysesStepdefs extends CamelTestSupport implements En {

    @Override
    public RouteBuilder createRouteBuilder() {
        return new AnalyzesRoute();
    }

    public AnalysesStepdefs() {

        Before(new String[]{"@Analyses"}, this::createRouteBuilder);

        Given("^a relatory$", () -> {
            producerTemplate.sendBodyAndProperty(
                    "direct:analysesRelatory",
                    null,
                    "relatory",
                    buildRelatory()
            );
        });

        When("^the analyses are made$", () -> {
            createRouteBuilder()
                    .onCompletion()
                    .end();
        });

        Then("^should create a correct result$", () -> {
            Result result = consumer.receiveBody("direct:analysesRelatory", Result.class);
            System.out.println(result);
        });
    }

    private Relatory buildRelatory() {
        Relatory relatory = new Relatory();
        relatory.setSalesmens(Arrays.asList(
                buildSalesman("49616172379", "Leonel Messi", "3250.00"),
                buildSalesman("92160993727", "Cristiano Ronaldo", "4320.50")
        ));
        relatory.setCustomers(Arrays.asList(
                buildCustomer("60416625000160", "Liga Espanhola", "Futebol")
        ));
        relatory.setSales(Arrays.asList(
                buildSale(1L,
                        "Leonel Messi",
                        Arrays.asList(
                                buildItem(1L, 1, 3.00),
                                buildItem(2L, 2, 1.00))),
                buildSale(5L,
                        "Cristiano Ronaldo",
                        Arrays.asList(
                                buildItem(5L, 4, 100.00)))
        ));
        return relatory;
    }

    private Salesman buildSalesman(String cpf, String name, String salary) {
        Salesman salesman = new Salesman();
        salesman.setCpf(cpf);
        salesman.setName(name);
        salesman.setSalary(new BigDecimal(salary));
        return salesman;
    }

    private Customer buildCustomer(String cnpj, String name, String area) {
        Customer customer = new Customer();
        customer.setCnpj(cnpj);
        customer.setName(name);
        customer.setBusinessArea(area);
        return customer;
    }

    private Sale buildSale(Long id, String name, List<Item> items) {
        Sale sale = new Sale();
        sale.setId(id);
        sale.setSalesmanName(name);
        sale.setItems(items);
        return sale;
    }

    private Item buildItem(Long id, Integer quantity, Double price) {
        Item item = new Item();
        item.setId(id);
        item.setPrice(price);
        item.setQuantity(quantity);
        return item;
    }
}
