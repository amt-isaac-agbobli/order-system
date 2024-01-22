package com.example.ordersystem.controllers;

import com.example.ordersystem.dtos.AddProductDto;
import com.example.ordersystem.dtos.UpdateProductDetailsDto;
import com.example.ordersystem.entitys.Product;
import com.example.ordersystem.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok().body(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable() Long id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody AddProductDto product) {
        return ResponseEntity.status(201).body(productService.addProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable()Long id){
        productService.deleteProduct(id);
        return ResponseEntity.status(200).body("Product is deleted Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable()Long id,
                                                @Valid @RequestBody UpdateProductDetailsDto product){
        productService.updateProduct(id, product);
        return ResponseEntity.status(200).body("Product is updated Successfully");
    }
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductByStatus(@RequestParam Boolean productStatus){
        return ResponseEntity.status(200).body(productService.searchProductByProductStatus(productStatus));
    }
    @GetMapping("/search/{productName}")
    public ResponseEntity<List<Product>> searchProductByName(@PathVariable()String productName){
        return ResponseEntity.status(200).body(productService.searchProductByProductName(productName));
    }
}
