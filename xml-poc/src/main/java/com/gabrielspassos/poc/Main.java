package com.gabrielspassos.poc;

import com.gabrielspassos.poc.service.CustomerService;

public class Main {

    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        customerService.convertCustomerToXml();
    }
}
