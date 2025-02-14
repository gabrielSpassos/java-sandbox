package com.gabrielspassos;

import java.util.List;

public class CreditCardDetailResponse {

    private String ccId;
    private String ccMaskedNumber;
    private Integer ccBalance;
    private Integer ccUtilization;
    private String ccState;
    private Integer ccApr;
    private String ccName;
    private Integer ccLimit;
    private Integer ccStatementMinimumDue;
    private Integer ccStatementBalance;
    private String ccStatementDueDate;
    private String ccStatementDate;
    private String ccPaymentPreference;
    private String ccLastRefreshedOn;
    private Integer ccPromoRate;
    private String ccPromoRateExpirationDate;
    private Boolean ccIsLinkable;
    private Boolean ccIsArchived;
    private Boolean ccIsPayable;
    private Boolean ccIsInterestReducedByPromoApr;
    private String ccSource;
    private String institutionName;
    private String institutionId;
    private Boolean ccIsAccruingInterest;
    private Boolean ccIsLateFeeProtected;
    private String linkAttemptId;
    private List<String> editable;

    public String getCcId() {
        return ccId;
    }

    public void setCcId(String ccId) {
        this.ccId = ccId;
    }

    public String getCcMaskedNumber() {
        return ccMaskedNumber;
    }

    public void setCcMaskedNumber(String ccMaskedNumber) {
        this.ccMaskedNumber = ccMaskedNumber;
    }

    public Integer getCcBalance() {
        return ccBalance;
    }

    public void setCcBalance(Integer ccBalance) {
        this.ccBalance = ccBalance;
    }

    public Integer getCcUtilization() {
        return ccUtilization;
    }

    public void setCcUtilization(Integer ccUtilization) {
        this.ccUtilization = ccUtilization;
    }

    public String getCcState() {
        return ccState;
    }

    public void setCcState(String ccState) {
        this.ccState = ccState;
    }

    public Integer getCcApr() {
        return ccApr;
    }

    public void setCcApr(Integer ccApr) {
        this.ccApr = ccApr;
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public Integer getCcLimit() {
        return ccLimit;
    }

    public void setCcLimit(Integer ccLimit) {
        this.ccLimit = ccLimit;
    }

    public Integer getCcStatementMinimumDue() {
        return ccStatementMinimumDue;
    }

    public void setCcStatementMinimumDue(Integer ccStatementMinimumDue) {
        this.ccStatementMinimumDue = ccStatementMinimumDue;
    }

    public Integer getCcStatementBalance() {
        return ccStatementBalance;
    }

    public void setCcStatementBalance(Integer ccStatementBalance) {
        this.ccStatementBalance = ccStatementBalance;
    }

    public String getCcStatementDueDate() {
        return ccStatementDueDate;
    }

    public void setCcStatementDueDate(String ccStatementDueDate) {
        this.ccStatementDueDate = ccStatementDueDate;
    }

    public String getCcStatementDate() {
        return ccStatementDate;
    }

    public void setCcStatementDate(String ccStatementDate) {
        this.ccStatementDate = ccStatementDate;
    }

    public String getCcPaymentPreference() {
        return ccPaymentPreference;
    }

    public void setCcPaymentPreference(String ccPaymentPreference) {
        this.ccPaymentPreference = ccPaymentPreference;
    }

    public String getCcLastRefreshedOn() {
        return ccLastRefreshedOn;
    }

    public void setCcLastRefreshedOn(String ccLastRefreshedOn) {
        this.ccLastRefreshedOn = ccLastRefreshedOn;
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

    public Boolean getCcIsLinkable() {
        return ccIsLinkable;
    }

    public void setCcIsLinkable(Boolean ccIsLinkable) {
        this.ccIsLinkable = ccIsLinkable;
    }

    public Boolean getCcIsArchived() {
        return ccIsArchived;
    }

    public void setCcIsArchived(Boolean ccIsArchived) {
        this.ccIsArchived = ccIsArchived;
    }

    public Boolean getCcIsPayable() {
        return ccIsPayable;
    }

    public void setCcIsPayable(Boolean ccIsPayable) {
        this.ccIsPayable = ccIsPayable;
    }

    public Boolean getCcIsInterestReducedByPromoApr() {
        return ccIsInterestReducedByPromoApr;
    }

    public void setCcIsInterestReducedByPromoApr(Boolean ccIsInterestReducedByPromoApr) {
        this.ccIsInterestReducedByPromoApr = ccIsInterestReducedByPromoApr;
    }

    public String getCcSource() {
        return ccSource;
    }

    public void setCcSource(String ccSource) {
        this.ccSource = ccSource;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public Boolean getCcIsAccruingInterest() {
        return ccIsAccruingInterest;
    }

    public void setCcIsAccruingInterest(Boolean ccIsAccruingInterest) {
        this.ccIsAccruingInterest = ccIsAccruingInterest;
    }

    public Boolean getCcIsLateFeeProtected() {
        return ccIsLateFeeProtected;
    }

    public void setCcIsLateFeeProtected(Boolean ccIsLateFeeProtected) {
        this.ccIsLateFeeProtected = ccIsLateFeeProtected;
    }

    public String getLinkAttemptId() {
        return linkAttemptId;
    }

    public void setLinkAttemptId(String linkAttemptId) {
        this.linkAttemptId = linkAttemptId;
    }

    public List<String> getEditable() {
        return editable;
    }

    public void setEditable(List<String> editable) {
        this.editable = editable;
    }
}
