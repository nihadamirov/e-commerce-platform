package com.euphoria_ecommerce.controller;

import com.euphoria_ecommerce.model.Wishlist;
import com.euphoria_ecommerce.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;

    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> getWishlistById(@PathVariable int id) {
        return new ResponseEntity<>(wishlistService.getWishlistById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Wishlist>> getAllWishlists() {
        return new ResponseEntity<>(wishlistService.getAllWishlist(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Wishlist> createWishlist(@RequestBody Wishlist wishlist) {
        return new ResponseEntity<>(wishlistService.createWishlist(wishlist), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Wishlist> updateWishlist(@PathVariable int id, @RequestBody Wishlist wishlist) {
        return new ResponseEntity<>(wishlistService.updateWishlist(id, wishlist), HttpStatus.CREATED);
    }
}
