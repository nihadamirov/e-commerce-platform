package com.euphoria_ecommerce.controller;

import com.euphoria_ecommerce.dto.WishlistDto;
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
    public ResponseEntity<WishlistDto> getWishlistById(@PathVariable int id) {
        return new ResponseEntity<>(wishlistService.getWishlistById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<WishlistDto>> getAllWishlists() {
        return new ResponseEntity<>(wishlistService.getAllWishlist(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<WishlistDto> createWishlist(@RequestBody WishlistDto wishlist) {
        return new ResponseEntity<>(wishlistService.createWishlist(wishlist), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<WishlistDto> updateWishlist(@PathVariable int id, @RequestBody WishlistDto wishlist) {
        return new ResponseEntity<>(wishlistService.updateWishlist(id, wishlist), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String>deleteWishlist(@PathVariable int id){
        wishlistService.deleteWishlist(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
