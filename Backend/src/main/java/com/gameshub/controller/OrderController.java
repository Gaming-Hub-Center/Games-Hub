package com.gameshub.controller;

import com.gameshub.controller.DTO.*;
import com.gameshub.model.order.*;
import com.gameshub.repository.order.*;
import com.gameshub.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderRepository orderRepository;
    private final PhysicalOrderItemRepository physicalOrderItemRepository;
    private final OrderService orderService;

    @PostMapping("checkout/physical")
    public ResponseEntity<String> physicalCheckout(@RequestBody OrderCheckoutDTO orderCheckoutDTO) {
        orderService.orderPhysical(orderCheckoutDTO.getBuyerID(), orderCheckoutDTO.isWallet());
        return ResponseEntity.ok("Ordered Successfully!");
    }

    @PostMapping("checkout/digital")
    public ResponseEntity<String> digitalCheckout(@RequestBody OrderCheckoutDTO orderCheckoutDTO) {
        orderService.orderDigital(orderCheckoutDTO.getBuyerID(), orderCheckoutDTO.isWallet());
        return ResponseEntity.ok("Ordered Successfully!");
    }

    // ------------- Testing ------------- //

    @GetMapping("orders")
    public ResponseEntity<List<OrderDAO>> getAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @GetMapping("physical/orders")
    public ResponseEntity<List<PhysicalOrderItemDAO>> getAllPhysicalOrders() {
        return ResponseEntity.ok(physicalOrderItemRepository.findAll());
    }

    // ------------- Testing ------------- //

}
