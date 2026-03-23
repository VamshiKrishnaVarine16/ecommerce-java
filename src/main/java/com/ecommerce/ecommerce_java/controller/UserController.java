package com.ecommerce.ecommerce_java.controller;

import com.ecommerce.ecommerce_java.model.User;
import com.ecommerce.ecommerce_java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(Principal principal) {
        User user = userService.getUserProfile(principal.getName());
        return ResponseEntity.ok(user);
    }
}