package com.euphoria_ecommerce.service;

import com.euphoria_ecommerce.dto.LoginRequest;
import com.euphoria_ecommerce.dto.RegisterRequest;
import com.euphoria_ecommerce.exception.UserAlreadyExistsException;
import com.euphoria_ecommerce.model.Token;
import com.euphoria_ecommerce.model.TokenType;
import com.euphoria_ecommerce.model.User;
import com.euphoria_ecommerce.repository.TokenRepository;
import com.euphoria_ecommerce.repository.UserRepository;
import com.euphoria_ecommerce.security.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;


    public AuthenticationResponse register(RegisterRequest request) {
        User user = new User();
        Optional<User> user1 = userRepository.findByEmail(request.email());
        if (user1.isPresent())
            throw new UserAlreadyExistsException("User already exists with Email: " + request.email());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());
        user = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        saveUserToken(user, jwtToken);
        return new AuthenticationResponse(jwtToken);
    }

    public boolean emailExists(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    public AuthenticationResponse authenticate(LoginRequest request) {
        if (!emailExists(request.email()))
            throw new UsernameNotFoundException("User not exist");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        User user = userRepository.findByEmail(request.email()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return new AuthenticationResponse(jwtToken);
    }

//    public User register(RegisterRequest request) {
//        User user = new User();
//        Optional<User> user1 = userRepository.findByEmail(request.email());
//        if (user1.isPresent())
//            throw new UserAlreadyExistsException("User already exists with Email: " + request.email());
//        user.setEmail(request.email());
//        user.setPassword(passwordEncoder.encode(request.password()));
//        user.setRole(request.role());
//        return userRepository.save(user);
//    }

//    public void saveUserVerificationToken(User user, String token) {
//        var verificationToken = new VerificationToken(token, user);
//        verificationTokenRepository.save(verificationToken);
//    }
//
//    public String validateToken(String verificationToken) {
//        VerificationToken token = verificationTokenRepository.findByToken(verificationToken);
//        if (token == null) {
//            return "Invalid verification token";
//        }
//        User user = token.getUser();
//        Calendar calendar = Calendar.getInstance();
//        if (token.getExprationTime().getTime() - calendar.getTime().getTime() <= 0) {
//            verificationTokenRepository.delete(token);
//            return "Token already expired";
//        }
//        user.setEnabled(true);
//        userRepository.save(user);
//        return "valid";
//    }

    private void revokeAllUserTokens(User user){
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token= Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }
}
