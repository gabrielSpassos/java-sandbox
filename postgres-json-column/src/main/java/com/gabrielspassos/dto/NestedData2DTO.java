package com.gabrielspassos.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class NestedData2DTO {

    private String key;
    private BigDecimal amount;

    public NestedData2DTO() {
    }

    public NestedData2DTO(String key, BigDecimal amount) {
        this.key = key;
        this.amount = amount;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NestedData2DTO that = (NestedData2DTO) o;
        return Objects.equals(key, that.key) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, amount);
    }

    @Override
    public String toString() {
        return "NestedData2DTO{" +
                "key='" + key + '\'' +
                ", amount=" + amount +
                '}';
    }
}
