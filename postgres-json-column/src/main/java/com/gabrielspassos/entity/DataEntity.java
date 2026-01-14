package com.gabrielspassos.entity;

import com.gabrielspassos.dto.DataDTO;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Table(name = "data")
public class DataEntity {

    @Column(value = "id")
    private UUID id;

    @Column(value = "data")
    private DataDTO data;

    @Column(value = "data_b")
    private DataDTO binaryData;

    @Column(value = "created_at")
    private OffsetDateTime createdAt;

    public DataEntity() {
    }

    public DataEntity(UUID id, DataDTO data, DataDTO binaryData, OffsetDateTime createdAt) {
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

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public DataDTO getBinaryData() {
        return binaryData;
    }

    public void setBinaryData(DataDTO binaryData) {
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
        DataEntity that = (DataEntity) o;
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
