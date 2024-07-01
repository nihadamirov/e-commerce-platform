package com.euphoria_ecommerce.service;

import com.euphoria_ecommerce.dto.AddressDto;
import com.euphoria_ecommerce.dto.UserResponseDTo;
import com.euphoria_ecommerce.dto.UserUpdateDto;
import com.euphoria_ecommerce.model.Address;
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

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        AddressDto addressDto = null;

        if (user.getAddress() != null) {
            Address address = user.getAddress();
            addressDto = new AddressDto(
                    address.getRegion(),
                    address.getState(),
                    address.getCity(),
                    address.getStreet(),
                    address.getCompanyName(),
                    address.getHomeType(),
                    address.getPhone(),
                    address.getPostalCode()
            );
        }

        return new UserResponseDTo(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getPhone(),
                addressDto
        );
    }

    public UserResponseDTo updateUser(UserUpdateDto userUpdateDto, String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        user.setPhone(userUpdateDto.phone());
        user.setFullName(userUpdateDto.fullName());

        User updatedUser = userRepository.save(user);

        AddressDto addressDto = null;
        if (updatedUser.getAddress() != null) {
            Address address = updatedUser.getAddress();
            addressDto = new AddressDto(
                    address.getRegion(),
                    address.getState(),
                    address.getCity(),
                    address.getStreet(),
                    address.getCompanyName(),
                    address.getHomeType(),
                    address.getPhone(),
                    address.getPostalCode()
            );
        }

        return new UserResponseDTo(
                updatedUser.getId(),
                updatedUser.getEmail(),
                updatedUser.getFullName(),
                updatedUser.getPhone(),
                addressDto
        );
    }

    public User getUserByEntityEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
