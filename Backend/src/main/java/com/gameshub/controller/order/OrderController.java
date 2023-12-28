package com.gameshub.controller.order;

import com.gameshub.controller.DTO.order.*;
import com.gameshub.model.order.*;
import com.gameshub.service.order.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("checkout/physical")
    public ResponseEntity<String> physicalCheckout(@RequestBody OrderCheckoutDTO orderCheckoutDTO) {
        orderService.orderPhysical(orderCheckoutDTO.getBuyerID(), Objects.equals(orderCheckoutDTO.getPaymentMethod(), "wallet"));
        return ResponseEntity.ok("Ordered Successfully!");
    }

    @PostMapping("checkout/digital")
    public ResponseEntity<String> digitalCheckout(@RequestBody OrderCheckoutDTO orderCheckoutDTO) {
        orderService.orderDigital(orderCheckoutDTO.getBuyerID(), Objects.equals(orderCheckoutDTO.getPaymentMethod(), "wallet"));
        return ResponseEntity.ok("Ordered Successfully!");
    }

    @GetMapping("/orders/{buyerId}")
    public ResponseEntity<List<OrderDAO>> getOrders(@PathVariable("buyerId") int buyerId) {
        return ResponseEntity.ok(orderService.getOrders(buyerId));
    }

}
