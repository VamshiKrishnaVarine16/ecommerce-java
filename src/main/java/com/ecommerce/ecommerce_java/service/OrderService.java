package com.ecommerce.ecommerce_java.service;

import com.ecommerce.ecommerce_java.model.Order;
import java.util.List;

public interface OrderService {

    Order placeOrder(String username);
    List<Order> getOrdersByUser(String username);
    Order getOrderById(Long orderId);
}