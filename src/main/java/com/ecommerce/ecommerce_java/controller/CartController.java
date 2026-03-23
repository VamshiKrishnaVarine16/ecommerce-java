package com.ecommerce.ecommerce_java.controller;

import com.ecommerce.ecommerce_java.dto.CartResponseDTO;
import com.ecommerce.ecommerce_java.dto.ProductMapper;
import com.ecommerce.ecommerce_java.model.Cart;
import com.ecommerce.ecommerce_java.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartResponseDTO> addToCart(Principal principal,
                                                      @RequestParam Long productId,
                                                      @RequestParam int quantity) {
        Cart cart = cartService.addToCart(principal.getName(), productId, quantity);
        return ResponseEntity.status(201).body(ProductMapper.toCartResponseDTO(cart));
    }

    @GetMapping
    public ResponseEntity<List<CartResponseDTO>> getCartItems(Principal principal) {
        List<CartResponseDTO> cartItems = cartService.getCartItems(principal.getName())
                .stream()
                .map(ProductMapper::toCartResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cartItems);
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