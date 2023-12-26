package com.gameshub.controller.wishlist;

import com.gameshub.controller.DTO.Wishlist.WishlistDTO;
import com.gameshub.model.wishlist.DigitalWishlistDAO;
import com.gameshub.model.wishlist.PhysicalWishlistDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gameshub.service.wishlist.wishlistService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wishlist")
public class wishlistController {

    private final wishlistService wishlistService;

    @PostMapping("/physical/add")
    public ResponseEntity<?> addPhysicalWishlistProduct(@RequestBody WishlistDTO wishlistDTO) {
        try {
            wishlistService.addPhysicalWishlistProduct(wishlistDTO.getBuyerID(), wishlistDTO.getProductID());
            return ResponseEntity.ok("Physical wishlist item added successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: No buyer or Physical_Product exits with that ID");
        }
    }

    @PostMapping("/digital/add")
    public ResponseEntity<?> addDigitalWishlistProduct(@RequestBody WishlistDTO wishlistDTO) {
        try {
            wishlistService.addDigitalWishlistProduct(wishlistDTO.getBuyerID(), wishlistDTO.getProductID());
            return ResponseEntity.ok("Digital wishlist item added successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: No buyer or Digital_Product exits with that ID");
        }
    }

    @DeleteMapping("/physical/remove")
    public ResponseEntity<?> deletePhysicalWishlistProduct(@RequestBody WishlistDTO wishlistDTO) {
        try {
            wishlistService.deletePhysicalWishlistProduct(wishlistDTO.getBuyerID(), wishlistDTO.getProductID());
            return ResponseEntity.ok("Physical wishlist item deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: No buyer or Physical_Product exits with that ID");
        }
    }

    @DeleteMapping("/digital/remove")
    public ResponseEntity<?> deleteDigitalWishlistProduct(@RequestBody WishlistDTO wishlistDTO) {
        try {
            wishlistService.deleteDigitalWishlistProduct(wishlistDTO.getBuyerID(), wishlistDTO.getProductID());
            return ResponseEntity.ok("Digital wishlist item deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: No buyer or Digital_Product exits with that ID");
        }
    }

    @GetMapping("/physical")
    public ResponseEntity<List<PhysicalWishlistDAO>> getPhysicalWishlistItems(@RequestParam int buyerId) {
        try {
            return ResponseEntity.ok(wishlistService.getPhysicalWishlistItems(buyerId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/digital")
    public ResponseEntity<List<DigitalWishlistDAO>> getDigitalWishlistItems(@RequestParam int buyerId) {
        try {
            return ResponseEntity.ok(wishlistService.getDigitalWishlistItems(buyerId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
