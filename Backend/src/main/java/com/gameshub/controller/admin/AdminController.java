package com.gameshub.controller.admin;

import com.gameshub.dto.product.ProductBriefDTO;
import com.gameshub.dto.product.ProductRequestDTO;
import com.gameshub.dto.user.AdminDTO;
import com.gameshub.dto.user.BuyerDTO;
import com.gameshub.dto.user.SellerDTO;
import com.gameshub.enums.ProductStatus;
import com.gameshub.service.admin.AdminProductsService;
import com.gameshub.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("admin")
public class AdminController {

    private final AdminService adminService;
    private final AdminProductsService adminProductsService;

    @GetMapping("products")
    public ResponseEntity<List<ProductRequestDTO>> getAllProducts(@RequestParam String productType, @RequestParam String status) {
        List<ProductRequestDTO> productsRequest = adminProductsService.getAdminProducts(productType, status);
        return ResponseEntity.ok(productsRequest);
    }

    @GetMapping("approve")
    public ResponseEntity<List<ProductRequestDTO>> approveProductCreation(@RequestParam String productType, @RequestParam int requestId) {
        adminProductsService.approveProductCreation(productType, requestId);
        List<ProductRequestDTO> productsRequest = adminProductsService.getAdminProducts(productType, ProductStatus.APPROVED.name());
        return ResponseEntity.ok(productsRequest);
    }

    @GetMapping("decline")
    public ResponseEntity<List<ProductRequestDTO>> declineProductCreation(@RequestParam String productType, @RequestParam int requestId) {
        adminProductsService.declineProductCreation(productType, requestId);
        List<ProductRequestDTO> productsRequest = adminProductsService.getAdminProducts(productType, ProductStatus.PENDING.name());
        return ResponseEntity.ok(productsRequest);
    }

    @GetMapping("view/buyers")
    public ResponseEntity<List<BuyerDTO>> getAllBuyers() {
        return ResponseEntity.ok(adminService.getAllBuyers());
    }

    @GetMapping("view/sellers")
    public ResponseEntity<List<SellerDTO>> getAllSellers() {
        return ResponseEntity.ok(adminService.getAllSellers());
    }

    @GetMapping("view/admins")
    public ResponseEntity<List<AdminDTO>> getAllAdmins(@AuthenticationPrincipal int adminId) {
        return ResponseEntity.ok(adminService.getAllAdmins(adminId));
    }

    @DeleteMapping("delete/buyer/{buyerId}")
    public ResponseEntity<String> deleteBuyer(@PathVariable int buyerId) {
        adminService.deleteBuyer(buyerId);
        return ResponseEntity.ok("Buyer " + buyerId + " deleted");
    }

    @DeleteMapping("delete/seller/{sellerId}")
    public ResponseEntity<String> deleteSeller(@PathVariable int sellerId) {
        adminService.deleteSeller(sellerId);
        return ResponseEntity.ok("Seller " + sellerId + " deleted");
    }

    @DeleteMapping("delete/admin/{adminId}")
    public ResponseEntity<String> deleteAdmin(@PathVariable int adminId) {
        adminService.deleteAdmin(adminId);
        return ResponseEntity.ok("Admin " + adminId + " deleted");
    }

    @DeleteMapping("delete/physical/product/{productId}")
    public ResponseEntity<String> deletePhysicalProduct(@PathVariable int productId) {
        adminService.deletePhysicalProduct(productId);
        return ResponseEntity.ok("Physical Product " + productId + " deleted");
    }

    @DeleteMapping("delete/digital/product/{productId}")
    public ResponseEntity<String> deleteDigitalProduct(@PathVariable int productId) {
        adminService.deleteDigitalProduct(productId);
        return ResponseEntity.ok("Digital Product " + productId + " deleted");
    }

    @GetMapping("view/physical/products/{sellerId}")
    public ResponseEntity<List<ProductBriefDTO>> getSellerPhysicalProducts(@PathVariable int sellerId) {
        return ResponseEntity.ok(adminService.getSellerPhysicalProducts(sellerId));
    }

    @GetMapping("view/digital/products/{sellerId}")
    public ResponseEntity<List<ProductBriefDTO>> getSellerDigitalProducts(@PathVariable int sellerId) {
        return ResponseEntity.ok(adminService.getSellerDigitalProducts(sellerId));
    }

}
