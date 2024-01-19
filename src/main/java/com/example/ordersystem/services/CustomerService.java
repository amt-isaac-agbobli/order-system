package com.example.ordersystem.services;

import com.example.ordersystem.Repositories.CustomerRepository;
import com.example.ordersystem.entitys.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Objects;

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

    public List<Customer> getAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()){
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND, "No customers found");
        }
        return customers;
    }

    public Customer getCustomerById(Long id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null){
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        return customer;
    }

    public Customer updateCustomerDetails(Long id, Customer customer){
        Customer customerToUpdate = customerRepository.findById(id).orElse(null);
        if (customerToUpdate == null){
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        if (Objects.equals(customerToUpdate.getEmail(), customer.getEmail())){
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        Customer updatedCustomer = customerRepository.updateCustomerById(id,
                                                                         customer.getFirstName(),
                                                                         customer.getLastName(),
                                                                         customer.getEmail(),
                                                                         customer.getAddress());
        System.out.println(customerToUpdate);
        return updatedCustomer;
    }

    public void deleteCustomer(Long id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null){
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        customerRepository.deleteById(id);
    }
}
