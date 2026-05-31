package com.ws101.aludoofilanda.EcommerceApi.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

/**
 * Data Transfer Object for creating a product.
 * Ensures only required fields are accepted from the request.
 * Validates input before it reaches the Service layer.
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateProductDto {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stockQuantity;

    private String imageUrl;
}