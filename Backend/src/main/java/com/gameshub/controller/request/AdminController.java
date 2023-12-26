package com.gameshub.controller.request;

import com.gameshub.service.admin.AdminProductsService;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminProductsService productRequestApproveService;

    @PostMapping("/approve-product/create")
    public ResponseEntity<?> approveProductCreation(@RequestParam String productType, @RequestParam int requestId) {
        productRequestApproveService.approveProductCreation(productType, requestId);
        return ResponseEntity.ok("Product creation request approved successfully.");
    }

}