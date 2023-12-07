package com.gameshub.requests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class DigitalProductRequestDAOTest {

    @Test
    public void testDigitalProductRequestDAO() {
        // Create a DigitalProductDAO object
        DigitalProductDAO digitalProductDAO = new DigitalProductDAO();
        digitalProductDAO.setId(1);
        // Set DigitalProductRequestDAO object
        DigitalProductRequestDAO digitalProductRequestDAO = new DigitalProductRequestDAO();
        digitalProductRequestDAO.setDateReceived(LocalDate.now());
        digitalProductRequestDAO.setStatus("Pending");
        digitalProductRequestDAO.setProduct(digitalProductDAO);

        // Verify the values set in the DigitalProductRequestDAO object
        assertEquals(LocalDate.now(), digitalProductRequestDAO.getDateReceived());
        assertEquals("Pending", digitalProductRequestDAO.getStatus());
        assertEquals(digitalProductDAO, digitalProductRequestDAO.getProduct());
    }
}
