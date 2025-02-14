package com.gabrielspassos.innter;

import java.util.List;

public class ConnectCreditCardRequest {

    private String dosId;
    private String linkAttemptId;
    private AggregatorRequest aggregator;
    private List<UpdateCreditCardRequest> creditCards;

    public String getDosId() {
        return dosId;
    }

    public void setDosId(String dosId) {
        this.dosId = dosId;
    }

    public String getLinkAttemptId() {
        return linkAttemptId;
    }

    public void setLinkAttemptId(String linkAttemptId) {
        this.linkAttemptId = linkAttemptId;
    }

    public AggregatorRequest getAggregator() {
        return aggregator;
    }

    public void setAggregator(AggregatorRequest aggregator) {
        this.aggregator = aggregator;
    }

    public List<UpdateCreditCardRequest> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<UpdateCreditCardRequest> creditCards) {
        this.creditCards = creditCards;
    }
}
