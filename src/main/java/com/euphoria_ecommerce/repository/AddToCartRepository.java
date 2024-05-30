package com.euphoria_ecommerce.repository;

import com.euphoria_ecommerce.model.AddToCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddToCartRepository extends JpaRepository<AddToCart, Integer> {
}
