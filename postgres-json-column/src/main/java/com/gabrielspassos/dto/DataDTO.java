package com.gabrielspassos.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class DataDTO {

    private String userId;
    private BigDecimal amount;
    private Boolean isActive;
    private List<String> accountIds;
    private NestedData1DTO nestedData1;
    private List<NestedData2DTO> nestedData2List;

    public DataDTO() {
    }

    public DataDTO(String userId, BigDecimal amount, Boolean isActive, List<String> accountIds, NestedData1DTO nestedData1, List<NestedData2DTO> nestedData2List) {
        this.userId = userId;
        this.amount = amount;
        this.isActive = isActive;
        this.accountIds = accountIds;
        this.nestedData1 = nestedData1;
        this.nestedData2List = nestedData2List;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<String> getAccountIds() {
        return accountIds;
    }

    public void setAccountIds(List<String> accountIds) {
        this.accountIds = accountIds;
    }

    public NestedData1DTO getNestedData1() {
        return nestedData1;
    }

    public void setNestedData1(NestedData1DTO nestedData1) {
        this.nestedData1 = nestedData1;
    }

    public List<NestedData2DTO> getNestedData2List() {
        return nestedData2List;
    }

    public void setNestedData2List(List<NestedData2DTO> nestedData2List) {
        this.nestedData2List = nestedData2List;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DataDTO dataDTO = (DataDTO) o;
        return Objects.equals(userId, dataDTO.userId) && Objects.equals(amount, dataDTO.amount) && Objects.equals(isActive, dataDTO.isActive) && Objects.equals(accountIds, dataDTO.accountIds) && Objects.equals(nestedData1, dataDTO.nestedData1) && Objects.equals(nestedData2List, dataDTO.nestedData2List);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, amount, isActive, accountIds, nestedData1, nestedData2List);
    }

    @Override
    public String toString() {
        return "DataDTO{" +
                "userId='" + userId + '\'' +
                ", amount=" + amount +
                ", isActive=" + isActive +
                ", accountIds=" + accountIds +
                ", nestedData1=" + nestedData1 +
                ", nestedData2List=" + nestedData2List +
                '}';
    }
}
