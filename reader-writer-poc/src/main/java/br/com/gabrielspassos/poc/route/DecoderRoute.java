package br.com.gabrielspassos.poc.route;

import br.com.gabrielspassos.poc.model.Customer;
import br.com.gabrielspassos.poc.model.Relatory;
import br.com.gabrielspassos.poc.model.Sale;
import br.com.gabrielspassos.poc.model.Salesman;
import br.com.gabrielspassos.poc.processor.ProcessorFactory;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import java.util.List;


public class DecoderRoute extends RouteBuilder {

    @Override
    public void configure() {
        onException(Exception.class)
                .maximumRedeliveries(3)
                .handled(true)
                .log("Tem um erro aqui");

        from("direct:decoder")
                .routeId("decoder")
                .process(ProcessorFactory.createSalesmanProcessor())
                .process(ProcessorFactory.createCustomerProcessor())
                .process(ProcessorFactory.createSaleProcessor())
                .process(this::buildRelatory)
                .to("direct:analysesRelatory")
                .end();
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
