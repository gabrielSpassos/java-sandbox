package com.gabrielspassos.poc.services.dto;

public class BankingEmployeeDTO extends EmployeeDTO {

    private Double bonusMultiplier;

    public BankingEmployeeDTO() {
    }

    public BankingEmployeeDTO(String name, Integer age, Long contractNumber,
                              Boolean isContractActive, Double bonusMultiplier) {
        super(name, age, contractNumber, isContractActive);
        this.bonusMultiplier = bonusMultiplier;
    }

    public Double getBonusMultiplier() {
        return bonusMultiplier;
    }

    public void setBonusMultiplier(Double bonusMultiplier) {
        this.bonusMultiplier = bonusMultiplier;
    }
}
