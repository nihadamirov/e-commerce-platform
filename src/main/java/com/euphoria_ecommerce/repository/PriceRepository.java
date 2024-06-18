package com.euphoria_ecommerce.repository;

import com.euphoria_ecommerce.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
