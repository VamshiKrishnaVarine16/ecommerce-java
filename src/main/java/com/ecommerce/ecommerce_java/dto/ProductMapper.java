package com.ecommerce.ecommerce_java.dto;

import com.ecommerce.ecommerce_java.model.OrderItem;
import com.ecommerce.ecommerce_java.model.Product;
import com.ecommerce.ecommerce_java.model.Cart;
import com.ecommerce.ecommerce_java.model.Order;
import com.ecommerce.ecommerce_java.model.OrderItem;
import com.ecommerce.ecommerce_java.model.Cart;

public class ProductMapper {

    public static Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setCategory(dto.getCategory());
        product.setImageUrl(dto.getImageUrl());
        return product;
    }

    public static CartResponseDTO toCartResponseDTO(Cart cart) {
        CartResponseDTO dto = new CartResponseDTO();
        dto.setId(cart.getId());
        dto.setProductName(cart.getProduct().getName());
        dto.setCategory(cart.getProduct().getCategory());
        dto.setQuantity(cart.getQuantity());
        dto.setPrice(cart.getPrice());
        dto.setSubtotal(cart.getPrice() * cart.getQuantity());
        return dto;
    }

    public static ProductResponseDTO toResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setCategory(product.getCategory());
        dto.setImageUrl(product.getImageUrl());
        return dto;
    }

    public static OrderItemResponseDTO toOrderItemResponseDTO(OrderItem orderItem) {
        OrderItemResponseDTO dto = new OrderItemResponseDTO();
        dto.setId(orderItem.getId());
        dto.setProductName(orderItem.getProduct().getName());
        dto.setQuantity(orderItem.getQuantity());
        dto.setPrice(orderItem.getPrice());
        dto.setSubtotal(orderItem.getPrice() * orderItem.getQuantity());
        return dto;
    }

    public static OrderResponseDTO toOrderResponseDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setUsername(order.getUser().getUsername());
        dto.setOrderItems(order.getOrderItems().stream()
                .map(ProductMapper::toOrderItemResponseDTO)
                .collect(java.util.stream.Collectors.toList()));
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        dto.setOrderDate(order.getOrderDate());
        return dto;
    }
}