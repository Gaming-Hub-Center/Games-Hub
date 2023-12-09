package com.gameshub.requests;

import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.request.DigitalProductRequestRepository;
import com.gameshub.repository.request.PhysicalProductRequestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PhysicalProductRequestRepositoryTest {
    @Autowired
    private PhysicalProductRequestRepository repository;

    PhysicalProductRequestDAO request = new PhysicalProductRequestDAO();

    @Mock
    private SellerDAO sellerDAO;

    @BeforeEach
    void setUp() {
        request.setDateReceived(LocalDate.of(2023, 12, 1));
        request.setStatus("Pending");
        request.setRequestType("Physical");
        request.setTitle("Test Physical Product");
        request.setPrice(100);
        request.setDescription("Test Description");
        request.setPostDate(LocalDate.of(2023, 12, 1));
        request.setCount(1);
        request.setCategory("Test Category");
        request.setId(1);
        request.setSeller(sellerDAO);
        repository.save(request);
    }

//    @Test
//    public void testCreateAndFindDigitalProductRequest() {
//        Optional<PhysicalProductRequestDAO> result = repository.findById(1);
//        assertFalse(result.isPresent());
////        assertEquals("Test Physical Product", result.get().getTitle());
//    }

    @Test
    public void testUpdateDigitalProductRequest() {
        Optional<PhysicalProductRequestDAO> result = repository.findById(1);
        assertTrue(result.isPresent());
        PhysicalProductRequestDAO request = result.get();
        request.setStatus("Pending");
        repository.save(request);

        Optional<PhysicalProductRequestDAO> updatedResult = repository.findById(1);
        assertTrue(updatedResult.isPresent());
        assertEquals("Pending", updatedResult.get().getStatus());
    }

    @Test
    public void testDeleteDigitalProductRequest() {
        repository.deleteById(1);
        Optional<PhysicalProductRequestDAO> result = repository.findById(1);
        assertTrue(result.isEmpty());
    }

}
