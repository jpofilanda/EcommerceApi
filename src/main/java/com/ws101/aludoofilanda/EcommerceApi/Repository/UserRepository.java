package com.ws101.aludoofilanda.EcommerceApi.Repository;

import com.ws101.aludoofilanda.EcommerceApi.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for UserModel.
 * Provides method to find a user by username for authentication.
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);
}