package com.gameshub.controller;

import com.gameshub.controller.DTO.*;
import com.gameshub.model.product.*;
import com.gameshub.service.*;
import com.gameshub.utils.ProductMapper;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/getdetails")
    public ResponseEntity<PhysicalProductDTO> getProductByID(@RequestParam int ID) {
        System.out.println("lskdflkjhsldfkjhgldakjhslkjhsdlkghlsdkjfhglksdhlfkjhgsdtiiuhltkjhlkjslfkdj");
        PhysicalProductDAO productDAO = productService.getByID(ID);
        PhysicalProductDTO productDTO = productMapper.toPhysicalProductDTO(productDAO);
        return ResponseEntity.ok(productDTO);
    }

}
