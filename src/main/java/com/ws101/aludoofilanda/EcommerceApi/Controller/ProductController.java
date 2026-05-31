package com.ws101.aludoofilanda.EcommerceApi.Controller;

import com.ws101.aludoofilanda.EcommerceApi.Model.ProductModel;
import com.ws101.aludoofilanda.EcommerceApi.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Public - anyone can view products
    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Public - anyone can view a single product
    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // Public - anyone can filter products
    @GetMapping("/filter")
    public ResponseEntity<List<ProductModel>> filterProducts(
            @RequestParam String filterType,
            @RequestParam String filterValue) {

        List<ProductModel> result;

        switch (filterType.toLowerCase()) {
            case "category":
                result = productService.filterByCategory(filterValue);
                break;
            case "name":
                result = productService.searchByName(filterValue);
                break;
            case "price":
                String[] range = filterValue.split("-");
                double min = Double.parseDouble(range[0]);
                double max = Double.parseDouble(range[1]);
                result = productService.filterByPrice(min, max);
                break;
            default:
                throw new IllegalArgumentException("Invalid filter type: " + filterType);
        }

        return ResponseEntity.ok(result);
    }

    // Admin only - only ADMIN role can create products
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProductModel> createProduct(@Valid @RequestBody ProductModel product) {
        return ResponseEntity.status(201).body(productService.createProduct(product));
    }

    // Admin only - only ADMIN role can update products
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable Long id,
                                                      @Valid @RequestBody ProductModel product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    // Admin only - only ADMIN role can patch products
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<ProductModel> patchProduct(@PathVariable Long id,
                                                     @RequestBody ProductModel product) {
        ProductModel existing = productService.getProductById(id);

        if (product.getName() != null) existing.setName(product.getName());
        if (product.getDescription() != null) existing.setDescription(product.getDescription());
        if (product.getImageUrl() != null) existing.setImageUrl(product.getImageUrl());
        if (product.getPrice() != null && product.getPrice() > 0)
            existing.setPrice(product.getPrice());
        if (product.getStockQuantity() != null && product.getStockQuantity() >= 0)
            existing.setStockQuantity(product.getStockQuantity());

        return ResponseEntity.ok(productService.createProduct(existing));
    }

    // Admin only - only ADMIN role can delete products
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}