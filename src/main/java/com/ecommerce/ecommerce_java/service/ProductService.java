package com.ecommerce.ecommerce_java.service;

import com.ecommerce.ecommerce_java.model.Product;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    void deleteProduct(Long id);
    Product updateProduct(Long id, Product product);
    List<Product> getProductsByCategory(String category);
    Page<Product> getAllProductsPaginated(int page, int size);
    List<Product> searchProductsByName(String name);
}