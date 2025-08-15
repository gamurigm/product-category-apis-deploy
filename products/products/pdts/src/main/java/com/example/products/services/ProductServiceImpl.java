package com.example.products.services;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.products.models.entities.Product;
import com.example.products.repositories.ProductRepository;
import com.example.products.client.CategoryClient;
import com.example.products.client.CategoryDTO;

@Service
@jakarta.transaction.Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryClient categoryClient;

    public ProductServiceImpl(ProductRepository productRepository, CategoryClient categoryClient) {
        this.productRepository = productRepository;
        this.categoryClient = categoryClient;
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        // Validar que la categoría exista usando Feign Client
        CategoryDTO category = null;
        try {
            category = categoryClient.getCategoryById(product.getCategoryId());
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "La categoría con id " + product.getCategoryId() + " no existe o no se pudo consultar.");
        }
        if (category == null || category.getId() == null) {
            throw new IllegalArgumentException("La categoría con id " + product.getCategoryId() + " no existe.");
        }
        return productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, Product product) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product with id " + id + " does not exist.");
        }
        product.setId(id);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product with id " + id + " does not exist.");
        }
        productRepository.deleteById(id);
    }
}
