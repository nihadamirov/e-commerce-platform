package com.euphoria_ecommerce.dto;

import lombok.Builder;

@Builder
public record ChangePassword(
        String password,
        String confirmPassword
) {
}
