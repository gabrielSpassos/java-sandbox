package com.gabrielspassos.debezium.poc.repository;

import com.gabrielspassos.debezium.poc.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<OrderEntity, UUID> {
}
