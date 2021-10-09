package com.gabrielspassos.poc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.gabrielspassos.poc.domain.Customer;
import com.gabrielspassos.poc.factory.CustomerFactory;

import java.io.File;
import java.io.IOException;

public class CustomerService {

    private static XmlMapper xmlMapper;

    public CustomerService() {
        xmlMapper = new XmlMapper();
    }

    public void convertCustomerToXml() {
        Customer naturalCustomer = CustomerFactory.createNaturalCustomer();
        Customer legalCustomer = CustomerFactory.createLegalCustomer();

        System.out.println("Natural customer: " + naturalCustomer);
        System.out.println("Legal customer: " + legalCustomer);

        String naturalCustomerAsXml = convertToXml(naturalCustomer);
        String legalCustomerAsXml = convertToXml(legalCustomer);

        System.out.println("Natural customer xml: " + naturalCustomerAsXml);
        System.out.println("Legal customer xml: " + legalCustomerAsXml);

        createXmlFile("naturalCustomer", naturalCustomer);
        createXmlFile("legalCustomer", legalCustomer);
    }

    private String convertToXml(Object object) {
        try {
            return xmlMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void createXmlFile(String filename, Object object) {
        try {
            String fileNameWithExtension = filename + ".xml";
            File file = new File(fileNameWithExtension);
            xmlMapper.writeValue(file, object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
