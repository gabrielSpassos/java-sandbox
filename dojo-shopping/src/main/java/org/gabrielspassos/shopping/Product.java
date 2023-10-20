package org.gabrielspassos.shopping;

import org.gabrielspassos.util.AmountUtil;

import java.math.BigDecimal;

public class Product {

    private String name;

    private BigDecimal price;

    private Integer quantity;

    public Product(String name, BigDecimal price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public BigDecimal getProductTotalPrice() {
        return AmountUtil.setScaleAndRound(price.multiply(BigDecimal.valueOf(quantity)));
    }
}
