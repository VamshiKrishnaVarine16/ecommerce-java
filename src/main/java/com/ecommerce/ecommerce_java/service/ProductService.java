package com.ecommerce.ecommerce_java.service;

import com.ecommerce.ecommerce_java.model.Product;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    void deleteProduct(Long id);
    Product updateProduct(Long id, Product product);
    List<Product> getProductsByCategory(String category);
    List<Product> searchProductsByName(String name);
}