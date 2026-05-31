package com.ws101.aludoofilanda.EcommerceApi.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

/**
 * Data Transfer Object for user registration.
 * Ensures only required fields are accepted from the request.
 * Applies strict validation constraints before reaching the Service layer.
 */
@Getter
@Setter
@NoArgsConstructor
public class RegisterUserDto {

    @NotBlank(message = "Username is required")
    @Size(min = 8, max = 20, message = "Username must be between 8 and 20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Role is required")
    private String role;
}