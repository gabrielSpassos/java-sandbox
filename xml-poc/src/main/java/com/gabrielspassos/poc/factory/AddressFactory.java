package com.gabrielspassos.poc.factory;

import com.gabrielspassos.poc.domain.Address;

public class AddressFactory {

    public static Address createAddress() {
        return createAddress(1, "Rua A, 101", "Porto Alegre", "RS", "Brazil");
    }

    private static Address createAddress(Integer id, String street, String city, String state, String country) {
        return Address.AddressBuilder.builder()
                .id(id)
                .street(street)
                .city(city)
                .state(state)
                .country(country)
                .build();
    }
}
