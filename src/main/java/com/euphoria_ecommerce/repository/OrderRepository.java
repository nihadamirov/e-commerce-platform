package com.euphoria_ecommerce.repository;

import com.euphoria_ecommerce.enums.OrderStatus;
import com.euphoria_ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

}
