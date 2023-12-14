package com.gameshub.controller.request;

import com.gameshub.controller.DTO.request.*;
import com.gameshub.exception.*;
import com.gameshub.service.request.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product-request")
public class ProductRequestController {

    private final ProductRequestService productRequestService;

    @PostMapping("/create/digital")
    public ResponseEntity<String> createDigitalProduct(@Valid @RequestBody DigitalProductRequestDTO digitalProductRequestDTO) {
        try {
            productRequestService.saveProductRequest(digitalProductRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Request to create Digital product is done!"); // Seller Not Found
        } catch (ResourceAlreadyFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating Digital product");
        }
    }

    @PostMapping("/create/physical")
    public ResponseEntity<String> createPhysicalProduct(@Valid @RequestBody PhysicalProductRequestDTO physicalProductRequestDTO) {
        try {
            productRequestService.saveProductRequest(physicalProductRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Request to create Physical product is done!");
        } catch (ResourceAlreadyFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating Physical product");
        }
    }

}
