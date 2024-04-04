package com.gameshub.controller.profile;

import com.gameshub.dto.user.BuyerDTO;
import com.gameshub.dto.user.SellerDTO;
import com.gameshub.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("profile_update")
public class UpdateProfileController {

    private final UserService userService;

    @PostMapping("buyer")
    public ResponseEntity<?> updateBuyerProfile(@AuthenticationPrincipal int buyerId, @RequestBody BuyerDTO updateBuyerDTO) {
        userService.updateBuyer(buyerId,updateBuyerDTO.getName(), updateBuyerDTO.getEmail(), updateBuyerDTO.getPhone(), updateBuyerDTO.getAddress(), updateBuyerDTO.getBalance());
        return ResponseEntity.ok().body("Buyer profile updated successfully");
    }

    @PostMapping("seller")
    public ResponseEntity<?> updateSellerProfile(@AuthenticationPrincipal int sellerId, @RequestBody SellerDTO updateSellerDTO) {
        userService.updateSeller(sellerId, updateSellerDTO.getName(), updateSellerDTO.getEmail(), updateSellerDTO.getPhone(), updateSellerDTO.getAddress(), updateSellerDTO.getSellerDescription());
        return ResponseEntity.ok().body("Seller profile updated successfully");
    }

}
