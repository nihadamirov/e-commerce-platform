package com.euphoria_ecommerce.repository;

import com.euphoria_ecommerce.enums.OrderStatus;
import com.euphoria_ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
//    @Query("SELECT o FROM Order o WHERE o.orderStatus IN (:statuses)")
    List<Order> findByOrderStatus(OrderStatus orderStatus);
}
