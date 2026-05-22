package com.gabrielspassos.poc.controller;

import com.gabrielspassos.poc.controller.request.OrderRequest;
import com.gabrielspassos.poc.entity.OrderEntity;
import com.gabrielspassos.poc.service.OrderService;
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
