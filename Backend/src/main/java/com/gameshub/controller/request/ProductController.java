package com.gameshub.controller.request;

import com.gameshub.controller.DTO.request.*;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.product.ProductDAO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.service.product.ProductService;
import com.gameshub.service.request.ProductRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-request")
public class ProductController {

    @Autowired
    private ProductRequestService productRequestService;

    @Autowired
    ProductService productService;

    @PostMapping("/create/digital")
    public ResponseEntity<String> createDigitalProduct(@Valid @RequestBody DigitalProductRequestDTO digitalProductRequestDTO) {
        try {
            productRequestService.saveProductRequest(digitalProductRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Request to create Digital product is done!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating Digital product");
        }
    }

    @PostMapping("/create/physical")
    public ResponseEntity<String> createPhysicalProduct(@Valid @RequestBody PhysicalProductRequestDTO physicalProductRequestDTO) {
        try {
            productRequestService.saveProductRequest(physicalProductRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Request to create Physical product is done!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating Physical product");
        }
    }

    @GetMapping("/{sellerID}/products")
    public ResponseEntity<List<ProductDAO>> getAllProductsBySellerID (@PathVariable(value = "sellerID") int sellerID){
        return new ResponseEntity<>(productService.getAllProductsBySellerID(sellerID), HttpStatus.OK);
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
    public ResponseEntity<List<PhysicalProductDAO>> getAllPendingPhysicalProductsBySellerID (@PathVariable(value = "sellerID") int sellerID){
        return new ResponseEntity<>(productRequestService.getAllPendingPhysicalProductsBySellerID(sellerID), HttpStatus.OK);
    }

    @GetMapping("/{sellerID}/products/pending/digital")
    public ResponseEntity<List<DigitalProductDAO>> getAllPendingDigitalProductsBySellerID (@PathVariable(value = "sellerID") int sellerID){
        return new ResponseEntity<>(productRequestService.getAllPendingDigitalProductsBySellerID(sellerID), HttpStatus.OK);
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
        if(productRequestService.deleteDigitalProductBySellerIdAndProductID(sellerID, productID))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{sellerID}/product/pending/physical/{productID}")
    public ResponseEntity<Void> deletePendingPhysicalProductOfSellerByProductID(@PathVariable(value = "sellerID") int sellerID, @PathVariable(value = "productID") int productID){
        if(productRequestService.deletePhysicalProductBySellerIdAndProductID(sellerID, productID))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
