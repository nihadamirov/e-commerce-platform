package com.euphoria_ecommerce.service;

import com.euphoria_ecommerce.dto.ChangePassword;
import com.euphoria_ecommerce.dto.MailBody;
import com.euphoria_ecommerce.dto.VerifyEmailRequest;
import com.euphoria_ecommerce.dto.VerifyOtpRequest;
import com.euphoria_ecommerce.model.ResetPassword;
import com.euphoria_ecommerce.model.User;
import com.euphoria_ecommerce.repository.ResetPasswordRepository;
import com.euphoria_ecommerce.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Service
public class ResetPasswordService {

    private final UserRepository userRepository;
    private final ResetPasswordRepository resetPasswordRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public ResetPasswordService(UserRepository userRepository, ResetPasswordRepository resetPasswordRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.resetPasswordRepository = resetPasswordRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public void verifyEmail(VerifyEmailRequest request) throws Exception {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("Please provide a valid email"));

        String otp = otpGenerator();
        MailBody mailBody = MailBody.builder()
                .to(request.email())
                .text("This is the OTP for your Forgot Password request: " + otp)
                .subject("OTP for forgot Password request")
                .build();

        ResetPassword fp = ResetPassword.builder()
                .otp(Integer.valueOf(otp))
                .expirationTime(new Date(System.currentTimeMillis() + 70 * 1000))
                .user(user)
                .build();

        emailService.sendSimpleMessage(mailBody);
        resetPasswordRepository.save(fp);
    }

    public void verifyOtp(VerifyOtpRequest request) throws Exception {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("Please provide a valid email"));

        ResetPassword fp = resetPasswordRepository.findByOtpAndUser(Integer.valueOf(request.otp()), user)
                .orElseThrow(() -> new RuntimeException("Invalid OTP for email: " + request.email()));

        if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
            resetPasswordRepository.deleteById(fp.getResetPasswordId());
            throw new RuntimeException("OTP has expired!");
        }
    }

    public void changePassword(ChangePassword changePassword, String email) {
        if (!Objects.equals(changePassword.password(), changePassword.confirmPassword())) {
            throw new RuntimeException("Please enter the password again!");
        }

        String encodedPassword = passwordEncoder.encode(changePassword.password());
        userRepository.updatePassword(email, encodedPassword);
    }

    private String otpGenerator() {
        Random rand = new Random();
        return String.valueOf(rand.nextInt(100_000, 999_999));
    }
    }

