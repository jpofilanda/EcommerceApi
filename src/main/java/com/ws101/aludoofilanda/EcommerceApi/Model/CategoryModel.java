package com.ws101.aludoofilanda.EcommerceApi.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

/**
 * Represents a product category in the e-commerce system.
 * One Category can have many Products (One-to-Many relationship).
 */
@Entity
@Table(name = "categories")
@Getter
@Setter
@ToString(exclude = "products")
@EqualsAndHashCode(exclude = "products")
@NoArgsConstructor
public class CategoryModel {

    /**
     * Auto-generated primary key for the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unique name of the category.
     */
    @NotBlank(message = "Category name is required")
    @Column(unique = true)
    private String name;

    /**
     * List of products belonging to this category.
     * One Category has many Products (One-to-Many relationship).
     * CascadeType.ALL means deleting a category also deletes its products.
     * FetchType.LAZY means products are only loaded when accessed.
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductModel> products;
}