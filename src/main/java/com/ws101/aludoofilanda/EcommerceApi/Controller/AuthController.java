package com.ws101.aludoofilanda.EcommerceApi.Controller;

import com.ws101.aludoofilanda.EcommerceApi.DTO.RegisterUserDto;
import com.ws101.aludoofilanda.EcommerceApi.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Handles authentication endpoints.
 * Register endpoint is publicly accessible.
 * Me endpoint returns current logged in user info.
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
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterUserDto dto) {
        authService.register(dto);
        return ResponseEntity.status(201).body("User registered successfully");
    }

    /**
     * Returns current logged in user info.
     * Returns 401 automatically if not logged in.
     * Used by frontend to check authentication status.
     */
    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> getCurrentUser(
            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(Map.of(
                "username", userDetails.getUsername(),
                "role", userDetails.getAuthorities().iterator().next().getAuthority()
        ));
    }
}