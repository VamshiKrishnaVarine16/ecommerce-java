package com.ecommerce.ecommerce_java.repository;

import com.ecommerce.ecommerce_java.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}