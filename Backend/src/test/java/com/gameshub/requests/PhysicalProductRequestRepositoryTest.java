package com.gameshub.requests;

import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.model.request.ProductRequestDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.request.DigitalProductRequestRepository;
import com.gameshub.repository.request.PhysicalProductRequestRepository;
import com.gameshub.repository.user.SellerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PhysicalProductRequestRepositoryTest {

    @Autowired
    private PhysicalProductRequestRepository repository;

    @Autowired
    private SellerRepository sellerRepository;

    @AfterEach
    void tearDown() {
        repository.deleteAll(); // Clear the database after each test
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
                2,
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
        repository.save(dao);

        // Retrieve the saved PhysicalProductRequestDAO from the database
        Optional<PhysicalProductRequestDAO> savedRequestOptional = repository.findById(dao.getId());
        assertTrue(savedRequestOptional.isPresent());



        PhysicalProductRequestDAO savedRequest = savedRequestOptional.get();
        assertNotNull(savedRequest);
//        assertEquals(/* Compare the attributes of savedRequest with the original request */);
    }

}
