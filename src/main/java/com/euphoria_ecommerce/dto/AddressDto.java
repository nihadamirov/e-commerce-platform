package com.euphoria_ecommerce.dto;

public record AddressDto(
        String region,
        String state,
        String city,
        String street,
        String companyName,
        String homeType,
        String phone,
        String postalCode
) {
}
