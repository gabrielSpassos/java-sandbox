package com.gabrielspassos.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Table(name = "users")
public class ListEntity {

    @Id
    @Column(value = "id")
    private UUID id;

    @Column(value = "user_id")
    private UUID userId;

    @Column(value = "name")
    private String name;

    @Column(value = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ListEntity that = (ListEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(name, that.name) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, createdAt);
    }

    @Override
    public String toString() {
        return "ListEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
