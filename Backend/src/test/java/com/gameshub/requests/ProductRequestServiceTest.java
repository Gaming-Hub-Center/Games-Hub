package com.gameshub.requests;

import com.gameshub.controller.DTO.product.DigitalProductDTO;
import com.gameshub.controller.DTO.product.PhysicalProductDTO;
import com.gameshub.controller.DTO.request.DigitalProductRequestDTO;
import com.gameshub.controller.DTO.request.PhysicalProductRequestDTO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.repository.request.DigitalProductRequestRepository;
import com.gameshub.repository.request.PhysicalProductRequestRepository;
import com.gameshub.service.request.ProductRequestService;
import com.gameshub.utils.ProductRequestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class ProductRequestServiceTest {

    @Mock
    private PhysicalProductRequestRepository physicalProductRequestRepository;

    @Mock
    private DigitalProductRequestRepository digitalProductRequestRepository;

    @Mock
    private ProductRequestMapper productRequestMapper;

    @InjectMocks
    private ProductRequestService productRequestService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void savePhysicalProductRequest_Success() {
        PhysicalProductRequestDTO physicalProductRequestDTO = new PhysicalProductRequestDTO();
        physicalProductRequestDTO.setProductType("PHYSICAL");
        physicalProductRequestDTO.setRequestType("DELETE");
        physicalProductRequestDTO.setRequestId(125);
        physicalProductRequestDTO.setStatus("PENDING");
        physicalProductRequestDTO.setPhysicalProductDTO(new PhysicalProductDTO());
        physicalProductRequestDTO.setDateReceived(LocalDate.now());

        PhysicalProductRequestDAO physicalProductRequestDAO = new PhysicalProductRequestDAO();


        when(productRequestMapper.toDAO(physicalProductRequestDTO)).thenReturn(physicalProductRequestDAO);

        productRequestService.saveProductRequest(physicalProductRequestDTO);

        verify(productRequestMapper, times(1)).toDAO(physicalProductRequestDTO);
        verify(physicalProductRequestRepository, times(1)).save(physicalProductRequestDAO);
        verify(digitalProductRequestRepository, never()).save(any());
    }

    @Test
    void saveDigitalProductRequest_Success() {
        DigitalProductRequestDTO digitalProductRequestDTO = new DigitalProductRequestDTO();
        digitalProductRequestDTO.setProductType("PHYSICAL");
        digitalProductRequestDTO.setRequestType("DELETE");
        digitalProductRequestDTO.setRequestId(125);
        digitalProductRequestDTO.setStatus("PENDING");
        digitalProductRequestDTO.setDigitalProductDTO(new DigitalProductDTO());
        digitalProductRequestDTO.setDateReceived(LocalDate.now());

        DigitalProductRequestDAO digitalProductRequestDAO = new DigitalProductRequestDAO();
        // Set up digitalProductRequestDAO properties...

        when(productRequestMapper.toDAO(digitalProductRequestDTO)).thenReturn(digitalProductRequestDAO);

        productRequestService.saveProductRequest(digitalProductRequestDTO);

        verify(productRequestMapper, times(1)).toDAO(digitalProductRequestDTO);
        verify(digitalProductRequestRepository, times(1)).save(digitalProductRequestDAO);
        verify(physicalProductRequestRepository, never()).save(any());
    }

    // Additional test cases for error scenarios, null checks, etc.
}
