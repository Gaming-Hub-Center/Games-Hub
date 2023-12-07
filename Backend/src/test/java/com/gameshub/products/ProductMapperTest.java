package com.gameshub.products;

import com.gameshub.controller.DTO.product.*;
import com.gameshub.model.product.*;

import com.gameshub.utils.ProductMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductMapperTest {

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Test
    public void testToProductDTOConversion() {
        // Create a sample DigitalProductDAO
        DigitalProductDAO digitalProductDAO = new DigitalProductDAO();
        digitalProductDAO.setId(1);
        digitalProductDAO.setTitle("Sample Digital Product");
        digitalProductDAO.setPrice(29);

        // Convert DigitalProductDAO to DigitalProductDTO
        DigitalProductDTO digitalProductDTO = productMapper.toProductDTO(digitalProductDAO);

        assertNotNull(digitalProductDTO);
        assertEquals(digitalProductDTO.getId(), digitalProductDAO.getId());
        assertEquals(digitalProductDTO.getTitle(), digitalProductDAO.getTitle());
        assertEquals(digitalProductDTO.getPrice(), digitalProductDAO.getPrice());
        // Add more assertions based on your fields

        // Create a sample PhysicalProductDAO
        PhysicalProductDAO physicalProductDAO = new PhysicalProductDAO();
        physicalProductDAO.setId(2);
        physicalProductDAO.setTitle("Sample Physical Product");

        // Convert PhysicalProductDAO to PhysicalProductDTO
        PhysicalProductDTO physicalProductDTO = productMapper.toProductDTO(physicalProductDAO);

        assertNotNull(physicalProductDTO);
        assertEquals(physicalProductDTO.getId(), physicalProductDAO.getId());
        assertEquals(physicalProductDTO.getTitle(), physicalProductDAO.getTitle());
        // Add more assertions based on your fields
    }

    @Test
    public void testToProductDAOConversion() {
        // Create a sample DigitalProductDTO
        DigitalProductDTO digitalProductDTO = new DigitalProductDTO();
        digitalProductDTO.setId(1);
        digitalProductDTO.setTitle("Sample Digital Product");
        digitalProductDTO.setPrice(29);

        // Convert DigitalProductDTO to DigitalProductDAO
        DigitalProductDAO digitalProductDAO = productMapper.toProductDAO(digitalProductDTO);

        assertNotNull(digitalProductDAO);
        assertEquals(digitalProductDAO.getId(), digitalProductDTO.getId());
        assertEquals(digitalProductDAO.getTitle(), digitalProductDTO.getTitle());
        assertEquals(digitalProductDAO.getPrice(), digitalProductDTO.getPrice());
        // Add more assertions based on your fields

        // Create a sample PhysicalProductDTO
        PhysicalProductDTO physicalProductDTO = new PhysicalProductDTO();
        physicalProductDTO.setId(2);
        physicalProductDTO.setTitle("Sample Physical Product");

        // Convert PhysicalProductDTO to PhysicalProductDAO
        PhysicalProductDAO physicalProductDAO = productMapper.toProductDAO(physicalProductDTO);

        assertNotNull(physicalProductDAO);
        assertEquals(physicalProductDAO.getId(), physicalProductDTO.getId());
        assertEquals(physicalProductDAO.getTitle(), physicalProductDTO.getTitle());
        // Add more assertions based on your fields
    }
}
