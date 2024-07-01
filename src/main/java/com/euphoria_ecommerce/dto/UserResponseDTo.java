package com.euphoria_ecommerce.dto;

public record UserResponseDTo(
        Long id,
        String email,
        String fullName,
        String phone,
        AddressDto addressDto
) {
}
