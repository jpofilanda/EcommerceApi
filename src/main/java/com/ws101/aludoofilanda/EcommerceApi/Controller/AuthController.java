package com.ws101.aludoofilanda.EcommerceApi.Controller;

import com.ws101.aludoofilanda.EcommerceApi.DTO.RegisterUserDto;
import com.ws101.aludoofilanda.EcommerceApi.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Handles authentication endpoints.
 * Register endpoint is publicly accessible.
 * Uses RegisterUserDto to validate input before reaching the Service layer.
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
     * Uses @Valid to trigger Bean Validation on RegisterUserDto.
     * Hashes the password before saving.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterUserDto dto) {
        authService.register(dto);
        return ResponseEntity.status(201).body("User registered successfully");
    }
}