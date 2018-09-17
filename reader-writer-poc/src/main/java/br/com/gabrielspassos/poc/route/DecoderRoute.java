package br.com.gabrielspassos.poc.route;

import br.com.gabrielspassos.poc.model.*;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

public class DecoderRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file://data/in/?fileName=relatory.dat&charset=utf-8")
                .routeId("decoder")
                .convertBodyTo(String.class)
                .process(this::extractMessageByInitialId)
                .process(this::createSalesmanList)
                .process(this::createCustomerList)
                .process(this::createSaleList)
                .process(this::decodeMessage)
                .process(this::buildRelatory)
                .to("direct:analisisRelatory")
                .end();
    }

    private void extractMessageByInitialId(Exchange exchange) {
        String message = exchange.getIn().getBody(String.class);
        String[] messagesArray = message.split("001|002|003");
        exchange.setProperty("messagesArray", messagesArray);
    }

    private void createSalesmanList(Exchange exchange) {
        List<Salesman> salesmenList = new ArrayList<>();
        exchange.setProperty("salesmanList", salesmenList);
    }

    private void createCustomerList(Exchange exchange) {
        List<Customer> customerList = new ArrayList<>();
        exchange.setProperty("customerList", customerList);
    }

    private void createSaleList(Exchange exchange) {
        List<Sale> saleList = new ArrayList<>();
        exchange.setProperty("saleList", saleList);
    }

    private void decodeMessage(Exchange exchange) {
        String[] messagesArrays = (String[]) exchange.getProperty("messagesArray");
        for (String messagesArray : messagesArrays) {
            String[] infos = messagesArray.split("ç");
            factory(infos, exchange);
        }
    }

    private void factory(String[] infos, Exchange exchange) {
        if(isSalesman(infos)) {
            Salesman salesman = buildSalesman(infos);
            List<Salesman> salesmanList = exchange.getProperty("salesmanList", List.class);
            salesmanList.add(salesman);
            exchange.setProperty("salesmanList", salesmanList);
        } else if (isSale(infos)) {
            Sale sale = buildSale(infos);
            List<Sale> saleList = exchange.getProperty("saleList", List.class);
            saleList.add(sale);
            exchange.setProperty("saleList", saleList);
        } else if (isCustomer(infos)) {
            Customer customer = buildCustomer(infos);
            List<Customer> customerList = exchange.getProperty("customerList", List.class);
            customerList.add(customer);
            exchange.setProperty("customerList", customerList);
        }
    }

    private Boolean isSalesman(String[] infos) {
        return infos.length == 4 && isCreatable(StringUtils.trim(infos[infos.length-1]));
    }

    private Salesman buildSalesman(String[] infos) {
        Salesman salesman = new Salesman();
        salesman.setCpf(infos[1]);
        salesman.setName(infos[2]);
        salesman.setSalary(new BigDecimal(infos[3].trim()));
        return salesman;
    }

    private Boolean isSale(String[] infos) {
        return infos.length == 4 && infos[2].contains("[");
    }

    private Sale buildSale(String[] infos) {
        Sale sale = new Sale();
        sale.setId(Long.valueOf(infos[1]));
        sale.setItems(buildItensList(infos[2]));
        sale.setSalesmanName(infos[3]);
        return sale;
    }

    private List<Item> buildItensList(String info) {
        List<Item> itemList =  new ArrayList<>();
        String[] result = info.split("\\[|,|]");
        for (String res : result) {
            String[] itemInfos = res.split("-");
            if(isItem(itemInfos)){
                Item item = buildItem(itemInfos);
                itemList.add(item);
            }
        }
        return itemList;
    }

    private Boolean isItem(String[] itemInfos) {
        return itemInfos.length == 3
                && isCreatable(itemInfos[0])
                && isCreatable(itemInfos[1])
                && isCreatable(itemInfos[2]);
    }

    private Item buildItem(String[] itemInfos) {
        Item item = new Item();
        item.setId(Long.valueOf(itemInfos[0]));
        item.setQuantity(Integer.valueOf(itemInfos[1]));
        item.setPrice(Double.valueOf(itemInfos[2]));
        return item;
    }

    private Boolean isCustomer(String[] infos) {
        return infos.length == 4;
    }

    private Customer buildCustomer(String[] infos) {
        Customer customer = new Customer();
        customer.setCnpj(infos[1]);
        customer.setName(infos[2]);
        customer.setBusinessArea(infos[3]);
        return customer;
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
