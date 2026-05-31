package com.ws101.aludoofilanda.EcommerceApi.DTO;

import lombok.*;

/**
 * Data Transfer Object for login requests.
 * Contains username and password for authentication.
 */
@Getter
@Setter
@NoArgsConstructor
public class LoginDto {
    private String username;
    private String password;
}