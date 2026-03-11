package com.ecommerce.ecommerce_java.controller;

import com.ecommerce.ecommerce_java.dto.ProductMapper;
import com.ecommerce.ecommerce_java.dto.ProductRequestDTO;
import com.ecommerce.ecommerce_java.dto.ProductResponseDTO;
import com.ecommerce.ecommerce_java.model.Product;
import com.ecommerce.ecommerce_java.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts()
                .stream()
                .map(ProductMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ProductMapper.toResponseDTO(product));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO dto) {
        Product product = productService.createProduct(ProductMapper.toEntity(dto));
        return ResponseEntity.status(201).body(ProductMapper.toResponseDTO(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO dto) {
        Product updated = productService.updateProduct(id, ProductMapper.toEntity(dto));
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ProductMapper.toResponseDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(@PathVariable String category) {
        List<ProductResponseDTO> products = productService.getProductsByCategory(category)
                .stream()
                .map(ProductMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDTO>> searchProductsByName(@RequestParam String name) {
        List<ProductResponseDTO> products = productService.searchProductsByName(name)
                .stream()
                .map(ProductMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }
}