package com.gabrielspassos.poc.dto;

import java.math.BigDecimal;

public class TransactionDTO {

    private String id;

    private String type;

    private String account;

    private BigDecimal amount;

    public TransactionDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", account='" + account + '\'' +
                ", amount=" + amount +
                '}';
    }
}
