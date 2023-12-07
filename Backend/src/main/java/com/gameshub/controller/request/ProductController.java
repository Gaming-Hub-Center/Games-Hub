package com.gameshub.controller.request;

import com.gameshub.controller.DTO.request.*;
import com.gameshub.service.product.ProductService;
import com.gameshub.service.request.ProductRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/request-create-product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRequestService productRequestService;

    @PostMapping("/digital")
    public ResponseEntity<String> createDigitalProduct(@Valid @RequestBody DigitalProductRequestDTO digitalProductRequestDTO) {
        try {
            productService.saveProduct(digitalProductRequestDTO.getDigitalProductDTO());
            productRequestService.saveProductRequest(digitalProductRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Request to create Digital product is done!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating Digital product");
        }
    }

    @PostMapping("/physical")
    public ResponseEntity<String> createPhysicalProduct(@Valid @RequestBody PhysicalProductRequestDTO physicalProductRequestDTO) {
        try {
            productService.saveProduct(physicalProductRequestDTO.getPhysicalProductDTO());
            productRequestService.saveProductRequest(physicalProductRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Request to create Physical product is done!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating Physical product");
        }
    }


}
