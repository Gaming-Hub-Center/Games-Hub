package com.gameshub.controller;

import com.gameshub.controller.DTO.*;
import com.gameshub.service.*;
import com.gameshub.utils.ProductMapper;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/physical/getdetails")
    public ResponseEntity<PhysicalProductDTO> getPhysicalProductByID(@RequestParam int ID) {
        PhysicalProductDTO productDTO = productService.getPhysicalByID(ID);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/digital/getdetails")
    public ResponseEntity<DigitalProductDTO> getDigitalProductByID(@RequestParam int ID) {
        DigitalProductDTO productDTO = productService.getDigitalByID(ID);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/physical/search")
    public ResponseEntity<List<ProductBriefDTO>> searchPhysicalProducts(@RequestParam String key) {
        List<ProductBriefDTO> productDTOs = productService.searchPhysicalByTitle(key);
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("/digital/search")
    public ResponseEntity<List<ProductBriefDTO>> searchDigitalProducts(@RequestParam String key) {
        List<ProductBriefDTO> productDTOs = productService.searchDigitalByTitle(key);
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("/physical/filter")
    public ResponseEntity<List<ProductBriefDTO>> filterPhysical(@RequestParam(required = false) String category,
                                                                @RequestParam(required = false) Float lowerBound,
                                                                @RequestParam(required = false) Float upperBound) {
        if(upperBound == null) upperBound = (float) Integer.MAX_VALUE;
        if(lowerBound == null) lowerBound = (float) 0;
        return ResponseEntity.ok(productService.filterPhysical(lowerBound, upperBound, category));
    }

    @GetMapping("digital/filter")
    public ResponseEntity<List<ProductBriefDTO>> filterDigital(@RequestParam(required = false) String category,
                                                               @RequestParam(required = false) Float lowerBound,
                                                               @RequestParam(required = false) Float upperBound) {
        if(upperBound == null) upperBound = (float) Integer.MAX_VALUE;
        if(lowerBound == null) lowerBound = (float) 0;
        return ResponseEntity.ok(productService.filterDigital(lowerBound, upperBound, category));
    }

    @GetMapping("physical/sort")
    public ResponseEntity<List<ProductBriefDTO>> sortPhysical(boolean ascending) {
        return ResponseEntity.ok(productService.sortPhysical(ascending));
    }

    @GetMapping("digital/sort")
    public ResponseEntity<List<ProductBriefDTO>> sortDigital(@RequestParam boolean ascending) {
        return ResponseEntity.ok(productService.sortDigital(ascending));
    }
}
