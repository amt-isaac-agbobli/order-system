package com.example.ordersystem.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCustomerDetailsDto {
    @NotNull(message = "First name cannot be null")
    @Size(min = 5, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;
    @NotNull(message = "Last name cannot be null")
    @Size(min = 5, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;
    private String address;
}
