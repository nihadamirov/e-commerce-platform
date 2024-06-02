package com.euphoria_ecommerce.service;

import com.euphoria_ecommerce.model.Wishlist;
import com.euphoria_ecommerce.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;

    public List<Wishlist> getAllWishlist() {
        return wishlistRepository.findAll();
    }

    public Wishlist getWishlistById(int id) {
        return wishlistRepository.findById(id).get();
    }

    public Wishlist createWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    public Wishlist updateWishlist(int id, Wishlist wishlist) {
        Wishlist foundedWishlist = wishlistRepository.findById(id).get();
        foundedWishlist.setQuantity(wishlist.getQuantity());
        foundedWishlist.setProduct(wishlist.getProduct());
        foundedWishlist.setUser(wishlist.getUser());
        return wishlistRepository.save(foundedWishlist);
    }
}
