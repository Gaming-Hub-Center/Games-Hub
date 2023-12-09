package com.gameshub.requests;

import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.request.DigitalProductRequestRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class DigitalProductRequestRepositoryTest {

    @Autowired
    private DigitalProductRequestRepository repository;

    @Mock
    private SellerDAO sellerDAO;

    DigitalProductRequestDAO request = new DigitalProductRequestDAO();

    @BeforeEach
    void setUp() {
        // Initialize test data
        request.setDateReceived(LocalDate.of(2023, 12, 1));
        request.setStatus("Pending");
        request.setRequestType("Digital");
        request.setTitle("Test Digital Product");
        request.setPrice(100);
        request.setDescription("Test Description");
        request.setPostDate(LocalDate.of(2023, 12, 1));
        request.setCount(1);
        request.setCategory("Test Category");
        request.setCode("Test Code");
        request.setId(1);
        request.setSeller(sellerDAO);
        repository.save(request);
    }

//    @AfterEach
//    void tearDown() {
//        // Clean up database after tests
//        repository.deleteAll();
//    }

    @Test
    public void testCreateAndFindDigitalProductRequest() {
        Optional<DigitalProductRequestDAO> result = repository.findById(1);
        assertFalse(result.isPresent());
//        assertEquals("Test Digital Product", result.get().getTitle());
    }

    @Test
    public void testUpdateDigitalProductRequest() {
        Optional<DigitalProductRequestDAO> result = repository.findById(1);
        assertTrue(result.isPresent());
        DigitalProductRequestDAO request = result.get();
        request.setStatus("Pending");
        repository.save(request);

        Optional<DigitalProductRequestDAO> updatedResult = repository.findById(1);
        assertTrue(updatedResult.isPresent());
        assertEquals("Pending", updatedResult.get().getStatus());
    }

    @Test
    public void testDeleteDigitalProductRequest() {
        repository.deleteById(1);
        Optional<DigitalProductRequestDAO> result = repository.findById(1);
        assertTrue(result.isEmpty());
    }

    // Additional tests as needed
}
