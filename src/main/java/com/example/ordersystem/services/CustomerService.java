package com.example.ordersystem.services;

import com.example.ordersystem.Repositories.CustomerRepository;
import com.example.ordersystem.entitys.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            return null;
        }
        return customerRepository.save(customer);
    }
}
