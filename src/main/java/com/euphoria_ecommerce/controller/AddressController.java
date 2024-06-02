package com.euphoria_ecommerce.controller;


import com.euphoria_ecommerce.dto.AddressDto;
import com.euphoria_ecommerce.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address/")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("add/{userId}")
    public ResponseEntity<AddressDto> addAddress(@PathVariable Long userId, @RequestBody AddressDto addressDto) {
        AddressDto createAddress = addressService.addAddress(userId, addressDto);
        return ResponseEntity.ok(createAddress);
    }

    @PostMapping("update/{userId}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable Long userId, @RequestBody AddressDto addressDto) {
        AddressDto updateAddress = addressService.updateAddress(userId, addressDto);
        return ResponseEntity.ok(updateAddress);
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<AddressDto> deleteAddress(@PathVariable Long userId) {
        addressService.deleteAddress(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("get/{userId}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable Long userId) {
        AddressDto addressDto = addressService.getAddressByUserId(userId);
        return ResponseEntity.ok(addressDto);
    }
}
