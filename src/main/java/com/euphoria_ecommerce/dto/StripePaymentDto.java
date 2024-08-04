package com.euphoria_ecommerce.dto;

import lombok.Data;

@Data
public class StripePaymentDto {

    private String cardNum;
    private String expMonth;
    private String expYear;
    private String cvc;
    private String token;
    private String userName;
    private boolean success;
}
