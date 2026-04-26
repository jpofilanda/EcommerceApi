package com.ws101.aludoofilanda.EcommerceApi.Service;

import com.ws101.aludoofilanda.EcommerceApi.Model.ProductModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<ProductModel> products = new ArrayList<>();
    private Long nextId = 1L;

    // In-memory storage using ArrayList instead of database
    // ID is manually generated using incrementing counter (nextId)

    public ProductService() {
        addSample("Laptop", "Gaming laptop", 50000.0, "Electronics", 10);
        addSample("Phone", "Android phone", 15000.0, "Electronics", 20);
        addSample("Headphones", "Noise cancelling", 3000.0, "Electronics", 15);
        addSample("Shoes", "Running shoes", 2500.0, "Fashion", 30);
        addSample("Shirt", "Cotton shirt", 800.0, "Fashion", 50);
        addSample("Watch", "Smart watch", 7000.0, "Accessories", 12);
        addSample("Bag", "Backpack", 1200.0, "Accessories", 18);
        addSample("Keyboard", "Mechanical keyboard", 3500.0, "Electronics", 10);
        addSample("Mouse", "Wireless mouse", 1200.0, "Electronics", 25);
        addSample("Chair", "Office chair", 4500.0, "Furniture", 8);
    }

    private void addSample(String name, String desc, Double price,
                           String category, Integer stock) {

        ProductModel product = new ProductModel();
        product.setId(nextId++);
        product.setName(name);
        product.setDescription(desc);
        product.setPrice(price);
        product.setCategory(category);
        product.setStockQuantity(stock);
        product.setImageUrl("");

        products.add(product);
    }

    public List<ProductModel> getAllProducts() {
        return products;
    }

    public ProductModel getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public ProductModel createProduct(ProductModel product) {
        if (product.getStockQuantity() == null) product.setStockQuantity(0);
        if (product.getPrice() == null) product.setPrice(0.0);
        product.setId(nextId++);
        products.add(product);
        return product;
    }

    public ProductModel updateProduct(Long id, ProductModel updated) {
        for (ProductModel p : products) {
            if (p.getId().equals(id)) {
                p.setName(updated.getName());
                p.setDescription(updated.getDescription());
                p.setPrice(updated.getPrice());
                p.setCategory(updated.getCategory());
                p.setStockQuantity(updated.getStockQuantity());
                p.setImageUrl(updated.getImageUrl());
                return p;
            }
        }
        return null;
    }

    public boolean deleteProduct(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }

    public List<ProductModel> filterByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    public List<ProductModel> searchByName(String name) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public List<ProductModel> filterByPrice(double min, double max) {
        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .toList();
    }
}
