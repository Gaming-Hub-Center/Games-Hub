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

    @PostMapping("/physical/addOrUpdate")
    public ResponseEntity<?> addOrUpdatePhysicalCartItem(@RequestBody CartDTO cartDTO) {
        try {
            cartService.addOrUpdatePhysicalCartItem(cartDTO.getBuyerID(), cartDTO.getProductID(), cartDTO.getCount());
            return ResponseEntity.ok("Physical cart item added/updated successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: No buyer or Physical_Product exits with that ID");
        }
    }

    @PostMapping("/digital/addOrUpdate")
    public ResponseEntity<?> addOrUpdateDigitalCartItem(@RequestBody CartDTO cartDTO) {
        try {
            cartService.addOrUpdateDigitalCartItem(cartDTO.getBuyerID(), cartDTO.getProductID(), cartDTO.getCount());
            return ResponseEntity.ok("Digital cart item added/updated successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: No buyer or Digital_Product exits with that ID");
        }
    }

    @DeleteMapping("/physical/remove")
    public ResponseEntity<?> removePhysicalCartItem(@RequestParam int buyerId, @RequestParam int productId) {
        try {
            cartService.removePhysicalCartItem(buyerId, productId);
            return ResponseEntity.ok("Physical cart item removed successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: Failed to remove Cart item ");
        }
    }

    @DeleteMapping("/digital/remove")
    public ResponseEntity<?> removeDigitalCartItem(@RequestParam int buyerId, @RequestParam int productId) {
        try {
            cartService.removeDigitalCartItem(buyerId, productId);
            return ResponseEntity.ok("Digital cart item removed successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: Failed to remove Cart item ");
        }
    }

    @GetMapping("/physical")
    public ResponseEntity<List<PhysicalCartDAO>> getPhysicalCartItems(@RequestParam int buyerId) {
        try {
            return ResponseEntity.ok(cartService.getPhysicalCartItems(buyerId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/digital")
    public ResponseEntity<List<DigitalCartDAO>> getDigitalCartItems(@RequestParam int buyerId) {
        try {
            return ResponseEntity.ok(cartService.getDigitalCartItems(buyerId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }


}

