package com.example.ordersystem.Repositories;

import com.example.ordersystem.entitys.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
