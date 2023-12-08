package com.gameshub.products;

import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.repository.product.PhysicalProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class DigitalProductDAOTest {


    @Test
    public void testDigitalProductCreation() { // TODO complete the test
        DigitalProductDAO product = new DigitalProductDAO();
        // Set properties
        product.setTitle("Test Digital Product");
        product.setPrice(15);

        Assertions.assertNotNull(product);
        Assertions.assertEquals("Test Digital Product", product.getTitle());
        Assertions.assertEquals(15, product.getPrice());
        // Add more assertions for other properties
    }
    // Add more test methods for other functionalities or behavior
}
