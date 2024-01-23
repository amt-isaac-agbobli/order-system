package com.example.ordersystem.Repositories;

import com.example.ordersystem.entitys.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Shipment s SET s.shipmentStatus = ?1 WHERE s.id = ?2")
    void updateShipmenStatus(String shipmentStatus, Long id);
}
