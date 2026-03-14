package com.ecommerce.ecommerce_java.service;

import com.ecommerce.ecommerce_java.model.Cart;
import com.ecommerce.ecommerce_java.model.Product;
import com.ecommerce.ecommerce_java.model.User;
import com.ecommerce.ecommerce_java.repository.CartRepository;
import com.ecommerce.ecommerce_java.repository.ProductRepository;
import com.ecommerce.ecommerce_java.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Cart addToCart(String username, Long productId, int quantity) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<Cart> existingCart = cartRepository.findByUserAndProductId(user, productId);
        if (existingCart.isPresent()) {
            Cart cart = existingCart.get();
            cart.setQuantity(cart.getQuantity() + quantity);
            return cartRepository.save(cart);
        }

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(quantity);
        cart.setPrice(product.getPrice());
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getCartItems(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cartRepository.findByUser(user);
    }

    @Override
    public void removeFromCart(Long cartItemId) {
        cartRepository.deleteById(cartItemId);
    }

    @Override
    public void clearCart(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        cartRepository.deleteByUser(user);
    }
}