package com.gameshub.controller.seller;

import com.gameshub.dto.product.DigitalProductRequestDTO;
import com.gameshub.dto.product.PhysicalProductRequestDTO;
import com.gameshub.dto.product.ProductPatchDTO;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.service.product.ProductRequestService;
import com.gameshub.service.product.ProductService;
import com.gameshub.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("seller")
public class SellerController {

    private final ProductRequestService productRequestService;
    private final ProductService productService;
    private final UserService userService;

    @PostMapping("request/create/digital")
    public ResponseEntity<String> createDigitalProduct(@RequestBody DigitalProductRequestDTO digitalProductRequestDTO) {
        productRequestService.saveProductRequest(digitalProductRequestDTO);
        return ResponseEntity.ok("Request to create Digital product is done!");
    }

    @PostMapping("request/create/physical")
    public ResponseEntity<String> createPhysicalProduct(@RequestBody PhysicalProductRequestDTO physicalProductRequestDTO) {
        productRequestService.saveProductRequest(physicalProductRequestDTO);
        return ResponseEntity.ok("Request to create Physical product is done!");
    }

    @GetMapping("products/physical")
    public ResponseEntity<List<PhysicalProductDAO>> getAllPhysicalProductsBySellerId(@AuthenticationPrincipal int sellerId) {
        return new ResponseEntity<>(productService.getAllPhysicalProductsBySellerId(sellerId), HttpStatus.OK);
    }

    @GetMapping("products/digital")
    public ResponseEntity<List<DigitalProductDAO>> getAllDigitalProductsBySellerId(@AuthenticationPrincipal int sellerId) {
        return new ResponseEntity<>(productService.getAllDigitalProductsBySellerId(sellerId), HttpStatus.OK);
    }

    @GetMapping("products/pending/physical")
    public ResponseEntity<List<PhysicalProductRequestDAO>> getAllPendingPhysicalProductsBySellerId(@AuthenticationPrincipal int sellerId) {
        return new ResponseEntity<>(productRequestService.getAllPendingPhysicalProductRequestsBySellerId(sellerId), HttpStatus.OK);
    }

    @GetMapping("products/pending/digital")
    public ResponseEntity<List<DigitalProductRequestDAO>> getAllPendingDigitalProductsBySellerId(@AuthenticationPrincipal int sellerId) {
        return new ResponseEntity<>(productRequestService.getAllPendingDigitalProductRequestsBySellerId(sellerId), HttpStatus.OK);
    }

    @GetMapping("product/physical/{productId}")
    public ResponseEntity<PhysicalProductDAO> getPhysicalProductByProductId(@PathVariable int productId) {
        return new ResponseEntity<>(productService.getPhysicalProductByProductId(productId), HttpStatus.OK);
    }

    @GetMapping("product/digital/{productId}")
    public ResponseEntity<DigitalProductDAO> getDigitalProductByProductId(@PathVariable int productId) {
        return new ResponseEntity<>(productService.getDigitalProductByProductId(productId), HttpStatus.OK);
    }

    @GetMapping("product/pending/physical/{productId}")
    public ResponseEntity<PhysicalProductRequestDAO> getPendingPhysicalProductByProductId(@PathVariable int productId) {
        return new ResponseEntity<>(productRequestService.getPhysicalProductRequestByProductId(productId), HttpStatus.OK);
    }

    @GetMapping("product/pending/digital/{productId}")
    public ResponseEntity<DigitalProductRequestDAO> getPendingDigitalProductByProductId(@PathVariable int productId) {
        return new ResponseEntity<>(productRequestService.getDigitalProductRequestByProductId(productId), HttpStatus.OK);
    }

    @DeleteMapping("product/digital/{productId}")
    public ResponseEntity<Void> deleteDigitalProductOfSellerByProductId(@AuthenticationPrincipal int sellerId, @PathVariable int productId) {
        if(productService.deleteDigitalProductBySellerIdAndProductId(sellerId, productId))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("product/physical/{productId}")
    public ResponseEntity<Void> deletePhysicalProductOfSellerByProductId(@AuthenticationPrincipal int sellerId, @PathVariable int productId) {
        if(productService.deletePhysicalProductBySellerIdAndProductId(sellerId, productId))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("product/pending/digital/{productId}")
    public ResponseEntity<Void> deletePendingDigitalProductOfSellerByProductId(@AuthenticationPrincipal int sellerId, @PathVariable int productId) {
        if(productRequestService.deleteDigitalProductRequestBySellerIdAndProductId(sellerId, productId))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("product/pending/physical/{productId}")
    public ResponseEntity<Void> deletePendingPhysicalProductOfSellerByProductId(@AuthenticationPrincipal int sellerId, @PathVariable int productId) {
        if(productRequestService.deletePhysicalProductRequestBySellerIdAndProductId(sellerId, productId))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("product/digital/{productId}")
    public ResponseEntity<Void> updateDigitalProductByProductId(@PathVariable int productId, @RequestBody ProductPatchDTO patch) {
        if(productService.updateDigitalProductByProductId(productId, patch))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("product/physical/{productId}")
    public ResponseEntity<Void> updatePhysicalProductByProductId(@PathVariable int productId, @RequestBody ProductPatchDTO patch) {
        if(productService.updatePhysicalProductByProductId(productId, patch))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("product/pending/digital/{productId}")
    public ResponseEntity<Void> updatePendingDigitalProductByProductId(@PathVariable int productId, @RequestBody ProductPatchDTO patch) {
        if(productRequestService.updateDigitalProductRequestByProductId(productId, patch))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PatchMapping("product/pending/physical/{productId}")
    public ResponseEntity<Void> updatePendingPhysicalProductByProductId(@PathVariable int productId, @RequestBody ProductPatchDTO patch) {
        if(productRequestService.updatePhysicalProductRequestByProductId(productId, patch))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}