package com.euphoria_ecommerce.service;

import com.euphoria_ecommerce.dto.AddressDto;
import com.euphoria_ecommerce.exception.AddressNotFoundException;
import com.euphoria_ecommerce.model.Address;
import com.euphoria_ecommerce.model.User;
import com.euphoria_ecommerce.repository.AddressRepository;
import com.euphoria_ecommerce.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public AddressDto addAddress(Long userId, AddressDto addressDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Address address = new Address();

        address.setRegion(addressDto.region());
        address.setState(addressDto.state());
        address.setCity(addressDto.city());
        address.setStreet(addressDto.street());
        address.setCompanyName(addressDto.companyName());
        address.setHomeType(addressDto.homeType());
        address.setPhone(addressDto.phone());
        address.setPostalCode(addressDto.postalCode());
        address.setUser(user);

        address = addressRepository.save(address);
        user.setAddress(address);
        userRepository.save(user);

        return addressDto;
    }

    public AddressDto updateAddress(Long userId, AddressDto addressDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Address address = user.getAddress();
        if (address == null) {
            throw new AddressNotFoundException("Address not found for user");
        }
        address.setRegion(addressDto.region());
        address.setState(addressDto.state());
        address.setCity(addressDto.city());
        address.setStreet(addressDto.street());
        address.setCompanyName(addressDto.companyName());
        address.setHomeType(addressDto.homeType());
        address.setPostalCode(addressDto.postalCode());
        address.setPhone(addressDto.phone());

        address = addressRepository.save(address);

        return addressDto;
    }

    public void deleteAddress(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Address address = user.getAddress();
        if (address == null) {
            throw new AddressNotFoundException("Address not found for user");
        }
        addressRepository.delete(address);
        user.setAddress(null);
        userRepository.save(user);
    }

    public AddressDto getAddressByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Address address = user.getAddress();
        if (address == null) {
            throw new AddressNotFoundException("Address not found for user");
        }
        AddressDto addressDto = new AddressDto(
                address.getRegion(),
                address.getState(),
                address.getCity(),
                address.getStreet(),
                address.getCompanyName(),
                address.getHomeType(),
                address.getPostalCode(),
                address.getPhone()
        );
        return addressDto;
    }

}
