package com.gameshub.requests.approve;

import com.gameshub.controller.AdminController;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.repository.request.PhysicalProductRequestRepository;
import com.gameshub.service.admin.AdminProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class AdminControllerTest {

    @Autowired
    private AdminProductsService productRequestApproveService;

    @Autowired
    private AdminController adminController;

    @Autowired
    private PhysicalProductRequestRepository physicalProductRequestRepository;

    @BeforeEach
    void setUp() {
        // Insert test data into the embedded database
        PhysicalProductRequestDAO request = new PhysicalProductRequestDAO();
        request.setTitle("Test Product");
        request.setDescription("Test Description");

        physicalProductRequestRepository.save(request);
    }

    // Your other tests
    // ...

//    @Test
//    void testApproveProductCreation_Success() {
//        // Arrange
//        int requestId = 1; // Use the ID of the inserted test data
//        String productType = "Physical";
//
//        // Act
//        ResponseEntity<?> response = adminController.approveProductCreation(productType, requestId);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Product creation request approved successfully.", response.getBody());
//    }
}
