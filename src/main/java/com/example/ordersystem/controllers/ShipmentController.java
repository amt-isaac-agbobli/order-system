package com.example.ordersystem.controllers;

import com.example.ordersystem.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/shipments")
public class ShipmentController {
    private final ShipmentService shipmentService;
    @Autowired
    public ShipmentController(ShipmentService shipmentService){
        this.shipmentService = shipmentService;
    }

    @PostMapping("/add/{orderId}")
    public ResponseEntity<Object> addOrderToShipment(@PathVariable long orderId, @RequestParam String shipmentStatus){
        return ResponseEntity.status(201).body(shipmentService.addOrderToShipment(orderId, shipmentStatus));
    }

}
