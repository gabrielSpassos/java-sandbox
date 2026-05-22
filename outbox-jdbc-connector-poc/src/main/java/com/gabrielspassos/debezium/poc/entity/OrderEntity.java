package com.gabrielspassos.debezium.poc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Table(name = "orders")
public class OrderEntity {

    @Id
    private UUID id;

    @Column(value = "product_name")
    private String productName;

    private BigDecimal amount;

    @Column(value = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public OrderEntity() {
    }

    public OrderEntity(UUID id, String productName, BigDecimal amount, LocalDateTime createdAt) {
        this.id = id;
        this.productName = productName;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(productName, that.productName) && Objects.equals(amount, that.amount) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, amount, createdAt);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                '}';
    }
}
