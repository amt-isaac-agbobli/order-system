package com.example.ordersystem.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductDetailsDto {
    @NotBlank(message = "Product name is required")
    @NotNull(message = "Product name is required")
    private String productName;
    private String productDescription;
    @NotBlank(message = "Product category is required")
    private String productCategory;
    @Digits(integer=10, fraction=2, message = "Product price must be a number")
    @NotNull(message = "Product price is required")
    private double productPrice;
    @Digits(integer=10, fraction=0, message = "Product quantity must be a number")
    private int productQuantity;
    private boolean productStatus;
}
