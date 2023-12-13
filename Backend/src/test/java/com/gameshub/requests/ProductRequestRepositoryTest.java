package com.gameshub.requests;

import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.request.DigitalProductRequestRepository;
import com.gameshub.repository.request.PhysicalProductRequestRepository;
import com.gameshub.repository.user.SellerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProductRequestRepositoryTest {

    @Autowired
    private PhysicalProductRequestRepository physicalProductRequestRepository;

    @Autowired
    private DigitalProductRequestRepository digitalProductRequestRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @AfterEach
    void tearDown() {
        physicalProductRequestRepository.deleteAll(); // Clear the database after each test
        digitalProductRequestRepository.deleteAll();
    }

    @Test
    void testSavePhysicalProductRequest() {
        SellerDAO sellerDAO = new SellerDAO(
                "Seller",
                "Seller@Gmail.com",
                "Pass",
                "123456",
                "Address",
                102,
                "ID",
                LocalDate.now(),
                "Description",
                "VAT"
        );

        // Save the SellerDAO to the database
        sellerRepository.save(sellerDAO);

        // Create a PhysicalProductRequestDAO
        PhysicalProductRequestDAO dao = new PhysicalProductRequestDAO(
                LocalDate.now(),
                "Status",
                "Type",
                "Title",
                120,
                "Description",
                LocalDate.now(),
                124,
                "CAT",
                sellerDAO
        );
        dao.setSeller(sellerDAO);
        // Set other attributes

        // Save the PhysicalProductRequestDAO to the database
        physicalProductRequestRepository.save(dao);

        // Retrieve the saved PhysicalProductRequestDAO from the database
        Optional<PhysicalProductRequestDAO> savedRequestOptional = physicalProductRequestRepository.findById(dao.getId());
        assertTrue(savedRequestOptional.isPresent());

        PhysicalProductRequestDAO savedRequest = savedRequestOptional.get();
        assertNotNull(savedRequest);
        assertEquals(savedRequest.getRequestType(), dao.getRequestType());
        assertEquals(savedRequest.getCategory(), dao.getCategory());
        assertEquals(savedRequest.getStatus(), dao.getStatus());
        assertEquals(savedRequest.getCount(), dao.getCount());
        assertEquals(savedRequest.getTitle(), dao.getTitle());
        assertEquals(savedRequest.getDescription(), dao.getDescription());
        assertEquals(savedRequest.getId(), dao.getId());
        assertEquals(savedRequest.getSeller(), dao.getSeller());
        assertEquals(savedRequest.getPostDate(), dao.getPostDate());
        assertEquals(savedRequest.getDateReceived(), dao.getDateReceived());

    }

    @Test
    void testSaveDigitalProductRequest() {
        SellerDAO sellerDAO = new SellerDAO(
                "Seller",
                "Seller@Gmail.com",
                "Pass",
                "123456",
                "Address",
                102,
                "ID",
                LocalDate.now(),
                "Description",
                "VAT"
        );

        // Save the SellerDAO to the database
        sellerRepository.save(sellerDAO);

        // Create a PhysicalProductRequestDAO
        DigitalProductRequestDAO dao = new DigitalProductRequestDAO(
                LocalDate.now(),
                "Status",
                "Type",
                "Title",
                120,
                "Description",
                LocalDate.now(),
                124,
                "CAT",
                sellerDAO,
                "Code"
        );
        dao.setSeller(sellerDAO);
        // Set other attributes

        // Save the PhysicalProductRequestDAO to the database
        digitalProductRequestRepository.save(dao);

        // Retrieve the saved PhysicalProductRequestDAO from the database
        Optional<DigitalProductRequestDAO> savedRequestOptional = digitalProductRequestRepository.findById(dao.getId());
        assertTrue(savedRequestOptional.isPresent());

        DigitalProductRequestDAO savedRequest = savedRequestOptional.get();
        assertNotNull(savedRequest);
        assertEquals(savedRequest.getRequestType(), dao.getRequestType());
        assertEquals(savedRequest.getCategory(), dao.getCategory());
        assertEquals(savedRequest.getStatus(), dao.getStatus());
        assertEquals(savedRequest.getCount(), dao.getCount());
        assertEquals(savedRequest.getTitle(), dao.getTitle());
        assertEquals(savedRequest.getDescription(), dao.getDescription());
        assertEquals(savedRequest.getId(), dao.getId());
        assertEquals(savedRequest.getSeller(), dao.getSeller());
        assertEquals(savedRequest.getPostDate(), dao.getPostDate());
        assertEquals(savedRequest.getDateReceived(), dao.getDateReceived());

    }

}
