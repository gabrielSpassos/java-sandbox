package com.gabrielspassos.poc.dto;

public class EmployeeDTO extends PersonDTO {

    private String firstName;

    private Long contractNumber;

    private Boolean isContractActive;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, Integer age, Long contractNumber, Boolean isContractActive) {
        super(name, age);
        this.contractNumber = contractNumber;
        this.isContractActive = isContractActive;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
