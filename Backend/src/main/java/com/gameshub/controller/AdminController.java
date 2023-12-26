package com.gameshub.controller;

import com.gameshub.controller.DTO.user.BuyerDTO;
import com.gameshub.controller.DTO.user.SellerDTO;
import com.gameshub.service.user.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/view/buyers")
    public ResponseEntity<List<BuyerDTO>> getAllBuyers() {
        return ResponseEntity.ok(adminService.getAllBuyers());
    }

    @GetMapping("/view/sellers")
    public ResponseEntity<List<SellerDTO>> getAllSellers() {
        return ResponseEntity.ok(adminService.getAllSellers());
    }

    @DeleteMapping("/delete/buyer/{buyerId}")
    public ResponseEntity<String> deleteBuyer(@PathVariable int buyerId) {
        adminService.deleteBuyer(buyerId);
        return ResponseEntity.ok("Buyer " + buyerId + " deleted");
    }

    @DeleteMapping("/delete/seller/{sellerId}")
    public ResponseEntity<String> deleteSeller(@PathVariable int sellerId) {
        adminService.deleteSeller(sellerId);
        return ResponseEntity.ok("Seller " + sellerId + " deleted");
    }

}
