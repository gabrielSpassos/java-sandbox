package com.gabrielspassos.debezium.poc.service;

import com.gabrielspassos.debezium.poc.controller.request.OrderRequest;
import com.gabrielspassos.debezium.poc.entity.OrderEntity;
import com.gabrielspassos.debezium.poc.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderEntity save(OrderRequest request) {
        var entity = new OrderEntity();
        entity.setProductName(request.productName());
        entity.setAmount(request.amount());
        return orderRepository.save(entity);
    }
}
