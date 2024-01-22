package com.example.ordersystem.services;

import com.example.ordersystem.Repositories.ProductRepository;
import com.example.ordersystem.dtos.AddProductDto;
import com.example.ordersystem.entitys.Product;
import com.example.ordersystem.exception.CustomHttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        List<Product> products= productRepository.findAll();
        if (products.isEmpty()){
            throw new CustomHttpException("Product not found", HttpStatus.NOT_FOUND);
        }
        return products;
    }

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null){
            throw new CustomHttpException("Product with" + id + " not found", HttpStatus.NOT_FOUND);
        }
        return product;
    }

    public Product addProduct(AddProductDto product) {
        Product productExists = productRepository.findProductByProductName(product.getProductName());
        if(productExists != null){
            throw new CustomHttpException("Product already exists", HttpStatus.CONFLICT);
        }
        Product newProduct = Product
                    .builder()
                    .productName(product.getProductName())
                    .productCategory(product.getProductCategory())
                    .productDescription(product.getProductDescription())
                    .productQuantity(product.getProductQuantity())
                    .productPrice(product.getProductPrice())
                    .productStatus(product.isProductStatus())
                    .build();
          return productRepository.save(newProduct);
    }

    public void deleteProduct(Long productId){
        Product productExit = productRepository.findById(productId).orElse(null);
        if(productExit == null){
            throw  new CustomHttpException("Product with" + productId + " not found", HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(productId);
    }
}
