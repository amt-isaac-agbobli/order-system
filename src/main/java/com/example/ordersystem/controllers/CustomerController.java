package com.example.ordersystem.controllers;

import com.example.ordersystem.dtos.AddCustomerDto;
import com.example.ordersystem.dtos.UpdateCustomerDetailsDto;
import com.example.ordersystem.entitys.Customer;
import com.example.ordersystem.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@Valid  @RequestBody AddCustomerDto customer){
        return ResponseEntity.status(201).body(customerService.AddCustomer(customer));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        return ResponseEntity.status(200).body(customerService.getCustomerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomerDetails(@PathVariable Long id,
                                                          @RequestBody UpdateCustomerDetailsDto customer){
        customerService.updateCustomerDetails(id, customer);
        return ResponseEntity.status(200).body("Customer updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(200).body("Customer deleted successfully");
    }

}
