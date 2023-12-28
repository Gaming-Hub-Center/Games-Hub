package com.gameshub.controller;

import com.gameshub.controller.DTO.DigitalProductDTO;
import com.gameshub.controller.DTO.PhysicalProductDTO;
import com.gameshub.controller.DTO.ProductBriefDTO;
import com.gameshub.model.product.PhysicalImageDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.service.product.ProductService;
import com.gameshub.utils.ProductMapper;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/physical/getall")
    public ResponseEntity<List<ProductBriefDTO>> getAllPhysical() {
        return ResponseEntity.ok(productService.getAllPhysical());
    }

    @GetMapping("/digital/getall")
    public ResponseEntity<List<ProductBriefDTO>> getAllDigital() {
        return ResponseEntity.ok(productService.getAllDigital());
    }

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

    @PostMapping("/physical/image")
    public ResponseEntity<?> savePhysicalImage(@RequestParam String url, @RequestParam int productId) {
        productService.savePhysicalImage(url, productId);
        return ResponseEntity.ok("saved");
    }

    @PostMapping("/digital/image")
    public ResponseEntity<?> saveDigitalImage(@RequestParam String url, @RequestParam int productId) {
        productService.saveDigitalImage(url, productId);
        return ResponseEntity.ok("saved");
    }

}
