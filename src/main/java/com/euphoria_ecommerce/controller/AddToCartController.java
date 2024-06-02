package com.euphoria_ecommerce.controller;

import com.euphoria_ecommerce.model.AddToCart;
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
    public ResponseEntity<AddToCart> getAddToCartById(@PathVariable int id) {
        return new ResponseEntity<>(addToCartService.getAddToCartById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<AddToCart>> getAllAddToCarts() {
        return new ResponseEntity<>(addToCartService.getAllAddToCarts(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<AddToCart> createAddToCart(@RequestBody AddToCart addToCart) {
        return new ResponseEntity<>(addToCartService.createAddToCart(addToCart), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddToCart> updateAddToCart(@PathVariable int id, @RequestBody AddToCart addToCart) {
        return new ResponseEntity<>(addToCartService.updateAddToCart(id, addToCart), HttpStatus.CREATED);
    }
}
