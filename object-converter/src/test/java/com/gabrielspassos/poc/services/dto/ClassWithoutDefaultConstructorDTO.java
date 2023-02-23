package com.gabrielspassos.poc.services.dto;

public class ClassWithoutDefaultConstructorDTO {

    private String agency;

    private String number;

    private Long digit;

    public ClassWithoutDefaultConstructorDTO(String agency, String number, Long digit) {
        this.agency = agency;
        this.number = number;
        this.digit = digit;
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

    @Override
    public String toString() {
        return "ClassWithoutDefaultConstructorDTO{" +
                "agency='" + agency + '\'' +
                ", number='" + number + '\'' +
                ", digit=" + digit +
                '}';
    }
}
