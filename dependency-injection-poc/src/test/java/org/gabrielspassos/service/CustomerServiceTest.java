package org.gabrielspassos.service;

import org.gabrielspassos.dto.CustomerDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerServiceTest {

    @Test
    void shouldReturnActiveCustomer() {
        // No easy way to mock/fake the customer repository
        CustomerService customerService = new CustomerService();

        List<CustomerDTO> activeCustomers = customerService.findActiveCustomers();

        assertNotNull(activeCustomers);
        assertFalse(activeCustomers.isEmpty());
    }

}