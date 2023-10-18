package org.gabrielspassos.shopping;

import java.math.BigDecimal;

public class Product {

    private String name;

    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    //todo: remove this getter
    public BigDecimal getPrice() {
        return price;
    }
}
