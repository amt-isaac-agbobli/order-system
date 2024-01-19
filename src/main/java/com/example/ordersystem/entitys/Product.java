package com.example.ordersystem.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    private Long productId;

    @Column(nullable = false)
    private String productName;

    private String productDescription;

    @Column(nullable = false)
    private String productCategory;

    @Column(nullable = false)
    private double productPrice;

    @Column(nullable = false)
    private int productQuantity;

    @Column(nullable = false)
    private boolean productStatus;
}
