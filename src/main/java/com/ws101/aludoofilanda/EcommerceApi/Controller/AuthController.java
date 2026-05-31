package com.ws101.aludoofilanda.EcommerceApi.Controller;

import com.ws101.aludoofilanda.EcommerceApi.Model.UserModel;
import com.ws101.aludoofilanda.EcommerceApi.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Handles authentication endpoints.
 * Register endpoint is publicly accessible.
 * Login and logout are handled by Spring Security.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Register a new user.
     * Accepts username, password, and role.
     * Hashes the password before saving.
     * Publicly accessible so anyone can sign up.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserModel user) {
        authService.register(user);
        return ResponseEntity.status(201).body("User registered successfully");
    }
}