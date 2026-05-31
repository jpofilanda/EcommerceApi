package com.ws101.aludoofilanda.EcommerceApi.Service;

import com.ws101.aludoofilanda.EcommerceApi.Model.CategoryModel;
import com.ws101.aludoofilanda.EcommerceApi.Model.ProductModel;
import com.ws101.aludoofilanda.EcommerceApi.Repository.CategoryRepository;
import com.ws101.aludoofilanda.EcommerceApi.Repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductModel getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + id + " not found"));
    }

    public ProductModel createProduct(ProductModel product) {
        return productRepository.save(product);
    }

    public ProductModel updateProduct(Long id, ProductModel updated) {
        ProductModel existing = getProductById(id);
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        existing.setStockQuantity(updated.getStockQuantity());
        existing.setImageUrl(updated.getImageUrl());
        return productRepository.save(existing);
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        throw new EntityNotFoundException("Product with ID " + id + " not found");
    }

    public List<ProductModel> filterByCategory(String categoryName) {
        return productRepository.findByCategoryNameIgnoreCase(categoryName);
    }

    public List<ProductModel> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<ProductModel> filterByPrice(double min, double max) {
        return productRepository.findByPriceBetween(min, max);
    }
}