package com.gameshub.requests;

import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.repository.request.DigitalProductRequestRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class DigitalProductRequestRepositoryTest {

    @Autowired
    private DigitalProductRequestRepository repository;

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
        request.setSellerId(1);
        request.setCategory("Test Category");
        request.setCode("Test Code");
        request.setId(1);
        repository.save(request);
    }

    @AfterEach
    void tearDown() {
        // Clean up database after tests
        repository.deleteAll();
    }

//    @Test
//    public void testCreateAndFindDigitalProductRequest() {
//        Boolean result = repository.existsById(1);
//        assertTrue(result);
//        assertEquals("Test Digital Product", result.get().getTitle());
//    }

    @Test
    public void testUpdateDigitalProductRequest() {
        Optional<DigitalProductRequestDAO> result = repository.findById(1);
        assertTrue(result.isPresent());
        DigitalProductRequestDAO request = result.get();
        request.setStatus("Completed");
        repository.save(request);

        Optional<DigitalProductRequestDAO> updatedResult = repository.findById(1);
        assertTrue(updatedResult.isPresent());
        assertEquals("Completed", updatedResult.get().getStatus());
    }

    @Test
    public void testDeleteDigitalProductRequest() {
        repository.deleteById(1);
        Optional<DigitalProductRequestDAO> result = repository.findById(1);
        assertTrue(result.isEmpty());
    }

//    @Test
//    public void testFindDigitalProductRequestBySomeCriteria() {
//        // If there are any custom query methods in the repository, test them here
//        // For example: repository.findByStatus("Pending");
//        // Add your custom query tests here
//    }

    // Additional tests as needed
}
