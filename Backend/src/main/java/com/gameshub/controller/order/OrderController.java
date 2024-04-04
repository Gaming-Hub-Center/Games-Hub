package com.gameshub.controller.order;

import com.gameshub.dto.order.OrderCheckoutDTO;
import com.gameshub.model.order.OrderDAO;
import com.gameshub.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("orders")
    public ResponseEntity<List<OrderDAO>> getOrders(@AuthenticationPrincipal int buyerId) {
        return ResponseEntity.ok(orderService.getOrders(buyerId));
    }

}
