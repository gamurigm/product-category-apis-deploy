package com.example.products.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.products.models.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
