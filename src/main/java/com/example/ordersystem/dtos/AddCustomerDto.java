package com.example.ordersystem.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddCustomerDto {
    @NotNull(message = "First name is required")
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotNull(message = "Last name is required")
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotNull(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;
    private String address;
}
