package com.ecommerce.ecommerce_java.service;

import com.ecommerce.ecommerce_java.model.Cart;
import java.util.List;

public interface CartService {

    Cart addToCart(String username, Long productId, int quantity);
    List<Cart> getCartItems(String username);
    void removeFromCart(Long cartItemId);
    void clearCart(String username);
}