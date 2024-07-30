package com.gabrielspassos.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.Objects;

@Table("pet")
public class PetEntity implements Persistable<String> {

    @Id
    @Column("id")
    private String id;

    @Column("name")
    private String name;

    @Column("created_at")
    private Timestamp createdAt;

    @Transient
    private boolean isNew;

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

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetEntity petEntity = (PetEntity) o;
        return isNew == petEntity.isNew
                && Objects.equals(id, petEntity.id)
                && Objects.equals(name, petEntity.name)
                && Objects.equals(createdAt, petEntity.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt, isNew);
    }

    @Override
    public String toString() {
        return "PetEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", isNew=" + isNew +
                '}';
    }
}
