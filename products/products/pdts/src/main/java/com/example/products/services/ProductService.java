package com.example.products.services;

import com.example.products.models.entities.Product;
import java.util.Optional;

public interface ProductService {
    Iterable<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    Product createProduct(Product product);
    void updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
