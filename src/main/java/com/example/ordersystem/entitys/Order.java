package com.example.ordersystem.entitys;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    private Long orderId;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private String orderStatus;

    @ManyToOne
    @JoinColumn(name = "customerId",
                referencedColumnName = "customerId",
                insertable = false,
                updatable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "productId",
                referencedColumnName = "productId",
                insertable = false,
                updatable = false)
    private Product product;
}
