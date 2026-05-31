package com.ws101.aludoofilanda.EcommerceApi.DTO;

/**
 * Data Transfer Object for product listing responses.
 * Returns only essential fields to keep responses concise.
 * Prevents exposing unnecessary data like category relationships.
 */
public record ProductListingDto(
        Long id,
        String name,
        Double price,
        String description,
        Integer stockQuantity,
        String imageUrl
) {}