package com.euphoria_ecommerce.repository;

import com.euphoria_ecommerce.model.RecentlyViewedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecentlyViewedProductsRepository extends JpaRepository<RecentlyViewedProducts,Integer> {
}
