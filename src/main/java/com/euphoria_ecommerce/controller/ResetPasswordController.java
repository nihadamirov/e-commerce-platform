package com.euphoria_ecommerce.controller;

import com.euphoria_ecommerce.dto.ChangePassword;
import com.euphoria_ecommerce.dto.ResponseMessage;
import com.euphoria_ecommerce.dto.VerifyEmailRequest;
import com.euphoria_ecommerce.dto.VerifyOtpRequest;
import com.euphoria_ecommerce.service.ResetPasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class ResetPasswordController {

    private final ResetPasswordService resetPasswordService;

    public ResetPasswordController(ResetPasswordService resetPasswordService) {
        this.resetPasswordService = resetPasswordService;
    }


    @PostMapping("/verify-email")
    public ResponseEntity<ResponseMessage> verifyEmail(@RequestBody VerifyEmailRequest request) throws Exception {
        resetPasswordService.verifyEmail(request);
        ResponseMessage message = new ResponseMessage("Email sent for verification!");
        return ResponseEntity.ok(message);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ResponseMessage> verifyOtp(@RequestBody VerifyOtpRequest request) throws Exception {
        resetPasswordService.verifyOtp(request);
        ResponseMessage message = new ResponseMessage("OTP verified successfully!");
        return ResponseEntity.ok(message);
    }

    @PostMapping("/change-password/{email}")
    public ResponseEntity<ResponseMessage> changePassword(@RequestBody ChangePassword changePassword,
                                                          @PathVariable String email) {
        resetPasswordService.changePassword(changePassword, email);
        ResponseMessage message = new ResponseMessage("Password changed successfully!");
        return ResponseEntity.ok(message);
    }
}
