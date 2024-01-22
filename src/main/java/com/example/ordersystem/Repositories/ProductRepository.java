package com.example.ordersystem.Repositories;

import com.example.ordersystem.entitys.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByProductName(String productName);
    @Transactional
    @Modifying
    @Query(value = "UPDATE products SET product_name = :productName, product_category = :productCategory, product_description = :productDescription, product_quantity = :productQuantity, product_price = :productPrice, product_status = :productStatus WHERE product_id = :id",
            nativeQuery = true)
    Product updateProductById(@Param("id")Long id,
                             @Param("productName") String productName,
                              @Param("productCategory") String productCategory,
                              @Param("productDescription") String productDescription,
                              @Param("productQuantity") int productQuantity,
                              @Param("productPrice") double productPrice,
                              @Param("productStatus") boolean productStatus);

    List<Product> findProductByProductStatus(boolean productStatus);

    List<Product> findProductByProductCategory(String productCategory);

    List<Product> findProductByProductCategoryAndProductStatus(String productCategory, boolean productStatus);

    List<Product> findProductByProductNameContaining(String productName);
}
