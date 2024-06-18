package com.euphoria_ecommerce.repository;

import com.euphoria_ecommerce.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    Optional<Rating> findByUserIdAndProductId(Long userId, Long productId);
}
