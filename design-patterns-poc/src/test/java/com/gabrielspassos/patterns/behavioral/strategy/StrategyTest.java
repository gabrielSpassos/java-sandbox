package com.gabrielspassos.patterns.behavioral.strategy;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StrategyTest {

    @Test
    void shouldPayWithCreditCard() {
        ShoppingCart cart = new ShoppingCart();
        Item item1 = new Item("1234", new BigDecimal(10));
        Item item2 = new Item("5678", new BigDecimal(40));
        Item item3 = new Item("9076", new BigDecimal(50));

        cart.addItem(item1);
        cart.addItem(item2);
        cart.addItem(item3);

        CreditCardStrategy strategy
                = new CreditCardStrategy("John Doe", "1234567890123456", "123", LocalDate.now());

        var result1 = cart.pay(strategy);

        assertEquals(new BigDecimal(100), result1);

        cart.removeItem(item2);

        var result2 = cart.pay(strategy);

        assertEquals(new BigDecimal(60), result2);
    }

    @Test
    void shouldPayWithPaypal() {
        ShoppingCart cart = new ShoppingCart();
        Item item1 = new Item("1234", new BigDecimal(10));
        Item item2 = new Item("5678", new BigDecimal(40));
        Item item3 = new Item("9076", new BigDecimal(50));

        cart.addItem(item1);
        cart.addItem(item2);
        cart.addItem(item3);

        PaypalStrategy strategy
                = new PaypalStrategy("john.doe@email.com", "password");

        var result1 = cart.pay(strategy);

        assertEquals(new BigDecimal(100), result1);

        cart.removeItem(item2);

        var result2 = cart.pay(strategy);

        assertEquals(new BigDecimal(60), result2);
    }

}