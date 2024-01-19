package com.example.ordersystem.services;

import com.example.ordersystem.Repositories.CustomerRepository;
import com.example.ordersystem.entitys.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class CustomerService {
    final private CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer AddCustomer(Customer customer){
        Customer customerExists = customerRepository.findCustomerByEmail(customer.getEmail());
        if(customerExists != null){
            //throw new IllegalStateException("Customer already exists");
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Customer already exists");
        }
        return customerRepository.save(customer);
    }
}
