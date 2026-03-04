package com.gabrielspassos.patterns.behavioral.strategy;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditCardStrategy implements PaymentStrategy {

    private String name;
    private String number;
    private String cvv;
    private LocalDate expirationDate;

    public CreditCardStrategy(String name, String number, String cvv, LocalDate expirationDate) {
        this.name = name;
        this.number = number;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }

    @Override
    public BigDecimal pay(BigDecimal amount) {
        IO.println("Payed with credit card, amount: " + amount);
        return amount;
    }

}
