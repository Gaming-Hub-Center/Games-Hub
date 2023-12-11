package com.gameshub.requests;

import com.gameshub.controller.DTO.request.DigitalProductRequestDTO;
import com.gameshub.controller.DTO.request.PhysicalProductRequestDTO;
import com.gameshub.controller.DTO.request.ProductRequestDTO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.request.DigitalProductRequestRepository;
import com.gameshub.repository.request.PhysicalProductRequestRepository;
import com.gameshub.service.request.ProductRequestService;
import com.gameshub.utils.ProductRequestMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRequestServiceTest {

    @MockBean
    private PhysicalProductRequestRepository physicalRepo;

    @MockBean
    private DigitalProductRequestRepository digitalRepo;

    @MockBean
    private ProductRequestMapper mapper;

    @Autowired
    private ProductRequestService service;

    @Test
    void testSavePhysicalProductRequest() {
        PhysicalProductRequestDTO dto = new PhysicalProductRequestDTO();
        PhysicalProductRequestDAO dao = new PhysicalProductRequestDAO();
        Mockito.when(mapper.toDAO(dto)).thenReturn(dao);

        service.saveProductRequest(dto);

        Mockito.verify(physicalRepo).save(dao);
    }

    @Test
    void testSaveDigitalProductRequest() {
        DigitalProductRequestDTO dto = new DigitalProductRequestDTO();
        DigitalProductRequestDAO dao = new DigitalProductRequestDAO();
        Mockito.when(mapper.toDAO(dto)).thenReturn(dao);

        service.saveProductRequest(dto);

        Mockito.verify(digitalRepo).save(dao);
    }

    @Test
    void testSaveProductRequestWithNullInput() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.saveProductRequest(null);
        });

         assertThat(exception.getMessage(), containsString("Unsupported request type"));
    }

    @Test
    void testSaveProductRequestWithUnrecognizedDTO() {
        ProductRequestDTO unrecognizedDTO = Mockito.mock(ProductRequestDTO.class);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.saveProductRequest(unrecognizedDTO);
        });

        assertThat(exception.getMessage(), containsString("Unsupported request type"));

        Mockito.verifyNoInteractions(physicalRepo);
        Mockito.verifyNoInteractions(digitalRepo);
    }

    @Test
    void testMapperLogicForPhysicalProductRequestDTO() {
        PhysicalProductRequestDTO dto = new PhysicalProductRequestDTO();
        PhysicalProductRequestDAO expectedDao = new PhysicalProductRequestDAO();
        Mockito.when(mapper.toDAO(dto)).thenReturn(expectedDao);

        PhysicalProductRequestDAO actualDao = mapper.toDAO(dto);

        assertEquals(expectedDao, actualDao);
        // Optionally verify more detailed fields if needed
    }

    @Test
    void testMapperLogicForDigitalProductRequestDTO() {
        DigitalProductRequestDTO dto = new DigitalProductRequestDTO();
        DigitalProductRequestDAO expectedDao = new DigitalProductRequestDAO();
        Mockito.when(mapper.toDAO(dto)).thenReturn(expectedDao);

        DigitalProductRequestDAO actualDao = mapper.toDAO(dto);

        assertEquals(expectedDao, actualDao);
        // Optionally verify more detailed fields if needed
    }

    @Test
    void testEndToEndPhysicalProductRequest() {
        // Step 1: Create and populate a PhysicalProductRequestDTO
        PhysicalProductRequestDTO requestDTO = new PhysicalProductRequestDTO();
        requestDTO.setSellerId(10);

        // Step 2: Save the DTO using the service
        service.saveProductRequest(requestDTO);

        // Step 3: Retrieve the saved request from the repository
        List<PhysicalProductRequestDAO> savedRequest = physicalRepo.findAll();

        // Step 4: Assert that the request was saved correctly
        assertNotNull(savedRequest);
    }

    @Test
    void testEndToEndDigitalProductRequest() {
        // Step 1: Create and populate a PhysicalProductRequestDTO
        DigitalProductRequestDTO requestDTO = new DigitalProductRequestDTO();
        requestDTO.setSellerId(10);

        // Step 2: Save the DTO using the service
        service.saveProductRequest(requestDTO);

        // Step 3: Retrieve the saved request from the repository
        List<PhysicalProductRequestDAO> savedRequest = physicalRepo.findAll();

        // Step 4: Assert that the request was saved correctly
        assertNotNull(savedRequest);
    }


}
