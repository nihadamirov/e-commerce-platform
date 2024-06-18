package com.euphoria_ecommerce.controller;

import com.euphoria_ecommerce.dto.AddToCartDto;
import com.euphoria_ecommerce.service.AddToCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addtocart")
@RequiredArgsConstructor
public class AddToCartController {
    private final AddToCartService addToCartService;

    @GetMapping("/{id}")
    public ResponseEntity<AddToCartDto> getAddToCartById(@PathVariable int id) {
        return new ResponseEntity<>(addToCartService.getAddToCartById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<AddToCartDto>> getAllAddToCarts() {
        return new ResponseEntity<>(addToCartService.getAllAddToCarts(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<AddToCartDto> createAddToCart(@RequestBody AddToCartDto addToCart) {
        return new ResponseEntity<>(addToCartService.createAddToCart(addToCart), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddToCartDto> updateAddToCart(@PathVariable int id, @RequestBody AddToCartDto addToCart) {
        return new ResponseEntity<>(addToCartService.updateAddToCart(id, addToCart), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String>deleteAddToCart(@PathVariable int id){
        addToCartService.deleteAddToCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/subtotal/{userId}")
    public ResponseEntity<Double> getSubtotal(@PathVariable int userId) {
        return new ResponseEntity<>(addToCartService.subTotal(userId), HttpStatus.OK);
    }
    @GetMapping("/grandtotal/{userId}")
    public ResponseEntity<Double> getGrandtotal(@PathVariable int userId) {
        return new ResponseEntity<>(addToCartService.grantTotal(userId), HttpStatus.OK);
    }

}
