package com.gameshub.controller.request;

import com.gameshub.service.admin.ProductRequestApproveService;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ProductRequestApproveService productRequestApproveService;

    @PostMapping("/approve-product/create")
    public ResponseEntity<?> approveProductCreation(@RequestParam String productType, @RequestParam int requestId) {
        int status = productRequestApproveService.approveProductCreation(productType, requestId);
        if (status == HttpStatus.OK.value()) {
            return ResponseEntity.ok("Product creation request approved successfully.");
        } else if (status == HttpStatus.NOT_FOUND.value()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product request not found.");
        } else if (status == HttpStatus.EXPECTATION_FAILED.value()) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Product request validation failed.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }


}