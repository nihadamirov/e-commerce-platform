package com.euphoria_ecommerce.dto;

import lombok.Data;

@Data
public class StripeTokenDto {

    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String cvc;
    private String userName;

    private String token;
    private boolean success;
}
