package com.euphoria_ecommerce.controller;

import com.euphoria_ecommerce.dto.StripeChargeDto;
import com.euphoria_ecommerce.dto.StripeTokenDto;
import com.euphoria_ecommerce.service.StripePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stripe")
public class StripePaymentController {

    private final StripePaymentService stripePaymentService;

    public StripePaymentController(StripePaymentService stripePaymentService) {
        this.stripePaymentService = stripePaymentService;
    }


    @PostMapping("/card/token")
    @ResponseBody
    public StripeTokenDto createCardToken(@RequestBody StripeTokenDto model) {
        return stripePaymentService.createCardToken(model);
    }


    @PostMapping("/charge")
    @ResponseBody
    public StripeChargeDto charge(@RequestBody StripeChargeDto model) {
        return stripePaymentService.charge(model);
    }
}
