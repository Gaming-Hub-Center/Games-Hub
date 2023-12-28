package com.gameshub.controller.request;

import com.gameshub.controller.DTO.request.*;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.service.request.ProductRequestService;
import com.gameshub.service.product.ProductService;
import com.gameshub.service.user.UserService;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seller")
public class SellerController {

    private final ProductRequestService productRequestService;
    private final ProductService productService;
    private final UserService userService;

    @PostMapping("/request/create/digital")
    public ResponseEntity<String> createDigitalProduct(@Valid @RequestBody DigitalProductRequestDTO digitalProductRequestDTO) {
        productRequestService.saveProductRequest(digitalProductRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Request to create Digital product is done!"); // Seller Not Found
    }

    @PostMapping("/request/create/physical")
    public ResponseEntity<String> createPhysicalProduct(@Valid @RequestBody PhysicalProductRequestDTO physicalProductRequestDTO) {
        System.out.println("Ahooooooooooooo");
        productRequestService.saveProductRequest(physicalProductRequestDTO);
        System.out.println("Ahooooooooooooo");
        return ResponseEntity.status(HttpStatus.CREATED).body("Request to create Physical product is done!");
    }

    @GetMapping("/{sellerID}/products/physical")
    public ResponseEntity<List<PhysicalProductDAO>> getAllPhysicalProductsBySellerID (@PathVariable(value = "sellerID") int sellerID){
        return new ResponseEntity<>(productService.getAllPhysicalProductsBySellerID(sellerID), HttpStatus.OK);
    }

    @GetMapping("/{sellerID}/products/digital")
    public ResponseEntity<List<DigitalProductDAO>> getAllDigitalProductsBySellerID (@PathVariable(value = "sellerID") int sellerID){
        return new ResponseEntity<>(productService.getAllDigitalProductsBySellerID(sellerID), HttpStatus.OK);
    }

    @GetMapping("/{sellerID}/products/pending/physical")
    public ResponseEntity<List<PhysicalProductRequestDAO>> getAllPendingPhysicalProductsBySellerID (@PathVariable(value = "sellerID") int sellerID){
        return new ResponseEntity<>(productRequestService.getAllPendingPhysicalProductRequestsBySellerID(sellerID), HttpStatus.OK);
    }

    @GetMapping("/{sellerID}/products/pending/digital")
    public ResponseEntity<List<DigitalProductRequestDAO>> getAllPendingDigitalProductsBySellerID (@PathVariable(value = "sellerID") int sellerID){
        return new ResponseEntity<>(productRequestService.getAllPendingDigitalProductRequestsBySellerID(sellerID), HttpStatus.OK);
    }

    @GetMapping("/{sellerID}/product/physical/{productID}")
    public ResponseEntity<PhysicalProductDAO> getPhysicalProductBySellerIDAndProductID (@PathVariable(value = "sellerID") int sellerID, @PathVariable(value = "productID") int productID){
        return new ResponseEntity<>(productService.getPhysicalProductByProductID(productID), HttpStatus.OK);
    }

    @GetMapping("/{sellerID}/product/digital/{productID}")
    public ResponseEntity<DigitalProductDAO> getDigitalProductBySellerIDAndProductID (@PathVariable(value = "sellerID") int sellerID, @PathVariable(value = "productID") int productID){
        return new ResponseEntity<>(productService.getDigitalProductByProductID(productID), HttpStatus.OK);
    }

    @GetMapping("/{sellerID}/product/pending/physical/{productID}")
    public ResponseEntity<PhysicalProductRequestDAO> getPendingPhysicalProductBySellerIDAndProductID (@PathVariable(value = "sellerID") int sellerID, @PathVariable(value = "productID") int productID){
        return new ResponseEntity<>(productRequestService.getPhysicalProductRequestByProductID(productID), HttpStatus.OK);
    }

    @GetMapping("/{sellerID}/product/pending/digital/{productID}")
    public ResponseEntity<DigitalProductRequestDAO> getPendingDigitalProductBySellerIDAndProductID (@PathVariable(value = "sellerID") int sellerID, @PathVariable(value = "productID") int productID){
        return new ResponseEntity<>(productRequestService.getDigitalProductRequestByProductID(productID), HttpStatus.OK);
    }

    @DeleteMapping("/{sellerID}/product/digital/{productID}")
    public ResponseEntity<Void> deleteDigitalProductOfSellerByProductID(@PathVariable(value = "sellerID") int sellerID, @PathVariable(value = "productID") int productID){
        if(productService.deleteDigitalProductBySellerIdAndProductID(sellerID, productID))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{sellerID}/product/physical/{productID}")
    public ResponseEntity<Void> deletePhysicalProductOfSellerByProductID(@PathVariable(value = "sellerID") int sellerID, @PathVariable(value = "productID") int productID){
        if(productService.deletePhysicalProductBySellerIdAndProductID(sellerID, productID))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{sellerID}/product/pending/digital/{productID}")
    public ResponseEntity<Void> deletePendingDigitalProductOfSellerByProductID(@PathVariable(value = "sellerID") int sellerID, @PathVariable(value = "productID") int productID){
        if(productRequestService.deleteDigitalProductRequestBySellerIdAndProductID(sellerID, productID))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{sellerID}/product/pending/physical/{productID}")
    public ResponseEntity<Void> deletePendingPhysicalProductOfSellerByProductID(@PathVariable(value = "sellerID") int sellerID, @PathVariable(value = "productID") int productID){
        if(productRequestService.deletePhysicalProductRequestBySellerIdAndProductID(sellerID, productID))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{sellerID}/product/digital/{productID}")
    public ResponseEntity<Void> updateDigitalProductOfSellerByProductID(@PathVariable(value = "productID") int productID, @RequestBody ProductPatchDTO patch){
        if(productService.updateDigitalProductByProductID(productID, patch))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{sellerID}/product/physical/{productID}")
    public ResponseEntity<Void> updatePhysicalProductOfSellerByProductID(@PathVariable(value = "productID") int productID, @RequestBody ProductPatchDTO patch){
        if(productService.updatePhysicalProductByProductID(productID, patch))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{sellerID}/product/pending/digital/{productID}")
    public ResponseEntity<Void> updatePendingDigitalProductOfSellerByProductID(@PathVariable(value = "productID") int productID, @RequestBody ProductPatchDTO patch){
        if(productRequestService.updateDigitalProductRequestByProductID(productID, patch))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{sellerID}/product/pending/physical/{productID}")
    public ResponseEntity<Void> updatePendingPhysicalProductOfSellerByProductID(@PathVariable(value = "productID") int productID, @RequestBody ProductPatchDTO patch){
        if(productRequestService.updatePhysicalProductRequestByProductID(productID, patch))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/name")
    public ResponseEntity<String> getSellerName(@RequestParam int id) {
        System.out.println(id);
        return ResponseEntity.ok(userService.getSellerById(id).getName());
    }

}