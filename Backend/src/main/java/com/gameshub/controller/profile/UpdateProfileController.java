package com.gameshub.controller.profile;

import com.gameshub.controller.DTO.user.*;
import com.gameshub.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profile_update")
public class UpdateProfileController {
    private final UserService userService;

    @PostMapping("/buyer")
    public ResponseEntity<?> updateBuyerProfile(@RequestBody BuyerDTO updateBuyerDTO) {
        try {
            userService.updateBuyer(updateBuyerDTO.getId(),updateBuyerDTO.getName(),updateBuyerDTO.getEmail(),updateBuyerDTO.getPhone(), updateBuyerDTO.getAddress(), updateBuyerDTO.getBalance());
            return ResponseEntity.ok().body("Buyer profile updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating buyer profile: " + e.getMessage());
        }
    }
    @PostMapping("/seller")
    public ResponseEntity<?> updateSellerProfile(@RequestBody SellerDTO updateSellerDTO) {
        try {
            userService.updateSeller(updateSellerDTO.getId(),updateSellerDTO.getName(),updateSellerDTO.getEmail(),updateSellerDTO.getPhone(), updateSellerDTO.getAddress(), updateSellerDTO.getSellerDescription());
            return ResponseEntity.ok().body("Seller profile updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating Seller profile: " + e.getMessage());
        }
    }
}
