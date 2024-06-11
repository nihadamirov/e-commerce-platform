package com.euphoria_ecommerce.service;

import com.euphoria_ecommerce.dto.AddressDto;
import com.euphoria_ecommerce.dto.UserResponseDTo;
import com.euphoria_ecommerce.model.Address;
import com.euphoria_ecommerce.model.Token;
import com.euphoria_ecommerce.model.User;
import com.euphoria_ecommerce.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTo getUserByEmail(String email) {

        User user = userRepository.findByEmail(email).get();
//        for(Token token: user.getTokens()) {
//            log.info("Token..!");
//            log.info(token.getToken());
//        }
        //user.getTokens()
        AddressDto addressDto = null;


        if (user.getAddress() != null) {
            Address address = user.getAddress();
            addressDto = new AddressDto(
                    address.getRegion(),
                    address.getState(),
                    null,
                    null
                    ,null,
                    null,
                    null,
                    null
                    );
        }

        UserResponseDTo userResponseDTo = new UserResponseDTo(
                user.getId(),
                user.getEmail(),
                addressDto
        );

        return userResponseDTo;
    }


    public User getUserByEntityEmail(String email) {
        return  userRepository.findByEmail(email).get();
    }
}
