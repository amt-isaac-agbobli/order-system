package com.example.ordersystem.Repositories;

import com.example.ordersystem.entitys.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);
    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.orderStatus = ?1 WHERE o.id = ?2")
    void updateByOrderStatus(String orderStatus, Long id);

}
