package com.gameshub.controller.request;

import com.gameshub.controller.DTO.request.*;
import com.gameshub.service.product.ProductManager;
import com.gameshub.service.request.ProductRequestManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/request-create-product")
public class ProductController {

    @Autowired
    private ProductManager productManager;

    @Autowired
    private ProductRequestManager productRequestManager;

    @PostMapping("/digital")
    public ResponseEntity<String> createDigitalProduct(@Valid @RequestBody DigitalProductRequestDTO digitalProductRequestDTO) {
        try {
            productManager.saveProduct(digitalProductRequestDTO.getDigitalProductDTO());
            productRequestManager.saveProductRequest(digitalProductRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Request to create Digital product is done!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating Digital product");
        }
    }

    @PostMapping("/physical")
    public ResponseEntity<String> createPhysicalProduct(@Valid @RequestBody PhysicalProductRequestDTO physicalProductRequestDTO) {
        try {
            productManager.saveProduct(physicalProductRequestDTO.getPhysicalProductDTO());
            productRequestManager.saveProductRequest(physicalProductRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Request to create Physical product is done!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating Physical product");
        }
    }


}
