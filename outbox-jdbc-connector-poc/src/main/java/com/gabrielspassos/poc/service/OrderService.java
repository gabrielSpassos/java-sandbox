package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.controller.request.OrderRequest;
import com.gabrielspassos.poc.entity.OrderEntity;
import com.gabrielspassos.poc.entity.OutboxEntity;
import com.gabrielspassos.poc.entity.value.JsonbPayload;
import com.gabrielspassos.poc.repository.OrderRepository;
import com.gabrielspassos.poc.repository.OutboxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OutboxRepository outboxRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public OrderEntity save(OrderRequest request) {
        var orderEntity = new OrderEntity();
        orderEntity.setDescription(request.description());
        var savedOrder = orderRepository.save(orderEntity);

        String payload = objectMapper.writeValueAsString(Map.of(
                "orderId", savedOrder.getId(),
                "description", savedOrder.getDescription()
        ));

        var outboxEntity = new OutboxEntity();
        outboxEntity.setAggregateType("ORDER");
        outboxEntity.setAggregateId(savedOrder.getId());
        outboxEntity.setEventType("ORDER_CREATED");
        outboxEntity.setPayload(new JsonbPayload(payload));
        outboxRepository.save(outboxEntity);

        return savedOrder;
    }
}
