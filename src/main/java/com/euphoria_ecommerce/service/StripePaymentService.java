package com.euphoria_ecommerce.service;

import com.euphoria_ecommerce.dto.StripeChargeDto;
import com.euphoria_ecommerce.dto.StripeTokenDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class StripePaymentService {

    @Value("$api.stripe.key")
    private String stripeApiKey;

    public void init() {
        Stripe.apiKey = stripeApiKey;
    }






    public StripeTokenDto createCardToken(StripeTokenDto model) {
        try {
            Map<String, Object> card = new HashMap<>();
            card.put("number", model.getCardNum());
            card.put("exp_month", Integer.parseInt(model.getExpMonth()));
            card.put("exp_year", Integer.parseInt(model.getExpYear()));
            card.put("cvc", model.getCvc());

            Map<String, Object> params = new HashMap<>();
            params.put("card", card);
            Token token = Token.create(params);
            if (token != null && token.getId() != null) {
                model.setSuccess(true);
                model.setToken(token.getId());
            }
            return model;
        } catch (StripeException e) {
            log.error("StripePaymentService (createCardToken)", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public StripeChargeDto charge(StripeChargeDto chargeRequest) {
        try {
            chargeRequest.setSuccess(String.valueOf(false));
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", (int)(chargeRequest.getAmount() * 100));
            chargeParams.put("currency", "AZN");
            chargeParams.put("description", "Payment for Id" + chargeRequest.getAdditionalInfo().getOrDefault("ID_TAG", ""));
            chargeParams.put("source", chargeRequest.getStripeToken());
            Map<String, Object> metaData =new HashMap<>();
            metaData.put("id", chargeRequest.getChargeId());
            metaData.putAll(chargeRequest.getAdditionalInfo());
            chargeParams.put("metaData", metaData);
            Charge charge = Charge.create(chargeParams);
            chargeRequest.setMessage(charge.getOutcome().getSellerMessage());

            if (charge.getPaid()) {
                chargeRequest.setChargeId(charge.getId());
                chargeRequest.setSuccess(String.valueOf(true));
            }
            return chargeRequest;

        } catch (StripeException e) {
            log.error("StripePaymentService (charge)", e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
