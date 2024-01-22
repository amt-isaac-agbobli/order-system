package com.example.ordersystem.controllers;

import com.example.ordersystem.dtos.CreateOrderDto;
import com.example.ordersystem.entitys.Order;
import com.example.ordersystem.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private  final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    @PostMapping("/create/{customerId}")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody CreateOrderDto order,
                                             @PathVariable() Long customerId){
        return ResponseEntity.status(201).body(orderService.craeteOrder(order, customerId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable() Long id){
        return ResponseEntity.status(200).body(orderService.getOrderById(id));
    }
}
