package com.gabrielspassos.controller.response;

import java.util.Objects;

public class CalculateDungeonHealthResponse {

    private String id;
    private Integer minimalHealth;

    public CalculateDungeonHealthResponse() {
    }

    public CalculateDungeonHealthResponse(String id, Integer minimalHealth) {
        this.id = id;
        this.minimalHealth = minimalHealth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMinimalHealth() {
        return minimalHealth;
    }

    public void setMinimalHealth(Integer minimalHealth) {
        this.minimalHealth = minimalHealth;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CalculateDungeonHealthResponse that = (CalculateDungeonHealthResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(minimalHealth, that.minimalHealth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, minimalHealth);
    }

    @Override
    public String toString() {
        return "CalculateDungeonHealthResponse{" +
                "id='" + id + '\'' +
                ", minimalHealth=" + minimalHealth +
                '}';
    }
}
