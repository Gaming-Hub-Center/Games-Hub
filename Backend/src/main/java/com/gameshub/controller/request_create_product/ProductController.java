//package com.gameshub.controller.request_create_product;
//
//import com.gameshub.controller.DTO.product.*;
//import com.gameshub.controller.DTO.request.*;
//import com.gameshub.service.product.ProductManager;
//import com.gameshub.service.request.ProductRequestManager;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//public class ProductController {
//
//    @Autowired
//    private ProductManager productManager;
//
//    @Autowired
//    private ProductRequestManager productRequestManager;
//
//    @PostMapping("/request-create-product")
//    public ResponseEntity<String> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
//        if (productRequestDTO.getProduct() instanceof PhysicalProductDTO) {
//            productManager.saveProduct(productRequestDTO.getProduct());
//            productRequestManager.saveProductRequest(productRequestDTO);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Request to create Physical product is done!");
//        } else if (productRequestDTO.getProduct() instanceof DigitalProductDTO) {
//            productManager.saveProduct(productRequestDTO.getProduct());
//            productRequestManager.saveProductRequest(productRequestDTO);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Request to create Digital product is done!");
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid product type.");
//    }
//
//}
