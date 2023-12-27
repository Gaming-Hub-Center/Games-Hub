package com.gameshub.admin;

import com.gameshub.controller.DTO.request.ProductRequestDTO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.request.DigitalProductRequestRepository;
import com.gameshub.repository.request.PhysicalProductRequestRepository;
import com.gameshub.service.admin.AdminProductsService;
import com.sun.jdi.request.InvalidRequestStateException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AdminProductsServiceIntegrationTest {

    @Autowired
    private AdminProductsService adminProductsService;

    @Autowired
    private DigitalProductRequestRepository digitalProductRequestRepository;

    @Autowired
    private PhysicalProductRequestRepository physicalProductRequestRepository;

    @Test
    void testApproveDigitalProductCreation() {
        // Given a pending digital product request
        int requestId = createTestDigitalProductRequest("Pending");

        // When the approve product creation is called
        adminProductsService.approveProductCreation("digital", requestId);

        // Then the product request should be approved
        DigitalProductRequestDAO request = digitalProductRequestRepository.findById(requestId).orElseThrow();
        Assertions.assertEquals("Approved", request.getStatus());
    }

//    @Test
//    void testApprovePhysicalProductCreation() {
//        // Given a pending digital product request
//        int requestId = createTestPhysicalProductRequest("Pending");
//
//        // When the approve product creation is called
//        adminProductsService.approveProductCreation("physical", requestId);
//
//        // Then the product request should be approved
//        PhysicalProductRequestDAO request = physicalProductRequestRepository.findById(requestId).orElseThrow();
//        Assertions.assertEquals("Approved", request.getStatus());
//    }

    @Test
    void testDeclineDigitalProductCreation() {
        // Given a pending digital product request
        int requestId = createTestDigitalProductRequest("Pending");

        // When the decline product creation is called
        adminProductsService.declineProductCreation("digital", requestId);

        // Then the product request should be declined
        DigitalProductRequestDAO request = digitalProductRequestRepository.findById(requestId).orElseThrow();
        Assertions.assertEquals("Declined", request.getStatus());
    }

    // Test 1: Handling invalid product types
    @Test
    void testApproveInvalidProductType() {
        int requestId = createTestDigitalProductRequest("Pending");
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            adminProductsService.approveProductCreation("invalidType", requestId);
        });

        String expectedMessage = "Invalid product type";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    // Test 2: Fetching product requests with different statuses
    @Test
    void testGetAdminProductsDifferentStatuses() {
        createTestDigitalProductRequest("Pending");
        createTestDigitalProductRequest("Approved");
        createTestDigitalProductRequest("Declined");

        List<ProductRequestDTO> pendingRequests = adminProductsService.getAdminProducts("digital", "Pending");
        Assertions.assertFalse(pendingRequests.isEmpty());

        List<ProductRequestDTO> approvedRequests = adminProductsService.getAdminProducts("digital", "Approved");
        Assertions.assertFalse(approvedRequests.isEmpty());

        List<ProductRequestDTO> declinedRequests = adminProductsService.getAdminProducts("digital", "Declined");
        Assertions.assertFalse(declinedRequests.isEmpty());
    }

    // Test 3: Approving a product request that is not in pending status
    @Test
    void testApproveNonPendingDigitalProduct() {
        int requestId = createTestDigitalProductRequest("Approved");
        Exception exception = Assertions.assertThrows(InvalidRequestStateException.class, () -> {
            adminProductsService.approveProductCreation("digital", requestId);
        });

        String expectedMessage = "Request must be in Pending status, but was: Approved";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    // Test 4: Declining a product request that is not in pending status
    @Test
    void testDeclineNonPendingDigitalProduct() {
        int requestId = createTestDigitalProductRequest("Approved");
        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            adminProductsService.declineProductCreation("digital", requestId);
        });

        String expectedMessage = "product request is not in pending status";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }


    // Helper methods
    private int createTestDigitalProductRequest(String status) {
        DigitalProductRequestDAO request = new DigitalProductRequestDAO();
        request.setStatus(status);
        // ... set other required fields
        digitalProductRequestRepository.save(request);
        return request.getId();
    }

    private int createTestPhysicalProductRequest(String status) {
        PhysicalProductRequestDAO request = new PhysicalProductRequestDAO();
        request.setStatus(status);
        SellerDAO sellerDAO = new SellerDAO("name", "email", "Pass", "Phone", "Add", 10, "National", LocalDate.now(), "Desc", "Vat");
        request.setSeller(sellerDAO);
        // ... set other required fields
        physicalProductRequestRepository.save(request);
        return request.getId();
    }

}
