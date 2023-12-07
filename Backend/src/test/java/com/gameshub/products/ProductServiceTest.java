package com.gameshub.products;

import com.gameshub.controller.DTO.product.DigitalProductDTO;
import com.gameshub.controller.DTO.product.PhysicalProductDTO;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.repository.product.DigitalProductRepository;
import com.gameshub.repository.product.PhysicalProductRepository;
import com.gameshub.service.product.ProductService;
import com.gameshub.utils.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

public class ProductServiceTest {

    @Mock
    private PhysicalProductRepository physicalProductRepository;

    @Mock
    private DigitalProductRepository digitalProductRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Mock
    private DigitalProductDAO digitalProductDAO;

    @Mock
    private PhysicalProductDAO physicalProductDAO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void savePhysicalProductTest() {

        // Create a corresponding PhysicalProductDAO
        PhysicalProductDTO physicalProductDTO = new PhysicalProductDTO();
        physicalProductDTO.setTitle("Test Product");
        physicalProductDTO.setPrice(20.0f);
        physicalProductDTO.setCount(2);
        physicalProductDTO.setId(122);
        physicalProductDTO.setDescription("Description");
        physicalProductDTO.setPostDate(LocalDate.now());

        // Mock the behavior of the productMapper to return the expected PhysicalProductDAO
        Mockito.when(productMapper.toProductDAO(physicalProductDTO)).thenReturn(physicalProductDAO);

        // Call the method under test
        productService.saveProduct(physicalProductDTO);

        // Verify that the save method of PhysicalProductRepository is called with the corresponding DAO object
        verify(physicalProductRepository).save(physicalProductDAO);
    }

    @Test
    public void saveDigitalProductTest() {

        DigitalProductDTO digitalProductDTO = new DigitalProductDTO();
        digitalProductDTO.setTitle("Test Product");
        digitalProductDTO.setPrice(20.0f);
        digitalProductDTO.setCount(2);
        digitalProductDTO.setId(122);
        digitalProductDTO.setDescription("Description");
        digitalProductDTO.setPostDate(LocalDate.now());
        digitalProductDTO.setCode("Code");

        Mockito.when(productMapper.toProductDAO(digitalProductDTO)).thenReturn(digitalProductDAO);

        productService.saveProduct(digitalProductDTO);

        // Verify that save method of DigitalProductRepository is called with the corresponding DAO object
        verify(digitalProductRepository).save(digitalProductDAO);
    }
}
