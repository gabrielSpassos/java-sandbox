package com.gabrielspassos.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("CAR")
public class CarEntity implements Persistable<String> {

    @Id
    @Column("ID")
    private String id;

    @Column("WAREHOUSE_ID")
    private String warehouseId;

    @Column("BRAND")
    private String brand;

    @Column("NAME")
    private String name;

    @Transient
    private boolean isNewEntry = false;

    public CarEntity() {
    }

    public CarEntity(String id, String warehouseId, String brand, String name, boolean isNewEntry) {
        this.id = id;
        this.warehouseId = warehouseId;
        this.brand = brand;
        this.name = name;
        this.isNewEntry = isNewEntry;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNewEntry;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarEntity carEntity = (CarEntity) o;
        return Objects.equals(id, carEntity.id) && Objects.equals(warehouseId, carEntity.warehouseId) && Objects.equals(brand, carEntity.brand) && Objects.equals(name, carEntity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, warehouseId, brand, name);
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "id='" + id + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
