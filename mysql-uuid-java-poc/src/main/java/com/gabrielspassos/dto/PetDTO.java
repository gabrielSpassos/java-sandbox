package com.gabrielspassos.dto;

import com.gabrielspassos.domain.PetEntity;

import java.sql.Timestamp;
import java.util.Objects;

public class PetDTO {

    private String id;
    private String name;
    private Timestamp createdAt;

    public PetDTO() {
    }

    public PetDTO(PetEntity petEntity) {
        this.id = petEntity.getId();
        this.name = petEntity.getName();
        this.createdAt = petEntity.getCreatedAt();
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetDTO petDTO = (PetDTO) o;
        return Objects.equals(id, petDTO.id)
                && Objects.equals(name, petDTO.name)
                && Objects.equals(createdAt, petDTO.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt);
    }

    @Override
    public String toString() {
        return "PetDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
