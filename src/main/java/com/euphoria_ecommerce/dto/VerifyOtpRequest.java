package com.euphoria_ecommerce.dto;

public record VerifyOtpRequest(
        String email,
        Integer otp
) {
}
