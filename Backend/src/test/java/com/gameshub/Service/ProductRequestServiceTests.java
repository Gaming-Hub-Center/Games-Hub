package com.gameshub.Service;

import com.gameshub.controller.DTO.request.*;
import com.gameshub.model.request.*;
import com.gameshub.model.user.*;
import com.gameshub.repository.request.*;
import com.gameshub.repository.user.*;
import com.gameshub.service.request.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.time.*;
import java.util.*;

@SpringBootTest
public class ProductRequestServiceTests {

    @Autowired
    ProductRequestService productRequestService;

    @Autowired
    DigitalProductRequestRepository digitalProductRequestRepository;

    @Autowired
    PhysicalProductRequestRepository physicalProductRequestRepository;

    @Autowired
    SellerRepository sellerRepository;



    @BeforeEach
    public void setup(){
        digitalProductRequestRepository.deleteAll();
        digitalProductRequestRepository.resetAutoIncrement();
        physicalProductRequestRepository.deleteAll();
        physicalProductRequestRepository.resetAutoIncrement();
        sellerRepository.deleteAll();
        sellerRepository.resetAutoIncrement();

        sellerRepository.save(
                new SellerDAO(
                        "Marwan",
                        "m@example.com",
                        "placeholderPassword",
                        "",
                        "",
                        0.0f,
                        "",
                        LocalDate.now(),
                        "",
                        "")
        );

        Optional<SellerDAO> sellerDAO = sellerRepository.findByEmail("m@example.com");
        assert sellerDAO.isPresent();
        SellerDAO seller = sellerDAO.get();

        digitalProductRequestRepository.save(
                new DigitalProductRequestDAO(
                        LocalDate.now(),
                        "Pending",
                        "Create",
                        "sample Game",
                        10,
                        "",
                        LocalDate.now(),
                        20,
                        "Game",
                        seller,
                        "abcde"
                )
        );

        physicalProductRequestRepository.save(
                new PhysicalProductRequestDAO(
                        LocalDate.now(),
                        "Pending",
                        "Create",
                        "sample Keyboard",
                        10,
                        "",
                        LocalDate.now(),
                        20,
                        "Keyboard",
                        seller
                )
        );

        // Digital Product Requests
        for (int i = 1; i <= 4; i++) {
            digitalProductRequestRepository.save(
                    new DigitalProductRequestDAO(
                            LocalDate.now(),
                            "Pending",
                            "Update",
                            "Digital Product " + i,
                            10 + i * 5,
                            "New features for Digital Product " + i,
                            LocalDate.now(),
                            20 + i * 5,
                            "Software",
                            seller,
                            "request-id-" + i
                    )
            );
        }

        // Physical Product Requests
        for (int i = 1; i <= 4; i++) {
            physicalProductRequestRepository.save(
                    new PhysicalProductRequestDAO(
                            LocalDate.now(),
                            "Pending",
                            "Create",
                            "Physical Product " + i,
                            15 + i * 5,
                            "Description for Physical Product " + i,
                            LocalDate.now(),
                            25 + i * 5,
                            "Electronics",
                            seller
                    )
            );
        }

    }

    @AfterEach
    void finish() {
        digitalProductRequestRepository.deleteAll();
        digitalProductRequestRepository.resetAutoIncrement();
        physicalProductRequestRepository.deleteAll();
        physicalProductRequestRepository.resetAutoIncrement();
        sellerRepository.deleteAll();
        sellerRepository.resetAutoIncrement();
    }

    @Test
    public void testGetDigitalProductRequest() {
        // Digital Product Request
        DigitalProductRequestDAO originalDigitalRequest = productRequestService.getDigitalProductRequestByProductID(1);
        assert originalDigitalRequest.getTitle().equals("sample Game") && originalDigitalRequest.getDescription().isEmpty();

        DigitalProductRequestDAO nonExistingDigitalRequest = productRequestService.getDigitalProductRequestByProductID(10);
        assert nonExistingDigitalRequest.toString().equals(new DigitalProductRequestDAO().toString());
    }

    @Test
    public void testDeleteDigitalProductRequest() {
        assert !productRequestService.deleteDigitalProductRequestBySellerIdAndProductID(1, 10);
        assert productRequestService.deleteDigitalProductRequestBySellerIdAndProductID(1, 1);
    }

    @Test
    public void testEditDigitalProductRequest() {
        // Digital Product Request
        DigitalProductRequestDAO originalDigitalRequest = productRequestService.getDigitalProductRequestByProductID(1);
        assert originalDigitalRequest.getTitle().equals("sample Game") && originalDigitalRequest.getDescription().isEmpty();

        assert productRequestService.updateDigitalProductRequestByProductID(1, new ProductPatchDTO("new name", "new Description"));

        DigitalProductRequestDAO updatedDigitalProductRequest = productRequestService.getDigitalProductRequestByProductID(1);

        assert updatedDigitalProductRequest.getTitle().equals("new name") && updatedDigitalProductRequest.getDescription().equals("new Description");
        assert !originalDigitalRequest.getTitle().equals(updatedDigitalProductRequest.getTitle()) && !originalDigitalRequest.getDescription().equals(updatedDigitalProductRequest.getDescription());
    }

    @Test
    public void testGetPhysicalProductRequest() {
        // Physical Product Request
        PhysicalProductRequestDAO originalPhysicalRequest = productRequestService.getPhysicalProductRequestByProductID(1);
        assert originalPhysicalRequest.getTitle().equals("sample Keyboard") && originalPhysicalRequest.getDescription().isEmpty();

        PhysicalProductRequestDAO nonExistingPhysicalRequest = productRequestService.getPhysicalProductRequestByProductID(2);
        assert nonExistingPhysicalRequest.toString().equals(new PhysicalProductRequestDAO().toString());
    }

    @Test
    public void testDeletePhysicalProductRequest() {
        assert !productRequestService.deletePhysicalProductRequestBySellerIdAndProductID(1, 10);
        assert productRequestService.deletePhysicalProductRequestBySellerIdAndProductID(1, 1);
    }

    @Test
    public void testEditPhysicalProductRequest() {
        // Physical Product Request
        PhysicalProductRequestDAO originalPhysicalRequest = productRequestService.getPhysicalProductRequestByProductID(1);
        assert originalPhysicalRequest.getTitle().equals("sample Keyboard") && originalPhysicalRequest.getDescription().isEmpty();

        assert productRequestService.updatePhysicalProductRequestByProductID(1, new ProductPatchDTO("new name", "new Description"));

        PhysicalProductRequestDAO updatedPhysicalProductRequest = productRequestService.getPhysicalProductRequestByProductID(1);

        assert updatedPhysicalProductRequest.getTitle().equals("new name") && updatedPhysicalProductRequest.getDescription().equals("new Description");
        assert !originalPhysicalRequest.getTitle().equals(updatedPhysicalProductRequest.getTitle()) && !originalPhysicalRequest.getDescription().equals(updatedPhysicalProductRequest.getDescription());
    }

    @Test
    public void getAllPendingDigitalProductRequestsBySellerIDTest() {
        List<DigitalProductRequestDAO> digitalProductRequests = productRequestService.getAllPendingDigitalProductRequestsBySellerID(1);
        digitalProductRequests.remove(0);
        // Ensure there are four digital product requests
        assert(digitalProductRequests.size() == 4);

        for (DigitalProductRequestDAO digitalProductRequest : digitalProductRequests) {
            // Add specific assertions based on your data
            assert(digitalProductRequest.getRequestType().equals("Update"));
            assert(digitalProductRequest.getCount() > 0);
            assert(digitalProductRequest.getSeller() != null);
            // Add more assertions as needed
        }
    }

    @Test
    public void getAllPendingPhysicalProductRequestsBySellerIDTest() {
        List<PhysicalProductRequestDAO> physicalProductRequests = productRequestService.getAllPendingPhysicalProductRequestsBySellerID(1);
        physicalProductRequests.remove(0);
        // Ensure there are four physical product requests
        assert(physicalProductRequests.size() == 4);

        for (PhysicalProductRequestDAO physicalProductRequest : physicalProductRequests) {
            // Add specific assertions based on your data
            assert(physicalProductRequest.getRequestType().equals("Create"));
            assert(physicalProductRequest.getCount() > 0);
            assert(physicalProductRequest.getSeller() != null);
            // Add more assertions as needed
        }
    }

}
