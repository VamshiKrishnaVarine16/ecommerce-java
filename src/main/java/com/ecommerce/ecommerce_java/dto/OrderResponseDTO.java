package com.ecommerce.ecommerce_java.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDTO {

    private Long id;
    private String username;
    private List<OrderItemResponseDTO> orderItems;
    private double totalAmount;
    private String status;
    private LocalDateTime orderDate;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public List<OrderItemResponseDTO> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItemResponseDTO> orderItems) { this.orderItems = orderItems; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
}