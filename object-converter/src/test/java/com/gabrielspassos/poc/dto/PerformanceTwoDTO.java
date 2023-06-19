package com.gabrielspassos.poc.dto;

public class PerformanceTwoDTO {

    private String id;

    private String name;

    private String value;

    private String otherValue;

    public PerformanceTwoDTO() {
    }

    public PerformanceTwoDTO(String id, String name, String value, String otherValue) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.otherValue = otherValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOtherValue() {
        return otherValue;
    }

    public void setOtherValue(String otherValue) {
        this.otherValue = otherValue;
    }
}
