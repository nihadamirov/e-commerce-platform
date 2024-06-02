package com.euphoria_ecommerce.service;

import com.euphoria_ecommerce.model.Order;
import com.euphoria_ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(int id) {
        return orderRepository.findById(id).get();
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(int id, Order order) {
        Order foundedOrder = orderRepository.findById(id).get();
        foundedOrder.setOrderNumber(order.getOrderNumber());
        foundedOrder.setOrderDate(order.getOrderDate());
        foundedOrder.setDeliveryDate(order.getDeliveryDate());
        foundedOrder.setTotalPrice(order.getTotalPrice());
        foundedOrder.setOrderStatus(order.getOrderStatus());
        foundedOrder.setPaymentMethod(order.getPaymentMethod());
        foundedOrder.setUser(order.getUser());
        foundedOrder.setProduct(order.getProduct());

        return orderRepository.save(foundedOrder);
    }
}
