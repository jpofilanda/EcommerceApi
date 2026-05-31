package com.ws101.aludoofilanda.EcommerceApi.Repository;

import com.ws101.aludoofilanda.EcommerceApi.Model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    List<ProductModel> findByNameContainingIgnoreCase(String name);

    List<ProductModel> findByCategoryNameIgnoreCase(String name);

    @Query("SELECT p FROM ProductModel p WHERE p.price BETWEEN :min AND :max")
    List<ProductModel> findByPriceBetween(@Param("min") double min, @Param("max") double max);
}