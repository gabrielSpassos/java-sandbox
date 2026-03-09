package com.gabrielspassos.patterns.behavioral.visitor;

import java.math.BigDecimal;

public class Fruit implements ItemElement {

    private String name;
    private BigDecimal priceByKg;
    private BigDecimal weight;

    public Fruit(String name, BigDecimal priceByKg, BigDecimal weight) {
        this.name = name;
        this.priceByKg = priceByKg;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPriceByKg() {
        return priceByKg;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    @Override
    public BigDecimal accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}
