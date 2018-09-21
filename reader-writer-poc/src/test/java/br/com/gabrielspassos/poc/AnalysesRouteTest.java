package br.com.gabrielspassos.poc;

import br.com.gabrielspassos.poc.model.*;
import br.com.gabrielspassos.poc.route.AnalyzesRoute;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class AnalysesRouteTest extends CamelTestSupport {

    @Override
    public RouteBuilder createRouteBuilder() {
        return new AnalyzesRoute();
    }

    @Test
    public void shouldCreateOutputFile() throws InterruptedException {
        template.sendBodyAndProperty(
                "direct:analysesRelatory",
                null,
                "relatory",
                buildRelatory()
        );

        Exchange exchange = consumer.receive("file://data/out/?fileName=result.${date:now:yyyy-MM-dd}.done.dat&charset=utf-8");
        String expectedFileOutputFileName = String.format("result.%s.done.dat", getCurrentDate());
        GenericFile file = (GenericFile) exchange.getIn().getBody();

        assertEquals(expectedFileOutputFileName, file.getFileName());
        assertEquals("data/out", file.getEndpointPath());
    }

    private String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
