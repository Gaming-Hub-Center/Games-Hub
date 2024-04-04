package com.gameshub.controller.order;

<<<<<<< Updated upstream
import com.gameshub.controller.DTO.order.*;
import com.gameshub.repository.order.*;
import com.gameshub.service.order.*;
import lombok.*;
import org.springframework.http.*;
=======
import com.gameshub.dto.order.OrderCheckoutDTO;
import com.gameshub.model.order.OrderDAO;
import com.gameshub.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
>>>>>>> Stashed changes
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("checkout/physical")
    public ResponseEntity<String> physicalCheckout(@AuthenticationPrincipal int buyerId, @RequestBody OrderCheckoutDTO orderCheckoutDTO) {
        orderService.orderPhysical(buyerId, orderCheckoutDTO.getPaymentMethod());
        return ResponseEntity.ok("Ordered Successfully!");
    }

    @PostMapping("checkout/digital")
    public ResponseEntity<String> digitalCheckout(@AuthenticationPrincipal int buyerId, @RequestBody OrderCheckoutDTO orderCheckoutDTO) {
        orderService.orderDigital(buyerId, orderCheckoutDTO.getPaymentMethod());
        return ResponseEntity.ok("Ordered Successfully!");
    }

<<<<<<< Updated upstream
    // ------------- Testing ------------- //

//    @GetMapping("orders")
//    public ResponseEntity<List<OrderDAO>> getAllOrders() {
//        return ResponseEntity.ok(orderRepository.findAll());
//    }
//
//    @GetMapping("physical/orders")
//    public ResponseEntity<List<PhysicalOrderItemDAO>> getAllPhysicalOrders() {
//        return ResponseEntity.ok(physicalOrderItemRepository.findAll());
//    }

    // ------------- Testing ------------- //
=======
    @GetMapping("orders")
    public ResponseEntity<List<OrderDAO>> getOrders(@AuthenticationPrincipal int buyerId) {
        return ResponseEntity.ok(orderService.getOrders(buyerId));
    }
>>>>>>> Stashed changes

}
