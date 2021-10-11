package com.gabrielspassos.poc.factory;


import com.gabrielspassos.poc.domain.Account;
import com.gabrielspassos.poc.domain.Customer;
import domain.enumarator.PersonType;

import java.util.List;

public class CustomerFactory {

    public static Customer createNaturalCustomer() {
        List<Account> accounts = List.of(AccountFactory.createCorrenteAccount(),
                AccountFactory.createPoupancaAccount(), AccountFactory.createSalarioAccount());
        return createCustomer(1, "http://localhost:8080/customer/1", "Jose Silva Souza",
                "27670104015", PersonType.NATURAL, "jose.souza@gmail.com", Boolean.TRUE, accounts);
    }

    public static Customer createLegalCustomer() {
        List<Account> accounts = List.of(AccountFactory.createPagamentoAccount());
        return createCustomer(2, "http://localhost:8080/customer/2", "Empresa A Ltda",
                "24496061000150", PersonType.LEGAL, "empresa@gmail.com", Boolean.FALSE, accounts);
    }

    private static Customer createCustomer(Integer id, String url, String name, String document, PersonType type,
                                           String email, Boolean isActive, List<Account> accounts) {
        return Customer.Builder.builder()
                .id(id)
                .url(url)
                .name(name)
                .document(document)
                .type(type)
                .email(email)
                .isActive(isActive)
                .accounts(accounts)
                .build();
    }
}
