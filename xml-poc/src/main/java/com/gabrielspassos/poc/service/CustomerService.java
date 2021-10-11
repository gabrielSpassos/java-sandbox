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

        File naturalCustomerFile = createXmlFile("naturalCustomer", naturalCustomer);
        File legalCustomerFile = createXmlFile("legalCustomer", legalCustomer);

        Customer newNaturalCustomer = readXmlFile(naturalCustomerFile);
        Customer newLegalCustomer = readXmlFile(legalCustomerFile);

        System.out.println("Read Natural customer: " + newNaturalCustomer);
        System.out.println("Read Legal customer: " + newLegalCustomer);
    }

    private String convertToXml(Object object) {
        try {
            return xmlMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private File createXmlFile(String filename, Object object) {
        try {
            String fileNameWithExtension = filename + ".xml";
            File file = new File(fileNameWithExtension);
            xmlMapper.writeValue(file, object);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    private Customer readXmlFile(File file) {
        try {
            return xmlMapper.readValue(file, Customer.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
}
