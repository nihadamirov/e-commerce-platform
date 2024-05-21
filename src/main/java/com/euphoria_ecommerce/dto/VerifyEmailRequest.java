package com.euphoria_ecommerce.dto;

import lombok.Builder;

@Builder
public record VerifyEmailRequest(String email) {
}
