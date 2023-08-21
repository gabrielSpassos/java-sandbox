package com.gabrielspassos.poc.dto;

import java.math.BigDecimal;

public class TransactionMetricDTO {

    private String id;

    private String type;

    private BigDecimal amount;

    private Boolean isSuccess;

    public TransactionMetricDTO(TransactionDTO transactionDTO) {
        this.id = transactionDTO.getId();
        this.type = transactionDTO.getType();
        this.amount = transactionDTO.getAmount();
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    @Override
    public String toString() {
        return "TransactionMetricDTO{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", isSuccess=" + isSuccess +
                '}';
    }
}
