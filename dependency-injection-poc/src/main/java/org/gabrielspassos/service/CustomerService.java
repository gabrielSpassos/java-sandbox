package org.gabrielspassos.service;

import org.gabrielspassos.dto.CustomerDTO;
import org.gabrielspassos.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
    }

    public List<CustomerDTO> findActiveCustomers() {
        return customerRepository.findAll().stream()
                .filter(CustomerDTO::getIsActive)
                .collect(Collectors.toList());
    }
}