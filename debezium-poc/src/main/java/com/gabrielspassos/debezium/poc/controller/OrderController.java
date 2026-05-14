package com.gabrielspassos.debezium.poc.controller;

import com.gabrielspassos.debezium.poc.controller.request.OrderRequest;
import com.gabrielspassos.debezium.poc.entity.OrderEntity;
import com.gabrielspassos.debezium.poc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderEntity> save(@RequestBody OrderRequest orderRequest) {
        OrderEntity savedEntity = orderService.save(orderRequest);
        return ResponseEntity.ok(savedEntity);
    }
}
