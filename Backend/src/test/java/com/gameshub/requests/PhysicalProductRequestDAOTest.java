package com.gameshub.requests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.repository.request.PhysicalProductRequestRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class PhysicalProductRequestDAOTest {

    @Mock
    PhysicalProductRequestRepository physicalProductRequestRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        physicalProductRequestRepository.deleteAll();
        PhysicalProductDAO physicalProductDAO = new PhysicalProductDAO();
    }

    @Test
    public void testPhysicalProductRequestDAO() {
        // Create a PhysicalProductDAO object
        PhysicalProductDAO physicalProductDAO = new PhysicalProductDAO();
        physicalProductDAO.setId(2);
        // Set PhysicalProductRequestDAO object
        PhysicalProductRequestDAO physicalProductRequestDAO = new PhysicalProductRequestDAO();
        physicalProductRequestDAO.setDateReceived(LocalDate.now());
        physicalProductRequestDAO.setStatus("Approved");
        physicalProductRequestDAO.setProduct(physicalProductDAO);

        // Verify the values set in the PhysicalProductRequestDAO object
        assertEquals(LocalDate.now(), physicalProductRequestDAO.getDateReceived());
        assertEquals("Approved", physicalProductRequestDAO.getStatus());
        assertEquals(physicalProductDAO, physicalProductRequestDAO.getProduct());
    }
}

