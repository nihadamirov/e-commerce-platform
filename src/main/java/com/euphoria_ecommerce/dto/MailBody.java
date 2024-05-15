package com.euphoria_ecommerce.dto;

import lombok.Builder;

@Builder
public record MailBody(String to,
                       String subject,
                       String text) {
}
