package com.gabrielspassos.patterns.behavioral.strategy;

import java.math.BigDecimal;

public class PaypalStrategy implements PaymentStrategy {

    private String emailId;
    private String password;

    public PaypalStrategy(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }

    @Override
    public BigDecimal pay(BigDecimal amount) {
        IO.println("Payed with paypal, amount: " + amount);
        return amount;
    }

}
