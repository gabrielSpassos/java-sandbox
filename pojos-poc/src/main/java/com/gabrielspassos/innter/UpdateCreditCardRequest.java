package com.gabrielspassos.innter;

public class UpdateCreditCardRequest {

    private String ccId;
    private Integer ccPromoRate;
    private String ccPromoRateExpirationDate;
    private Integer ccBalance;
    private Integer ccStatementMinimumDue;
    private String ccStatementDueDate;
    private Integer ccCreditLimit;
    private Boolean ccIsLateFeeProtected;

    public String getCcId() {
        return ccId;
    }

    public void setCcId(String ccId) {
        this.ccId = ccId;
    }

    public Integer getCcPromoRate() {
        return ccPromoRate;
    }

    public void setCcPromoRate(Integer ccPromoRate) {
        this.ccPromoRate = ccPromoRate;
    }

    public String getCcPromoRateExpirationDate() {
        return ccPromoRateExpirationDate;
    }

    public void setCcPromoRateExpirationDate(String ccPromoRateExpirationDate) {
        this.ccPromoRateExpirationDate = ccPromoRateExpirationDate;
    }

    public Integer getCcBalance() {
        return ccBalance;
    }

    public void setCcBalance(Integer ccBalance) {
        this.ccBalance = ccBalance;
    }

    public Integer getCcStatementMinimumDue() {
        return ccStatementMinimumDue;
    }

    public void setCcStatementMinimumDue(Integer ccStatementMinimumDue) {
        this.ccStatementMinimumDue = ccStatementMinimumDue;
    }

    public String getCcStatementDueDate() {
        return ccStatementDueDate;
    }

    public void setCcStatementDueDate(String ccStatementDueDate) {
        this.ccStatementDueDate = ccStatementDueDate;
    }

    public Integer getCcCreditLimit() {
        return ccCreditLimit;
    }

    public void setCcCreditLimit(Integer ccCreditLimit) {
        this.ccCreditLimit = ccCreditLimit;
    }

    public Boolean getCcIsLateFeeProtected() {
        return ccIsLateFeeProtected;
    }

    public void setCcIsLateFeeProtected(Boolean ccIsLateFeeProtected) {
        this.ccIsLateFeeProtected = ccIsLateFeeProtected;
    }
}
