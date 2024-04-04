package com.gameshub.controller.cart;

import com.gameshub.dto.cart.CartDTO;
import com.gameshub.model.cart.DigitalCartDAO;
import com.gameshub.model.cart.PhysicalCartDAO;
import com.gameshub.service.cart.CartService;
import com.gameshub.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("cart")
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    @PostMapping("physical/addOrUpdate")
    public ResponseEntity<String> addOrUpdatePhysicalCartItem(@AuthenticationPrincipal int buyerId, @RequestBody CartDTO cartDTO) {
        cartService.addOrUpdatePhysicalCartItem(buyerId, cartDTO.getProductId(), cartDTO.getChangeCount());
        return ResponseEntity.ok("Physical cart item added/updated successfully");
    }

    @PostMapping("digital/addOrUpdate")
    public ResponseEntity<String> addOrUpdateDigitalCartItem(@AuthenticationPrincipal int buyerId, @RequestBody CartDTO cartDTO) {
        cartService.addOrUpdateDigitalCartItem(buyerId, cartDTO.getProductId(), cartDTO.getChangeCount());
        return ResponseEntity.ok("Digital cart item added/updated successfully");
    }

    @DeleteMapping("physical/remove/{productId}")
    public ResponseEntity<String> removePhysicalCartItem(@AuthenticationPrincipal int buyerId, @PathVariable int productId) {
        cartService.removePhysicalCartItem(buyerId, productId);
        return ResponseEntity.ok("Physical cart item removed successfully");
    }

    @DeleteMapping("digital/remove/{productId}")
    public ResponseEntity<String> removeDigitalCartItem(@AuthenticationPrincipal int buyerId, @PathVariable int productId) {
        cartService.removeDigitalCartItem(buyerId, productId);
        return ResponseEntity.ok("Digital cart item removed successfully");
    }

    @GetMapping("physical")
    public ResponseEntity<List<PhysicalCartDAO>> getPhysicalCartItems(@AuthenticationPrincipal int buyerId) {
        return ResponseEntity.ok(cartService.getPhysicalCartItems(buyerId));
    }

    @GetMapping("digital")
    public ResponseEntity<List<DigitalCartDAO>> getDigitalCartItems(@AuthenticationPrincipal int buyerId) {
        return ResponseEntity.ok(cartService.getDigitalCartItems(buyerId));
    }

}
