package com.gameshub.controller.product;

import com.gameshub.dto.product.DigitalProductDTO;
import com.gameshub.dto.product.PhysicalProductDTO;
import com.gameshub.dto.product.ProductBriefDTO;
import com.gameshub.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("physical/getall")
    public ResponseEntity<List<ProductBriefDTO>> getAllPhysical() {
        return ResponseEntity.ok(productService.getAllPhysical());
    }

    @GetMapping("digital/getall")
    public ResponseEntity<List<ProductBriefDTO>> getAllDigital() {
        return ResponseEntity.ok(productService.getAllDigital());
    }

    @GetMapping("physical/getdetails/{productId}")
    public ResponseEntity<PhysicalProductDTO> getPhysicalProductById(@PathVariable int productId) {
        PhysicalProductDTO productDTO = productService.getPhysicalById(productId);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("digital/getdetails/{productId}")
    public ResponseEntity<DigitalProductDTO> getDigitalProductById(@PathVariable int productId) {
        DigitalProductDTO productDTO = productService.getDigitalById(productId);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("physical/search/{key}")
    public ResponseEntity<List<ProductBriefDTO>> searchPhysicalProducts(@PathVariable String key) {
        List<ProductBriefDTO> productDTOs = productService.searchPhysicalByTitle(key);
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("digital/search/{key}")
    public ResponseEntity<List<ProductBriefDTO>> searchDigitalProducts(@PathVariable String key) {
        List<ProductBriefDTO> productDTOs = productService.searchDigitalByTitle(key);
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("physical/filter")
    public ResponseEntity<List<ProductBriefDTO>> filterPhysical(@RequestParam(required = false) String category,
                                                                @RequestParam(required = false) Float lowerBound,
                                                                @RequestParam(required = false) Float upperBound) {
        if(upperBound == null)
            upperBound = (float) Integer.MAX_VALUE;
        if(lowerBound == null)
            lowerBound = (float) 0;

        return ResponseEntity.ok(productService.filterPhysical(lowerBound, upperBound, category));
    }

    @GetMapping("digital/filter")
    public ResponseEntity<List<ProductBriefDTO>> filterDigital(@RequestParam(required = false) String category,
                                                               @RequestParam(required = false) Float lowerBound,
                                                               @RequestParam(required = false) Float upperBound) {
        if(upperBound == null)
            upperBound = (float) Integer.MAX_VALUE;
        if(lowerBound == null)
            lowerBound = (float) 0;

        return ResponseEntity.ok(productService.filterDigital(lowerBound, upperBound, category));
    }

    @GetMapping("physical/sort")
    public ResponseEntity<List<ProductBriefDTO>> sortPhysical(@RequestParam boolean ascending) {
        return ResponseEntity.ok(productService.sortPhysical(ascending));
    }

    @GetMapping("digital/sort")
    public ResponseEntity<List<ProductBriefDTO>> sortDigital(@RequestParam boolean ascending) {
        return ResponseEntity.ok(productService.sortDigital(ascending));
    }

}
