package com.gameshub.controller.wishlist;

import com.gameshub.dto.wishlist.WishlistDTO;
import com.gameshub.model.wishlist.DigitalWishlistDAO;
import com.gameshub.model.wishlist.PhysicalWishlistDAO;
import com.gameshub.service.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("physical/add")
    public ResponseEntity<String> addPhysicalWishlistProduct(@AuthenticationPrincipal int buyerId, @RequestBody WishlistDTO wishlistDTO) {
        wishlistService.addPhysicalWishlistProduct(buyerId, wishlistDTO.getProductId());
        return ResponseEntity.ok("Physical wishlist item added successfully");
    }

    @PostMapping("digital/add")
    public ResponseEntity<String> addDigitalWishlistProduct(@AuthenticationPrincipal int buyerId, @RequestBody WishlistDTO wishlistDTO) {
        wishlistService.addDigitalWishlistProduct(buyerId, wishlistDTO.getProductId());
        return ResponseEntity.ok("Digital wishlist item added successfully");
    }

    @DeleteMapping("physical/remove")
    public ResponseEntity<String> deletePhysicalWishlistProduct(@AuthenticationPrincipal int buyerId, @RequestBody WishlistDTO wishlistDTO) {
        wishlistService.deletePhysicalWishlistProduct(buyerId, wishlistDTO.getProductId());
        return ResponseEntity.ok("Physical wishlist item deleted successfully");
    }

    @DeleteMapping("digital/remove")
    public ResponseEntity<String> deleteDigitalWishlistProduct(@AuthenticationPrincipal int buyerId, @RequestBody WishlistDTO wishlistDTO) {
        wishlistService.deleteDigitalWishlistProduct(buyerId, wishlistDTO.getProductId());
        return ResponseEntity.ok("Digital wishlist item deleted successfully");
    }

    @GetMapping("physical")
    public ResponseEntity<List<PhysicalWishlistDAO>> getPhysicalWishlistItems(@AuthenticationPrincipal int buyerId) {
        return ResponseEntity.ok(wishlistService.getPhysicalWishlistItems(buyerId));
    }

    @GetMapping("digital")
    public ResponseEntity<List<DigitalWishlistDAO>> getDigitalWishlistItems(@AuthenticationPrincipal int buyerId) {
        return ResponseEntity.ok(wishlistService.getDigitalWishlistItems(buyerId));
    }

}
