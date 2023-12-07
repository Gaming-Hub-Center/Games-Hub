package com.gameshub.products;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gameshub.controller.DTO.product.PhysicalProductDTO;
import com.gameshub.controller.DTO.product.ProductDTO;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class PhysicalProductDTOTest {

    @Test
    public void testPhysicalProductDTO() {
        // Create a PhysicalProductDTO object
        PhysicalProductDTO physicalProductDTO = new PhysicalProductDTO();
        physicalProductDTO.setId(1);
        physicalProductDTO.setTitle("Sample Physical Product");
        physicalProductDTO.setPrice(25.0f);
        physicalProductDTO.setDescription("This is a physical product");
        physicalProductDTO.setPhysicalOrDigital("physical");
        physicalProductDTO.setPostDate(LocalDate.now());
        physicalProductDTO.setCount(10);

        // Verify the values set in the DTO object
        assertEquals(1, physicalProductDTO.getId());
        assertEquals("Sample Physical Product", physicalProductDTO.getTitle());
        assertEquals(25.0f, physicalProductDTO.getPrice());
        assertEquals("This is a physical product", physicalProductDTO.getDescription());
        assertEquals("physical", physicalProductDTO.getPhysicalOrDigital());
        assertEquals(LocalDate.now(), physicalProductDTO.getPostDate());
        assertEquals(10, physicalProductDTO.getCount());
    }

    @Test
    public void testPhysicalProductDTO_() {
        // Create a PhysicalProductDTO object
        ProductDTO physicalProductDTO = new PhysicalProductDTO();
        physicalProductDTO.setId(1);
        physicalProductDTO.setTitle("Sample Physical Product");
        physicalProductDTO.setPrice(25.0f);
        physicalProductDTO.setDescription("This is a physical product");
        physicalProductDTO.setPhysicalOrDigital("physical");
        physicalProductDTO.setPostDate(LocalDate.now());
        physicalProductDTO.setCount(10);

        // Verify the values set in the DTO object
        assertEquals(1, physicalProductDTO.getId());
        assertEquals("Sample Physical Product", physicalProductDTO.getTitle());
        assertEquals(25.0f, physicalProductDTO.getPrice());
        assertEquals("This is a physical product", physicalProductDTO.getDescription());
        assertEquals("physical", physicalProductDTO.getPhysicalOrDigital());
        assertEquals(LocalDate.now(), physicalProductDTO.getPostDate());
        assertEquals(10, physicalProductDTO.getCount());
    }
}
