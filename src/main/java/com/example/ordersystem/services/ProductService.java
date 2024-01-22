package com.example.ordersystem.services;

import com.example.ordersystem.Repositories.ProductRepository;
import com.example.ordersystem.dtos.AddProductDto;
import com.example.ordersystem.entitys.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(AddProductDto product) {
        Product newProduct = Product
                    .builder()
                    .productName(product.getProductName())
                    .productCategory(product.getProductCategory())
                    .productDescription(product.getProductDescription())
                    .productQuantity(product.getProductQuantity())
                    .productPrice(product.getProductPrice())
                    .productStatus(product.isProductStatus())
                    .build();
           productRepository.save(newProduct);
        return newProduct;
    }

    public void deleteProduct(Long productId){
        Product productExit = productRepository.findById(productId).orElse(null);
        if(productExit == null){
            throw  new RuntimeException("Product does not exit");
        }
        productRepository.deleteById(productId);
    }
}
