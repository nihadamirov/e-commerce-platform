package com.euphoria_ecommerce.dto;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class StripeChargeDto {
    private String stripeToken;
    private String username;
    private Double amount;
    private String success;
    private String message;
    private String chargeId;
    Map<String, Object> additionalInfo = new HashMap<>();


}
