package com.gameshub.controller.request;

import com.gameshub.service.request.approve.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductRequestApproveService productRequestApproveService;

    @PostMapping("/approve-product/create")
    public ResponseEntity<?> approveProductCreation(@RequestParam String productType, @RequestParam int requestId) {
        productRequestApproveService.approveProductCreation(productType, requestId);
        return ResponseEntity.ok("Product creation request approved successfully.");
    }

}