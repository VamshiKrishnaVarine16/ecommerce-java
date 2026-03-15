package com.ecommerce.ecommerce_java.controller;

import com.ecommerce.ecommerce_java.model.Cart;
import com.ecommerce.ecommerce_java.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(Principal principal,
                                          @RequestParam Long productId,
                                          @RequestParam int quantity) {
        Cart cart = cartService.addToCart(principal.getName(), productId, quantity);
        return ResponseEntity.status(201).body(cart);
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getCartItems(Principal principal) {
        return ResponseEntity.ok(cartService.getCartItems(principal.getName()));
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long cartItemId) {
        cartService.removeFromCart(cartItemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(Principal principal) {
        cartService.clearCart(principal.getName());
        return ResponseEntity.noContent().build();
    }
}