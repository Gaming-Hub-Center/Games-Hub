package com.gameshub.requests.approve;

import com.gameshub.exception.ResourceNotFoundException;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.repository.product.DigitalProductRepository;
import com.gameshub.repository.product.PhysicalProductRepository;
import com.gameshub.repository.request.DigitalProductRequestRepository;
import com.gameshub.service.request.approve.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DigitalProductApprovalStrategyTest {

    @Mock
    private DigitalProductRequestRepository digitalProductRequestRepository;

    @Mock
    private DigitalProductRepository digitalProductRepository;

    @InjectMocks
    private DigitalProductApprovalStrategy strategy;

    @Captor
    private ArgumentCaptor<DigitalProductDAO> productCaptor;

    private DigitalProductRequestDAO requestDAO;
    private DigitalProductDAO productDAO;

    @BeforeEach
    void setUp() {
        requestDAO = new DigitalProductRequestDAO();
        productDAO = new DigitalProductDAO();
        // Setup the initial state of requestDAO and productDAO
    }

    @Test
    void approveAndCreateProduct_NormalOperation() {
        when(digitalProductRequestRepository.findById(anyInt())).thenReturn(Optional.of(requestDAO));

        strategy.approveAndCreateProduct(1);

        verify(digitalProductRepository).save(productCaptor.capture());
        DigitalProductDAO capturedProduct = productCaptor.getValue();

        // Assertions to check if capturedProduct has the right properties
        assertEquals(requestDAO.getTitle(), capturedProduct.getTitle());
        // ...other assertions
    }

    @Test
    void approveAndCreateProduct_RequestNotFound() {
        when(digitalProductRequestRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> strategy.approveAndCreateProduct(1));
    }

    @Test
    void approveAndUpdateProduct_NormalOperation() {
        when(digitalProductRequestRepository.findById(anyInt())).thenReturn(Optional.of(requestDAO));
        when(digitalProductRepository.findById(anyInt())).thenReturn(Optional.of(productDAO));

        strategy.approvedAndUpdateProduct(1, 1);

        verify(digitalProductRepository).save(productCaptor.capture());
        DigitalProductDAO capturedProduct = productCaptor.getValue();

        // Assertions to check if capturedProduct has been updated correctly
        assertEquals(requestDAO.getTitle(), capturedProduct.getTitle());
        // ...other assertions
    }

    @Test
    void approveAndUpdateProduct_RequestNotFound() {
        when(digitalProductRequestRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> strategy.approvedAndUpdateProduct(1, 1));
    }

    @Test
    void approveAndUpdateProduct_ProductNotFound() {
        when(digitalProductRequestRepository.findById(anyInt())).thenReturn(Optional.of(requestDAO));
        when(digitalProductRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> strategy.approvedAndUpdateProduct(1, 1));
    }

}

