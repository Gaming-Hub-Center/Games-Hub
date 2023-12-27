package com.gameshub.admin;

import com.gameshub.controller.DTO.request.ProductRequestDTO;
import com.gameshub.exception.ResourceNotFoundException;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.request.DigitalProductRequestRepository;
import com.gameshub.repository.user.SellerRepository;
import com.gameshub.service.admin.AdminProductsService;
import com.sun.jdi.request.InvalidRequestStateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AdminDigitalProductServiceIntegrationTest {
    @Autowired
    private AdminProductsService adminProductsService;
    @Autowired
    private DigitalProductRequestRepository digitalProductRequestRepository;
    private DigitalProductRequestDAO testRequest;
    @Autowired
    private SellerRepository sellerRepository;

    @BeforeEach
    public void setup() {
        SellerDAO sellerDAO = new SellerDAO("name", "email", "password", "phone", "address", 12, "nationalID", LocalDate.now(), "sellerDescription", "vatRegistrationNumber");
        sellerRepository.save(sellerDAO);
        testRequest = new DigitalProductRequestDAO(LocalDate.now(), "Pending", "Game", "Test Game", 59, "Test Description", LocalDate.now(), 100, "Action", sellerRepository.findByEmail("email").orElse(null), "Code");
        digitalProductRequestRepository.save(testRequest);
    }

    @Test
    public void testApprovePhysicalProduct_Success() {
        adminProductsService.approveProductCreation("digital", testRequest.getId());
        DigitalProductRequestDAO updatedRequest = digitalProductRequestRepository.findById(testRequest.getId()).orElseThrow();
        assertEquals("Approved", updatedRequest.getStatus());
    }

    @Test
    public void testApprovePhysicalProduct_NonExistingRequest() {
        assertThrows(ResourceNotFoundException.class, () -> adminProductsService.approveProductCreation("digital", Integer.MAX_VALUE));
    }

    @Test
    public void testApprovePhysicalProduct_NonPendingStatus() {
        testRequest.setStatus("Approved");
        digitalProductRequestRepository.save(testRequest);

        assertThrows(InvalidRequestStateException.class, () -> adminProductsService.approveProductCreation("digital", testRequest.getId()));
    }

    @Test
    public void testDeclinePhysicalProduct_Success() {
        adminProductsService.declineProductCreation("digital", testRequest.getId());
        DigitalProductRequestDAO updatedRequest = digitalProductRequestRepository.findById(testRequest.getId()).orElseThrow();
        assertEquals("Declined", updatedRequest.getStatus());
    }

    @Test
    public void testDeclinePhysicalProduct_NonExistingRequest() {
        assertThrows(IllegalArgumentException.class, () -> adminProductsService.declineProductCreation("digital", Integer.MAX_VALUE));
    }

    @Test
    public void testDeclinePhysicalProduct_NonPendingStatus() {
        testRequest.setStatus("Declined");
        digitalProductRequestRepository.save(testRequest);

        assertThrows(IllegalStateException.class, () -> adminProductsService.declineProductCreation("digital", testRequest.getId()));
    }

    @Test
    public void testGetPendingPhysicalProducts() {
        List<ProductRequestDTO> requests = adminProductsService.getAdminProducts("digital", "Pending");
        assertFalse(requests.isEmpty());
        assertEquals("Pending", requests.get(0).getStatus());
    }

    @Test
    public void testInvalidProductType() {
        assertThrows(IllegalArgumentException.class, () -> adminProductsService.approveProductCreation("invalidType", testRequest.getId()));
        assertThrows(IllegalArgumentException.class, () -> adminProductsService.declineProductCreation("invalidType", testRequest.getId()));
        assertThrows(IllegalArgumentException.class, () -> adminProductsService.getAdminProducts("invalidType", "Pending"));
    }
}
