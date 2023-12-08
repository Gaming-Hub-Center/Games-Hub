package com.gameshub.products;

import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.repository.product.PhysicalProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class PhysicalProductDAOTest {

    @Mock
    PhysicalProductRepository physicalProductRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        physicalProductRepository.deleteAll();
        PhysicalProductDAO physicalProductDAO = new PhysicalProductDAO(124, "Title1", 12, "Description1",LocalDate.now(), 10, 1235);
        PhysicalProductDAO physicalProductDAO1 = new PhysicalProductDAO(125, "Title1", 12, "Description1",LocalDate.now(), 10, 1235);
        physicalProductRepository.save(physicalProductDAO1);
        physicalProductRepository.save(physicalProductDAO);
    }


    @Test
    public void testPhysicalProductCreation() {
        PhysicalProductDAO product = new PhysicalProductDAO();
        // Set properties
        product.setTitle("Test Product");
        product.setPrice(20);
        product.setCount(2);
        product.setId(122);
        product.setDescription("Description");
        product.setPostDate(LocalDate.now());

        Assertions.assertNotNull(product);
        Assertions.assertEquals("Test Product", product.getTitle());
        Assertions.assertEquals(20, product.getPrice());
        Assertions.assertEquals(2, product.getCount());
        Assertions.assertEquals(122, product.getId());
        Assertions.assertEquals("Description", product.getDescription());
        Assertions.assertEquals(LocalDate.now(), product.getPostDate());

    }
    // Add more test methods for other functionalities or behavior
}
