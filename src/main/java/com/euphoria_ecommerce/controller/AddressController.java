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

    @PostMapping("add/{email}")
    public ResponseEntity<AddressDto> addAddress(@PathVariable String email, @RequestBody AddressDto addressDto) {
        AddressDto createAddress = addressService.addAddress(email, addressDto);
        return ResponseEntity.ok(createAddress);
    }

    @PostMapping("update/{email}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable String email, @RequestBody AddressDto addressDto) {
        AddressDto updateAddress = addressService.updateAddress(email, addressDto);
        return ResponseEntity.ok(updateAddress);
    }

    @DeleteMapping("delete/{email}")
    public ResponseEntity<AddressDto> deleteAddress(@PathVariable String email) {
        addressService.deleteAddress(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("get/{email}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable String email) {
        AddressDto addressDto = addressService.getAddressByUserId(email);
        return ResponseEntity.ok(addressDto);
    }
}
