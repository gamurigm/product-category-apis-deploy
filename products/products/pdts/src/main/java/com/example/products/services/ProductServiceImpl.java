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
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ProductServiceImpl.class);

    // Método para depurar la conexión con el microservicio de categorías
    public void checkCategoryService() {
        try {
            java.util.List<CategoryDTO> categories = categoryClient.getAllCategories();
            logger.info("Categorías disponibles: {}", categories);
        } catch (Exception e) {
            logger.error("Error al conectar con el servicio de categorías", e);
        }
    }

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
        // Validar que la categoría exista usando Feign Client y manejar errores
        // específicos
        try {
            CategoryDTO category = categoryClient.getCategoryById(product.getCategoryId());
            if (category == null || category.getId() == null) {
                throw new IllegalArgumentException("La categoría con id " + product.getCategoryId() + " no existe");
            }
            return productRepository.save(product);
        } catch (feign.FeignException.NotFound e) {
            throw new IllegalArgumentException("La categoría con id " + product.getCategoryId() + " no existe");
        } catch (feign.FeignException e) {
            throw new RuntimeException("Error al comunicarse con el servicio de categorías", e);
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al consultar la categoría", e);
        }
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
