package com.example.products.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "category-service", url = "${CATEGORIES_SERVICE_URL}")
public interface CategoryClient {
    @GetMapping("/api/categorias")
    List<CategoryDTO> getAllCategories();

    @GetMapping("/api/categorias/{id}")
    CategoryDTO getCategoryById(@PathVariable("id") Long id);
}
