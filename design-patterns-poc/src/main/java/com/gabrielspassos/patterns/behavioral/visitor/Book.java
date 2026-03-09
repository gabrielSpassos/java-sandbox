package com.gabrielspassos.patterns.behavioral.visitor;

import java.math.BigDecimal;

public class Book implements ItemElement {

    private String isbnNumber;
    private BigDecimal price;

    public Book(String isbnNumber, BigDecimal price) {
        this.isbnNumber = isbnNumber;
        this.price = price;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public BigDecimal accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }

}
