package com.ws101.aludoofilanda.EcommerceApi.Service;

import com.ws101.aludoofilanda.EcommerceApi.Model.ProductModel;
import com.ws101.aludoofilanda.EcommerceApi.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductModel getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public ProductModel createProduct(ProductModel product) {
        return productRepository.save(product);
    }

    public ProductModel updateProduct(Long id, ProductModel updated) {
        return productRepository.findById(id).map(p -> {
            p.setName(updated.getName());
            p.setDescription(updated.getDescription());
            p.setPrice(updated.getPrice());
            p.setCategory(updated.getCategory());
            p.setStockQuantity(updated.getStockQuantity());
            p.setImageUrl(updated.getImageUrl());
            return productRepository.save(p);
        }).orElse(null);
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<ProductModel> filterByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    public List<ProductModel> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<ProductModel> filterByPrice(double min, double max) {
        return productRepository.findByPriceBetween(min, max);
    }
}