package com.example.ordersystem.Repositories;

import com.example.ordersystem.entitys.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.beans.Transient;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByEmail(String email);



    @Transient
    @Query(value = "UPDATE customers SET first_name = :firstName, last_name = :lastName, email = :email, address = :address WHERE id = :id",
            nativeQuery = true)
    Customer updateCustomerById(@Param("id") Long id,
                                @Param("firstName") String firstName,
                                @Param("lastName") String lastName,
                                @Param("email") String email,
                                @Param("address") String address);
}
