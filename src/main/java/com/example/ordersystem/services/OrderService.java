package com.example.ordersystem.services;

import com.example.ordersystem.Repositories.CustomerRepository;
import com.example.ordersystem.Repositories.OrderRepository;
import com.example.ordersystem.Repositories.ProductRepository;
import com.example.ordersystem.dtos.CreateOrderDto;
import com.example.ordersystem.entitys.Customer;
import com.example.ordersystem.entitys.Order;
import com.example.ordersystem.entitys.Product;
import com.example.ordersystem.exception.CustomHttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public Order craeteOrder(CreateOrderDto order, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new CustomHttpException("Customer with" + customerId + " not found", HttpStatus.NOT_FOUND);
        }
        Product product = productRepository.findById(order.getProductId()).orElse(null);
        if(product == null){
            throw new CustomHttpException("Product id is required", HttpStatus.NOT_FOUND);
        }
        if(product.getProductQuantity() < order.getQuantity()){
            throw new CustomHttpException("Product quantity is not enough", HttpStatus.NOT_FOUND);
        }
       Order newOrder = Order
                .builder()
                .orderStatus("Pending")
                .quantity(order.getQuantity())
                .totalPrice(order.getQuantity() * product.getProductPrice())
                .customerId(customer.getCustomerId())
                .productId(order.getProductId())
                .build();
        product.setProductQuantity(product.getProductQuantity() - order.getQuantity());

        productRepository.save(product);

       return orderRepository.save(newOrder);
    }

    public Order getOrderById(Long id) {
       Order order = orderRepository.findById(id).orElse(null);
         if(order == null){
              throw new CustomHttpException("Order with id " + id + " not found", HttpStatus.NOT_FOUND);
         }
            return order;
    }

}
