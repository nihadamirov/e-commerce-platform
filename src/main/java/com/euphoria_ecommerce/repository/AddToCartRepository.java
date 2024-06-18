package com.euphoria_ecommerce.repository;

import com.euphoria_ecommerce.model.AddToCart;
import com.euphoria_ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddToCartRepository extends JpaRepository<AddToCart, Integer> {
    List<AddToCart> findByUserId(Long userId);
}
