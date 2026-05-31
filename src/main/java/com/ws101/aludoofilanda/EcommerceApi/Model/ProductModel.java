package com.ws101.aludoofilanda.EcommerceApi.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * Represents a product in the e-commerce system.
 * Each product belongs to one Category (Many-to-One relationship).
 * Uses JPA annotations for database mapping and Lombok for boilerplate reduction.
 */
@Entity
@Table(name = "products")
@Getter
@Setter
@ToString(exclude = "category")
@EqualsAndHashCode(exclude = "category")
@NoArgsConstructor
public class ProductModel {

    /**
     * Auto-generated primary key for the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the product. Must be at least 3 characters.
     */
    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    /**
     * Description of the product.
     */
    @NotBlank(message = "Description is required")
    private String description;

    /**
     * Price of the product. Must be greater than 0.
     */
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    /**
     * Available stock quantity. Cannot be negative.
     */
    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stockQuantity;

    /**
     * URL of the product image. Optional field.
     */
    private String imageUrl;

    /**
     * The category this product belongs to.
     * Many products can belong to one Category (Many-to-One relationship).
     * FetchType.LAZY means category is only loaded when accessed.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryModel category;
}