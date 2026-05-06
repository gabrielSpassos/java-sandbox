package com.gabrielspassos.service;

import com.gabrielspassos.entity.OrderEntity;
import com.gabrielspassos.entity.OutboxEntity;
import com.gabrielspassos.repository.OrderRepository;
import com.gabrielspassos.repository.OutboxRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    public OrderService(OrderRepository orderRepository, OutboxRepository outboxRepository, ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public void createOrder(String description) {
        UUID orderId = UUID.randomUUID();

        OrderEntity order = new OrderEntity(orderId, description, Instant.now());
        orderRepository.save(order);

        var eventPayload = objectMapper.writeValueAsString(Map.of(
                "orderId", orderId,
                "description", description
        ));

        OutboxEntity outbox = new OutboxEntity(
                UUID.randomUUID(),
                "Order",
                orderId,
                "OrderCreated",
                eventPayload,
                Instant.now(),
                false
        );

        outboxRepository.save(outbox);
    }
}
