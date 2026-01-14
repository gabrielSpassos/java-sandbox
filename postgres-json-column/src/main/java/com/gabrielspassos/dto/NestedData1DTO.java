package com.gabrielspassos.dto;

import java.util.Objects;

public class NestedData1DTO {

    private String id;
    private String value;

    public NestedData1DTO() {
    }

    public NestedData1DTO(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NestedData1DTO that = (NestedData1DTO) o;
        return Objects.equals(id, that.id) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }

    @Override
    public String toString() {
        return "NestedData1DTO{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
