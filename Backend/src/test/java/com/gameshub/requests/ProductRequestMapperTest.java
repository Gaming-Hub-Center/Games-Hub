package com.gameshub.requests;

import com.gameshub.controller.DTO.product.DigitalProductDTO;
import com.gameshub.controller.DTO.product.PhysicalProductDTO;
import com.gameshub.controller.DTO.request.DigitalProductRequestDTO;
import com.gameshub.controller.DTO.request.PhysicalProductRequestDTO;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.utils.ProductMapper;
import com.gameshub.utils.ProductRequestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductRequestMapperTest {

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductRequestMapper productRequestMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void toDTOPhysicalProductRequestDAOValidTest() {

        // Creating a PhysicalProductRequestDAO object for testing
        PhysicalProductRequestDAO physicalProductRequestDAO = new PhysicalProductRequestDAO();
        physicalProductRequestDAO.setRequestId(1);
        physicalProductRequestDAO.setDateReceived(LocalDate.now());
        physicalProductRequestDAO.setStatus("Pending");
        physicalProductRequestDAO.setRequestType("PHYSICAL");
        physicalProductRequestDAO.setProduct(new PhysicalProductDAO());

        // Mocking behavior for productMapper
        PhysicalProductDTO physicalProductDTO = new PhysicalProductDTO();
        physicalProductDTO.setProductId(123);
        when(productMapper.toProductDTO(physicalProductRequestDAO.getProduct()))
                .thenReturn(physicalProductDTO);

        // Executing the method under test
        PhysicalProductRequestDTO result = productRequestMapper.toDTO(physicalProductRequestDAO);

        // Assertions to verify the conversion
        assertNotNull(result);
        assertEquals(1, result.getRequestId());
        assertEquals(LocalDate.now(), result.getDateReceived());
        assertEquals("Pending", result.getStatus());
        assertNotNull(result.getPhysicalProductDTO());
        assertEquals(123, result.getPhysicalProductDTO().getProductId());
    }

    @Test
    void toDTODigitalProductRequestDAOValidTest() {

        // Creating a DigitalProductRequestDAO object for testing
        DigitalProductRequestDAO digitalProductRequestDAO = new DigitalProductRequestDAO();
        digitalProductRequestDAO.setRequestId(1);
        digitalProductRequestDAO.setDateReceived(LocalDate.now());
        digitalProductRequestDAO.setStatus("Pending");
        digitalProductRequestDAO.setRequestType("DIGITAL");
        digitalProductRequestDAO.setProduct(new DigitalProductDAO());

        // Mocking behavior for productMapper
        DigitalProductDTO digitalProductDTO = new DigitalProductDTO();
        digitalProductDTO.setProductId(456);
        when(productMapper.toProductDTO(digitalProductRequestDAO.getProduct()))
                .thenReturn(digitalProductDTO);

        // Executing the method under test
        DigitalProductRequestDTO result = productRequestMapper.toDTO(digitalProductRequestDAO);

        // Assertions to verify the conversion
        assertNotNull(result);
        assertEquals(1, result.getRequestId());
        assertEquals(LocalDate.now(), result.getDateReceived());
        assertEquals("Pending", result.getStatus());
        assertNotNull(result.getDigitalProductDTO());
        assertEquals(456, result.getDigitalProductDTO().getProductId());
    }

    @Test
    void toDAOPhysicalProductRequestDTOValidTest() {

        // Creating a PhysicalProductRequestDTO object for testing
        PhysicalProductRequestDTO physicalProductRequestDTO = new PhysicalProductRequestDTO();
        PhysicalProductDTO physicalProductDTO = new PhysicalProductDTO();
        physicalProductDTO.setProductId(123);
        physicalProductRequestDTO.setPhysicalProductDTO(physicalProductDTO);
        physicalProductRequestDTO.setRequestId(1);
        physicalProductRequestDTO.setDateReceived(LocalDate.now());
        physicalProductRequestDTO.setStatus("Pending");

        // Mocking behavior for productMapper
        PhysicalProductDAO physicalProductDAO = new PhysicalProductDAO();
        when(productMapper.toProductDAO(physicalProductDTO)).thenReturn(physicalProductDAO);

        // Executing the method under test
        PhysicalProductRequestDAO result = productRequestMapper.toDAO(physicalProductRequestDTO);

        // Assertions to verify the conversion
        assertNotNull(result);
        assertNotNull(result.getProduct());
        assertEquals(physicalProductDAO, result.getProduct());
        assertEquals(1, result.getRequestId());
        assertEquals(LocalDate.now(), result.getDateReceived());
        assertEquals("Pending", result.getStatus());
    }

    @Test
    void toDAODigitalProductRequestDTOValidTest() {
        MockitoAnnotations.openMocks(this);

        // Creating a DigitalProductRequestDTO object for testing
        DigitalProductRequestDTO digitalProductRequestDTO = new DigitalProductRequestDTO();
        DigitalProductDTO digitalProductDTO = new DigitalProductDTO();
        digitalProductDTO.setProductId(456);
        digitalProductRequestDTO.setDigitalProductDTO(digitalProductDTO);
        digitalProductRequestDTO.setRequestId(2);
        digitalProductRequestDTO.setDateReceived(LocalDate.now());
        digitalProductRequestDTO.setStatus("Completed");

        // Mocking behavior for productMapper
        DigitalProductDAO digitalProductDAO = new DigitalProductDAO();
        when(productMapper.toProductDAO(digitalProductDTO)).thenReturn(digitalProductDAO);

        // Executing the method under test
        DigitalProductRequestDAO result = productRequestMapper.toDAO(digitalProductRequestDTO);

        // Assertions to verify the conversion
        assertNotNull(result);
        assertNotNull(result.getProduct());
        assertEquals(digitalProductDAO, result.getProduct());
        assertEquals(2, result.getRequestId());
        assertEquals(LocalDate.now(), result.getDateReceived());
        assertEquals("Completed", result.getStatus());
    }



}
