package com.ecommerce.ecommerce_java.controller;

import com.ecommerce.ecommerce_java.dto.OrderResponseDTO;
import com.ecommerce.ecommerce_java.dto.ProductMapper;
import com.ecommerce.ecommerce_java.model.Order;
import com.ecommerce.ecommerce_java.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<OrderResponseDTO> placeOrder(Principal principal) {
        Order order = orderService.placeOrder(principal.getName());
        return ResponseEntity.status(201).body(ProductMapper.toOrderResponseDTO(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getMyOrders(Principal principal) {
        List<OrderResponseDTO> orders = orderService.getOrdersByUser(principal.getName())
                .stream()
                .map(ProductMapper::toOrderResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ProductMapper.toOrderResponseDTO(order));
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(@PathVariable Long orderId,
                                                               @RequestParam String status) {
        Order order = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(ProductMapper.toOrderResponseDTO(order));
    }
}