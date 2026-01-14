package com.gabrielspassos.entity;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Table(name = "data")
public class DataAsStringEntity {

    @Column(value = "id")
    private UUID id;

    @Column(value = "data")
    private String data;

    @Column(value = "data_b")
    private String binaryData;

    @Column(value = "created_at")
    private OffsetDateTime createdAt;

    public DataAsStringEntity() {
    }

    public DataAsStringEntity(UUID id, String data, String binaryData, OffsetDateTime createdAt) {
        this.id = id;
        this.data = data;
        this.binaryData = binaryData;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getBinaryData() {
        return binaryData;
    }

    public void setBinaryData(String binaryData) {
        this.binaryData = binaryData;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DataAsStringEntity that = (DataAsStringEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(data, that.data) && Objects.equals(binaryData, that.binaryData) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, binaryData, createdAt);
    }

    @Override
    public String toString() {
        return "DataAsStringEntity{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", binaryData='" + binaryData + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
