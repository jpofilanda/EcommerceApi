package com.ws101.aludoofilanda.EcommerceApi.Service;

import com.ws101.aludoofilanda.EcommerceApi.DTO.RegisterUserDto;
import com.ws101.aludoofilanda.EcommerceApi.Model.UserModel;
import com.ws101.aludoofilanda.EcommerceApi.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Handles user registration logic.
 * Accepts RegisterUserDto and maps it to UserModel.
 * Hashes the password using BCrypt before saving to the database.
 */
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user from RegisterUserDto.
     * Throws exception if username already exists.
     * Hashes password before saving.
     */
    public UserModel register(RegisterUserDto dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        UserModel user = new UserModel();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole() == null || dto.getRole().isEmpty()
                ? "ROLE_USER" : dto.getRole());

        return userRepository.save(user);
    }
}