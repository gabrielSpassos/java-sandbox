package com.gabrielspassos.request;

import java.math.BigDecimal;
import java.util.Objects;

public class WebhookRequest {

    private String userId;
    private String accountId;
    private BigDecimal balance;

    public WebhookRequest() {
    }

    public WebhookRequest(String userId, String accountId, BigDecimal balance) {
        this.userId = userId;
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WebhookRequest that = (WebhookRequest) o;
        return Objects.equals(userId, that.userId)
                && Objects.equals(accountId, that.accountId)
                && Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, accountId, balance);
    }

    @Override
    public String toString() {
        return "WebhookRequest{" +
                "userId='" + userId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", balance=" + balance +
                '}';
    }
}
