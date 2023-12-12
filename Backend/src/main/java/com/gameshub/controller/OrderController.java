package com.gameshub.controller;

import com.gameshub.model.order.*;
import com.gameshub.repository.order.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderRepository orderRepository;
    private final PhysicalOrderRepository physicalOrderRepository;



    // ------------- Testing ------------- //

    @GetMapping("orders")
    public ResponseEntity<List<OrderDAO>> getAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @GetMapping("physical/orders")
    public ResponseEntity<List<PhysicalOrderDAO>> getAllPhysicalOrders() {
        return ResponseEntity.ok(physicalOrderRepository.findAll());
    }

    // ------------- Testing ------------- //

}
