package com.ws101.aludoofilanda.EcommerceApi.Repository;

import com.ws101.aludoofilanda.EcommerceApi.Model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    List<ProductModel> findByCategoryIgnoreCase(String category);

    List<ProductModel> findByNameContainingIgnoreCase(String name);

    List<ProductModel> findByPriceBetween(double min, double max);
}