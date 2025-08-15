package com.example.products.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "category-service", url = "${CATEGORIES_SERVICE_URL:http://localhost:8089}")
public interface CategoryClient {
    @GetMapping("/categories")
    List<CategoryDTO> getAllCategories();

    @GetMapping("/categories/{id}")
    CategoryDTO getCategoryById(@PathVariable("id") Long id);
}
