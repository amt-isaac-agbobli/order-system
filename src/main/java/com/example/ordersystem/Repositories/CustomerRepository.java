package com.example.ordersystem.Repositories;

import com.example.ordersystem.entitys.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE customers SET first_name = :firstName, last_name = :lastName, address = :address WHERE customer_id = :id",
            nativeQuery = true)
    void updateCustomerById(@Param("id") Long id,
                            @Param("firstName") String firstName,
                            @Param("lastName") String lastName,
                            @Param("address") String address);
}