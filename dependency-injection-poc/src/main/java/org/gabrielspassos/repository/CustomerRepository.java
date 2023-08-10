package org.gabrielspassos.repository;

import org.gabrielspassos.dto.CustomerDTO;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CustomerRepository {

    public List<CustomerDTO> findAll() {
        CustomerDTO customerDTO = new CustomerDTO(UUID.randomUUID().toString(), "John Doe", true);
        return Collections.singletonList(customerDTO);
    }
}
