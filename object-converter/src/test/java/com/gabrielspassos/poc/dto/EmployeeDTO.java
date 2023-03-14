package com.gabrielspassos.poc.dto;

public class EmployeeDTO extends PersonDTO {

    private Long contractNumber;

    private Boolean isContractActive;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, Integer age, Long contractNumber, Boolean isContractActive) {
        super(name, age);
        this.contractNumber = contractNumber;
        this.isContractActive = isContractActive;
    }

    public Long getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(Long contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Boolean getContractActive() {
        return isContractActive;
    }

    public void setContractActive(Boolean contractActive) {
        isContractActive = contractActive;
    }
}
