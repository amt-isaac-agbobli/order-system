package com.example.ordersystem.services;

import com.example.ordersystem.Repositories.OrderRepository;
import com.example.ordersystem.Repositories.ShipmentRepository;
import com.example.ordersystem.entitys.Order;
import com.example.ordersystem.entitys.Shipment;
import com.example.ordersystem.exception.CustomHttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public ShipmentService(ShipmentRepository shipmentRepository, OrderRepository orderRepository) {
        this.shipmentRepository = shipmentRepository;
        this.orderRepository = orderRepository;
    }

    public Object addOrderToShipment(Long orderId, String shipmentStatus){
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null){
            throw new CustomHttpException("Order not found", HttpStatus.NOT_FOUND);
        }
        Shipment shipment = Shipment
                .builder()
                .order(order)
                .shipmentStatus(shipmentStatus)
                .orderId(orderId)
                .build();
        shipmentRepository.save(shipment);

        orderRepository.updateByOrderStatus("Shipped", orderId);

        return Map.entry("message", "Order added to shipment");
    }

    public Object updateShipmentStatus(Long shipmentId, String shipmentStatus){
        Shipment shipment = shipmentRepository.findById(shipmentId).orElse(null);
        if (shipment == null){
            throw new CustomHttpException("Shipment not found", HttpStatus.NOT_FOUND);
        }
        shipmentRepository.updateShipmenStatus(shipmentStatus, shipmentId);
        return Map.entry("message", "Shipment status updated successfully");
    }
}
