package br.com.gabrielspassos.poc.model;

import java.util.List;

public class Relatory {

    List<Salesman> salesmens;
    List<Sale> sales;
    List<Customer> customers;

    public List<Salesman> getSalesmens() {
        return salesmens;
    }

    public void setSalesmens(List<Salesman> salesmens) {
        this.salesmens = salesmens;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
