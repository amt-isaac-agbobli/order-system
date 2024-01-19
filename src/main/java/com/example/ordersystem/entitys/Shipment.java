package com.example.ordersystem.entitys;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shipment {
    @Id
    @SequenceGenerator(
            name = "shipment_sequence",
            sequenceName = "shipment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipment_sequence")
    private Long shipmentId;
    @Column(nullable = false)
    private Long orderId;
    @Column(nullable = false)
    private String shipmentStatus;

    @ManyToOne
    @JoinColumn(name = "orderId",
                referencedColumnName = "orderId",
                insertable = false,
                updatable = false)
    private Order order;

}
