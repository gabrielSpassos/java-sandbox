package com.gabrielspassos;

import java.util.List;

public class ListCreditCardResponse {

    private Integer totalBalance;
    private Integer utilization;
    private List<CreditCardDetailResponse> creditCards;

    public Integer getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Integer totalBalance) {
        this.totalBalance = totalBalance;
    }

    public Integer getUtilization() {
        return utilization;
    }

    public void setUtilization(Integer utilization) {
        this.utilization = utilization;
    }

    public List<CreditCardDetailResponse> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCardDetailResponse> creditCards) {
        this.creditCards = creditCards;
    }
}
