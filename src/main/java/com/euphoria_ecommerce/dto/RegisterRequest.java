package com.euphoria_ecommerce.dto;

import com.euphoria_ecommerce.enums.Role;

public record RegisterRequest (
        String email,
        String password,
        Role role) {
}
