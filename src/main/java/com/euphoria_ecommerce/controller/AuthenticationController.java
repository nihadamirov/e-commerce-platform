package com.euphoria_ecommerce.controller;

import com.euphoria_ecommerce.dto.LoginRequest;
import com.euphoria_ecommerce.dto.RegisterRequest;
import com.euphoria_ecommerce.dto.UserResponseDTo;
import com.euphoria_ecommerce.model.User;
import com.euphoria_ecommerce.repository.UserRepository;
import com.euphoria_ecommerce.security.AuthenticationResponse;
import com.euphoria_ecommerce.service.AuthenticationService;
import com.euphoria_ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final ApplicationEventPublisher publisher;
    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/get")
    public UserResponseDTo getUserEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);

    }

    @GetMapping("/getentity")
    public User getUserEntityByEmail(@RequestParam String email) {
        return userService.getUserByEntityEmail(email);
    }
}

//    @PostMapping("/register")
//    public ResponseEntity<String> register(
//            @RequestBody RegisterRequest request, final HttpServletRequest httpServletRequest
//    ) {
//        User user = authenticationService.register(request);
//        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(httpServletRequest)));
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//    @GetMapping("/register/verifyEmail")
//    public String verifyEmail(@RequestParam("token") String token) {
//        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
//        if (verificationToken.getUser().isEnabled()) {
//            return "This account has already been verified, please, login";
//        }
//        String verificationResult = authenticationService.validateToken(token);
//        if (verificationResult.equalsIgnoreCase("valid")) {
//            return "Email verified successfully. Now you can login.";
//        }
//        return "Invalid verification token";
//    }
//
//    public String applicationUrl(HttpServletRequest request) {
//        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
//    }