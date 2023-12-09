package com.gameshub.model.request;

import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductRequestDAOTest {

    @Mock
    PhysicalProductDAO physicalProductDAO;

    @Test
    void testPhysicalProductRequestDAO() {
//        // Create a PhysicalProductDAO object
//
//        // Create a PhysicalProductRequestDAO object
//        PhysicalProductRequestDAO physicalProductRequestDAO = new PhysicalProductRequestDAO();
//        physicalProductRequestDAO.setRequestId(1);
//        physicalProductRequestDAO.setDateReceived(LocalDate.now());
//        physicalProductRequestDAO.setStatus("Pending");
//        physicalProductRequestDAO.setProduct(physicalProductDAO);
//
//        // Verify properties in PhysicalProductRequestDAO
//        assertNotNull(physicalProductRequestDAO);
//        assertEquals(1L, physicalProductRequestDAO.getRequestId());
//        assertEquals(LocalDate.now(), physicalProductRequestDAO.getDateReceived());
//        assertEquals("Pending", physicalProductRequestDAO.getStatus());
//        assertEquals(physicalProductDAO, physicalProductRequestDAO.getProduct());
    }

    @Test
    void testDigitalProductRequestDAO() {
//        // Create a DigitalProductDAO object
//        DigitalProductDAO digitalProductDAO = new DigitalProductDAO();
//        digitalProductDAO.setId(2);
//        // Set other properties...
//
//        // Create a DigitalProductRequestDAO object
//        DigitalProductRequestDAO digitalProductRequestDAO = new DigitalProductRequestDAO();
//        digitalProductRequestDAO.setRequestId(2);
//        digitalProductRequestDAO.setDateReceived(LocalDate.now());
//        digitalProductRequestDAO.setStatus("Approved");
//        digitalProductRequestDAO.setProduct(digitalProductDAO);
//
//        // Verify properties in DigitalProductRequestDAO
//        assertNotNull(digitalProductRequestDAO);
//        assertEquals(2L, digitalProductRequestDAO.getRequestId());
//        assertEquals(LocalDate.now(), digitalProductRequestDAO.getDateReceived());
//        assertEquals("Approved", digitalProductRequestDAO.getStatus());
//        assertEquals(digitalProductDAO, digitalProductRequestDAO.getProduct());
    }
}
