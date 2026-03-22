package com.ecommerce.ecommerce_java.repository;

import com.ecommerce.ecommerce_java.model.Cart;
import com.ecommerce.ecommerce_java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUser(User user);
    Optional<Cart> findByUserAndProductId(User user, Long productId);

    @Transactional
    void deleteByUser(User user);
}