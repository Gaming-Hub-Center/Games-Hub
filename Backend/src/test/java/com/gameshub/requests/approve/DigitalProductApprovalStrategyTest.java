package com.gameshub.requests.approve;

import com.gameshub.exception.ResourceNotFoundException;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.repository.product.DigitalProductRepository;
import com.gameshub.repository.request.DigitalProductRequestRepository;
import com.gameshub.service.request.approve_product_update_and_create.DigitalProductApprovalStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DigitalProductApprovalStrategyTest {
    @Mock
    private DigitalProductRequestRepository digitalProductRequestRepository;

    @Mock
    private DigitalProductRepository digitalProductRepository;

    @InjectMocks
    private DigitalProductApprovalStrategy strategy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void approveAndCreateProduct_Success() {
        int requestId = 1;
        DigitalProductRequestDAO mockRequest = new DigitalProductRequestDAO();
        when(digitalProductRequestRepository.findById(requestId)).thenReturn(Optional.of(mockRequest));

        strategy.approveAndCreateProduct(requestId);

        verify(digitalProductRequestRepository).save(mockRequest);
        verify(digitalProductRepository).save(any(DigitalProductDAO.class));
    }

    @Test
    void approvedAndUpdateProduct_Success() {
        int requestId = 1;
        int productId = 2;
        DigitalProductRequestDAO mockRequest = new DigitalProductRequestDAO();
        DigitalProductDAO mockProduct = new DigitalProductDAO();
        when(digitalProductRequestRepository.findById(requestId)).thenReturn(Optional.of(mockRequest));
        when(digitalProductRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        strategy.approvedAndUpdateProduct(requestId, productId);

        verify(digitalProductRequestRepository).save(mockRequest);
        verify(digitalProductRepository).save(mockProduct);
    }

    @Test
    void approveAndCreateProduct_RequestNotFound_ThrowsException() {
        int requestId = 1;
        when(digitalProductRequestRepository.findById(requestId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            strategy.approveAndCreateProduct(requestId);
        });
    }

    @Test
    void approvedAndUpdateProduct_ProductNotFound_ThrowsException() {
        int requestId = 1;
        int productId = 2;
        DigitalProductRequestDAO mockRequest = new DigitalProductRequestDAO();
        when(digitalProductRequestRepository.findById(requestId)).thenReturn(Optional.of(mockRequest));
        when(digitalProductRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            strategy.approvedAndUpdateProduct(requestId, productId);
        });
    }
}
