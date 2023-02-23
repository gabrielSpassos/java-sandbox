package com.gabrielspassos.poc.services.dto;

import java.math.BigDecimal;

public class SavingAccountDTO {

    private String agency;

    private String number;

    private Long digit;

    private BigDecimal amount;

    public SavingAccountDTO() {
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getDigit() {
        return digit;
    }

    public void setDigit(Long digit) {
        this.digit = digit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "SavingAccountDTO{" +
                "agency='" + agency + '\'' +
                ", number='" + number + '\'' +
                ", digit=" + digit +
                ", amount=" + amount +
                '}';
    }
}
