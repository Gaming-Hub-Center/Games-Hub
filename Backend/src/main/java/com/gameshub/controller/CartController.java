package com.gameshub.controller;

import com.gameshub.controller.DTO.CartDTO;
import com.gameshub.model.cart.DigitalCartDAO;
import com.gameshub.model.cart.PhysicalCartDAO;
import com.gameshub.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    // Add or update a physical cart item
    @PostMapping("/physical/addOrUpdate")
    public ResponseEntity<?> addOrUpdatePhysicalCartItem(@RequestBody CartDTO cartDTO) {
        cartService.addOrUpdatePhysicalCartItem(cartDTO.getBuyerID(), cartDTO.getProductID(), cartDTO.getCount());
        return ResponseEntity.ok("Physical cart item added/updated successfully");
    }

    // Add or update a digital cart item
    @PostMapping("/digital/addOrUpdate")
    public ResponseEntity<?> addOrUpdateDigitalCartItem(@RequestBody CartDTO cartDTO) {
        cartService.addOrUpdateDigitalCartItem(cartDTO.getBuyerID(), cartDTO.getProductID(), cartDTO.getCount());
        return ResponseEntity.ok("Digital cart item added/updated successfully");
    }

    // Remove a physical cart item
    @DeleteMapping("/physical/remove")
    public ResponseEntity<?> removePhysicalCartItem(@RequestParam int buyerId, @RequestParam int productId) {
        cartService.removePhysicalCartItem(buyerId, productId);
        return ResponseEntity.ok("Physical cart item removed successfully");
    }

    // Remove a digital cart item
    @DeleteMapping("/digital/remove")
    public ResponseEntity<?> removeDigitalCartItem(@RequestParam int buyerId, @RequestParam int productId) {
        cartService.removeDigitalCartItem(buyerId, productId);
        return ResponseEntity.ok("Digital cart item removed successfully");
    }

    // Get all physical cart items for a buyer
    @GetMapping("/physical")
    public ResponseEntity<List<PhysicalCartDAO>> getPhysicalCartItems(@RequestParam int buyerId) {
        return ResponseEntity.ok(cartService.getPhysicalCartItems(buyerId));
    }

    // Get all digital cart items for a buyer
    @GetMapping("/digital")
    public ResponseEntity<List<DigitalCartDAO>> getDigitalCartItems(@RequestParam int buyerId) {
        return ResponseEntity.ok(cartService.getDigitalCartItems(buyerId));
    }

    // Additional cart related functionalities can be implemented as needed.
}
