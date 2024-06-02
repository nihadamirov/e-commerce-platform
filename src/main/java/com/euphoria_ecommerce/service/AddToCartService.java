package com.euphoria_ecommerce.service;

import com.euphoria_ecommerce.model.AddToCart;
import com.euphoria_ecommerce.repository.AddToCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddToCartService {
    private final AddToCartRepository addToCartRepository;

    public List<AddToCart> getAllAddToCarts() {
        return addToCartRepository.findAll();
    }

    public AddToCart getAddToCartById(int id) {
        return addToCartRepository.findById(id).get();
    }

    public AddToCart createAddToCart(AddToCart addToCart) {
        return addToCartRepository.save(addToCart);
    }

    public AddToCart updateAddToCart(int id, AddToCart addToCart) {
        AddToCart foundedCart = addToCartRepository.findById(id).get();
        foundedCart.setQuantity(addToCart.getQuantity());
        foundedCart.setShipping(addToCart.getShipping());
        foundedCart.setAddedTimePrice(addToCart.getAddedTimePrice());
        foundedCart.setUser(addToCart.getUser());
        foundedCart.setProduct(addToCart.getProduct());
        return addToCartRepository.save(foundedCart);
    }
}
