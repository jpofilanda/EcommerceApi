package com.ws101.aludoofilanda.EcommerceApi.Repository;

import com.ws101.aludoofilanda.EcommerceApi.Model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {

    Optional<CategoryModel> findByNameIgnoreCase(String name);
}