package com.gabrielspassos.dto;

import com.gabrielspassos.domain.CarEntity;

import java.util.Objects;

public class CarDTO {

    private String id;
    private String warehouseId;
    private String brand;
    private String name;

    public CarDTO(CarEntity carEntity) {
        this.id = carEntity.getId();
        this.warehouseId = carEntity.getWarehouseId();
        this.brand = carEntity.getBrand();
        this.name = carEntity.getName();
    }

    public String getId() {
        return id;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO carDTO = (CarDTO) o;
        return Objects.equals(id, carDTO.id) && Objects.equals(warehouseId, carDTO.warehouseId) && Objects.equals(brand, carDTO.brand) && Objects.equals(name, carDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, warehouseId, brand, name);
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "id='" + id + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
