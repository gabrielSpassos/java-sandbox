package com.gabrielspassos.patterns.behavioral.strategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Item> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        this.items.add(item);
        return true;
    }

    public boolean removeItem(Item item) {
        this.items.remove(item);
        return true;
    }

    public BigDecimal pay(PaymentStrategy paymentMethod){
        BigDecimal amount = calculateTotal();
        return paymentMethod.pay(amount);
    }

    private BigDecimal calculateTotal() {
        return this.items.stream()
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
