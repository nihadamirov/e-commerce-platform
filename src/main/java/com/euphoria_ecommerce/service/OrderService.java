package com.euphoria_ecommerce.service;

import com.euphoria_ecommerce.dto.OrderDto;
import com.euphoria_ecommerce.exception.OrderNotFoundException;
import com.euphoria_ecommerce.exception.ProductNotFoundException;
import com.euphoria_ecommerce.exception.UserNotFoundException;
import com.euphoria_ecommerce.model.*;
import com.euphoria_ecommerce.repository.OrderRepository;
import com.euphoria_ecommerce.repository.ProductRepository;
import com.euphoria_ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<OrderDto> getAllOrders() {

        return orderRepository.findAll()
                .stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public OrderDto getOrderById(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id:" + id));
        return modelMapper.map(order, OrderDto.class);
    }

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = new Order();
        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id:" + orderDto.getProductId()));
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + orderDto.getUserId()));

        order.setId(order.getId());
        order.setOrderNumber(orderDto.getOrderNumber());
        order.setOrderDate(orderDto.getOrderDate());
        order.setDeliveryDate(orderDto.getDeliveryDate());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setQuantity(orderDto.getQuantity());
        order.setUser(user);
        order.setProduct(product);

        double total = 0;
        for (Price price : product.getPrice()) {
            total = orderDto.getQuantity() * price.getAmount();
            log.info("Price is: " + price.getAmount());
        }
        log.info("total is: " + total);
        order.setTotalPrice(total);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderDto.class);
    }

    public OrderDto updateOrder(int id, OrderDto orderDto) {
        Order foundedOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id:" + id));

        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id:" + orderDto.getProductId()));
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + orderDto.getUserId()));

        foundedOrder.setOrderNumber(orderDto.getOrderNumber());
        foundedOrder.setOrderDate(orderDto.getOrderDate());
        foundedOrder.setDeliveryDate(orderDto.getDeliveryDate());
        foundedOrder.setOrderStatus(orderDto.getOrderStatus());
        foundedOrder.setPaymentMethod(orderDto.getPaymentMethod());
        foundedOrder.setQuantity(orderDto.getQuantity());
        foundedOrder.setUser(user);
        foundedOrder.setProduct(product);

        double total = 0;
        for (Price price : product.getPrice()) {
            total = orderDto.getQuantity() * price.getAmount();
        }
        foundedOrder.setTotalPrice(total);

        return modelMapper.map(orderRepository.save(foundedOrder), OrderDto.class);
    }

    private OrderDto convertEntityToDto(Order order) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        OrderDto orderDto = new OrderDto();
        orderDto = modelMapper.map(order, OrderDto.class);
        return orderDto;
    }
}
