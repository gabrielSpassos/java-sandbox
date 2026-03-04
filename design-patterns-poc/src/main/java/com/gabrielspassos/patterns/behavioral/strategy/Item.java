package com.gabrielspassos.patterns.behavioral.strategy;

import java.math.BigDecimal;

public class Item {

    private String code;
    private BigDecimal price;

    public Item(String code, BigDecimal price) {
        this.code = code;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
