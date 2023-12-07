package com.gameshub.products;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gameshub.controller.DTO.product.DigitalProductDTO;
import com.gameshub.controller.DTO.product.PhysicalProductDTO;
import com.gameshub.controller.DTO.product.ProductDTO;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class DigitalProductDTOTest {

    @Test
    public void testDigitalProductDTO() {
        // Create a DigitalProductDTO object
        DigitalProductDTO digitalProductDTO = new DigitalProductDTO();
        digitalProductDTO.setProductId(2);
        digitalProductDTO.setTitle("Sample Digital Product");
        digitalProductDTO.setPrice(15.0f);
        digitalProductDTO.setDescription("This is a digital product");
        digitalProductDTO.setPhysicalOrDigital("digital");
        digitalProductDTO.setPostDate(LocalDate.now());
        digitalProductDTO.setCount(5);
        digitalProductDTO.setCode("ABC123");

        // Verify the values set in the DTO object
        assertEquals(2, digitalProductDTO.getProductId());
        assertEquals("Sample Digital Product", digitalProductDTO.getTitle());
        assertEquals(15.0f, digitalProductDTO.getPrice());
        assertEquals("This is a digital product", digitalProductDTO.getDescription());
        assertEquals("digital", digitalProductDTO.getPhysicalOrDigital());
        assertEquals(LocalDate.now(), digitalProductDTO.getPostDate());
        assertEquals(5, digitalProductDTO.getCount());
        assertEquals("ABC123", digitalProductDTO.getCode());
    }

    @Test
    public void testDigitalProductDTO_() {
        // Create a PhysicalProductDTO object
        ProductDTO physicalProductDTO = new PhysicalProductDTO();
        physicalProductDTO.setProductId(1);
        physicalProductDTO.setTitle("Sample Physical Product");
        physicalProductDTO.setPrice(25.0f);
        physicalProductDTO.setDescription("This is a physical product");
        physicalProductDTO.setPhysicalOrDigital("physical");
        physicalProductDTO.setPostDate(LocalDate.now());
        physicalProductDTO.setCount(10);

        // Verify the values set in the DTO object
        assertEquals(1, physicalProductDTO.getProductId());
        assertEquals("Sample Physical Product", physicalProductDTO.getTitle());
        assertEquals(25.0f, physicalProductDTO.getPrice());
        assertEquals("This is a physical product", physicalProductDTO.getDescription());
        assertEquals("physical", physicalProductDTO.getPhysicalOrDigital());
        assertEquals(LocalDate.now(), physicalProductDTO.getPostDate());
        assertEquals(10, physicalProductDTO.getCount());
    }
}
