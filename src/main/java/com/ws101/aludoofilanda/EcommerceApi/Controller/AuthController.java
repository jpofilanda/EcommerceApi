package com.ws101.aludoofilanda.EcommerceApi.Controller;

import com.ws101.aludoofilanda.EcommerceApi.DTO.LoginDto;
import com.ws101.aludoofilanda.EcommerceApi.DTO.RegisterUserDto;
import com.ws101.aludoofilanda.EcommerceApi.Service.AuthService;
import com.ws101.aludoofilanda.EcommerceApi.Service.JwtUtil;
import com.ws101.aludoofilanda.EcommerceApi.Service.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Handles authentication endpoints.
 * Register, Login (returns JWT), and Me endpoints.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthController(AuthService authService,
                          JwtUtil jwtUtil,
                          AuthenticationManager authenticationManager,
                          UserDetailsServiceImpl userDetailsService) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Register a new user.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterUserDto dto) {
        authService.register(dto);
        return ResponseEntity.status(201)
                .body("User registered successfully");
    }

    /**
     * Login endpoint - returns JWT token on success.
     * Frontend stores token and sends it in Authorization header.
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestBody LoginDto dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(), dto.getPassword()));

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(dto.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(Map.of("token", token));
    }

    /**
     * Returns current logged in user info.
     */
    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> getCurrentUser(
            @org.springframework.security.core.annotation.AuthenticationPrincipal
            UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(Map.of(
                "username", userDetails.getUsername(),
                "role", userDetails.getAuthorities()
                        .iterator().next().getAuthority()
        ));
    }
}