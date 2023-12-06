package com.gameshub.base_code_test;

import com.gameshub.model.product.PhysicalProductDAO;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class DigitalProductDAOTest {

    @Test
    void testBuilder() {
        LocalDate postDate = LocalDate.now();
        PhysicalProductDAO physicalProductDAO = PhysicalProductDAO.builder()
                .productId(1L)
                .productTitle("E-Book")
                .productPrice(9.99)
                .productDescription("A digital book")
                .physicalOrDigital("Digital")
                .productPostDate(postDate)
                .productCount(100)
                .build();

        assertNotNull(physicalProductDAO);
        assertEquals(1L, physicalProductDAO.getProductId());
        assertEquals("E-Book", physicalProductDAO.getTitle());
        assertEquals(9.99, physicalProductDAO.getPrice());
        assertEquals("A digital book", physicalProductDAO.getDescription());
        assertEquals("Digital", physicalProductDAO.getPhysicalOrDigital());
        assertEquals(postDate, physicalProductDAO.getPostDate());
        assertEquals(100, physicalProductDAO.getCount());
    }
}
