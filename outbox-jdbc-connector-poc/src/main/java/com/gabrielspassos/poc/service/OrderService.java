package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.controller.request.OrderRequest;
import com.gabrielspassos.poc.entity.OrderEntity;
import com.gabrielspassos.poc.repository.OrderRepository;
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
