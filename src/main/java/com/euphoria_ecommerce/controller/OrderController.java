package com.euphoria_ecommerce.controller;

import com.euphoria_ecommerce.dto.OrderDto;
import com.euphoria_ecommerce.enums.OrderStatus;
import com.euphoria_ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable int id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }
    @GetMapping("/orderstatus{orderStatus}")
    public ResponseEntity<List<OrderDto>> getOrdersByStatus(@PathVariable OrderStatus orderStatus) {
        return new ResponseEntity<>(orderService.getOrdersByStatus(orderStatus), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto order) {
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable int id, @RequestBody OrderDto order) {
        return new ResponseEntity<>(orderService.updateOrder(id, order), HttpStatus.CREATED);
    }
}
