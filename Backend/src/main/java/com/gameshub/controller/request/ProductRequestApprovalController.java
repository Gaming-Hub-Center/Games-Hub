package com.gameshub.controller.request;

import com.gameshub.exception.*;
import com.gameshub.service.request.approve.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductRequestApprovalController {

    private ProductRequestApproveService productRequestApproveService;

    @PostMapping("/approve/create")
    public ResponseEntity<?> approveProductCreation(@RequestParam String productType, @RequestParam int requestId) {
        try {
            productRequestApproveService.approveProductCreation(productType, requestId);
            return ResponseEntity.ok("Product creation request approved successfully.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/approve/update")
    public ResponseEntity<?> approveProductUpdate(@RequestParam String productType, @RequestParam int requestId, @RequestParam int productId) {
        try {
            productRequestApproveService.approveProductUpdate(productType, requestId, productId);
            return ResponseEntity.ok("Product update request approved successfully.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}