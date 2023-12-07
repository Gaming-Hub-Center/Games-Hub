package com.gameshub.products;

import com.gameshub.model.product.DigitalProductDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DigitalProductDAOTest {

    @Test
    public void testDigitalProductCreation() {
        DigitalProductDAO product = new DigitalProductDAO();
        // Set properties
        product.setTitle("Test Digital Product");
        product.setPrice(15.0f);


        Assertions.assertNotNull(product);
        Assertions.assertEquals("Test Digital Product", product.getTitle());
        Assertions.assertEquals(15.0f, product.getPrice());
        // Add more assertions for other properties
    }
    // Add more test methods for other functionalities or behavior
}
