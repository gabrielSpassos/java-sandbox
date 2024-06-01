package com.gabrielspassos.dto;

import com.gabrielspassos.domain.WarehouseEntity;

import java.util.Objects;

public class WarehouseDTO {

    private String id;
    private String name;

    public WarehouseDTO(WarehouseEntity warehouseEntity) {
        this.id = warehouseEntity.getId();
        this.name = warehouseEntity.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseDTO that = (WarehouseDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "WarehouseDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
