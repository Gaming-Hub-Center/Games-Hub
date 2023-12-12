package com.gameshub.requests.approve;

import com.gameshub.exception.ResourceNotFoundException;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.repository.product.PhysicalProductRepository;
import com.gameshub.repository.request.PhysicalProductRequestRepository;
import com.gameshub.service.request.approve_product_update_and_create.PhysicalProductApprovalStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PhysicalProductApprovalStrategyTest {
    @Mock
    private PhysicalProductRequestRepository physicalProductRequestRepository;

    @Mock
    private PhysicalProductRepository physicalProductRepository;

    @InjectMocks
    private PhysicalProductApprovalStrategy strategy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void approveAndCreateProduct_Success() {
        int requestId = 1;
        PhysicalProductRequestDAO mockRequest = new PhysicalProductRequestDAO();
        when(physicalProductRequestRepository.findById(requestId)).thenReturn(Optional.of(mockRequest));

        strategy.approveAndCreateProduct(requestId);

        verify(physicalProductRequestRepository).save(mockRequest);
        verify(physicalProductRepository).save(any(PhysicalProductDAO.class));
    }

    @Test
    void approvedAndUpdateProduct_Success() {
        int requestId = 1;
        int productId = 2;
        PhysicalProductRequestDAO mockRequest = new PhysicalProductRequestDAO();
        PhysicalProductDAO mockProduct = new PhysicalProductDAO();
        when(physicalProductRequestRepository.findById(requestId)).thenReturn(Optional.of(mockRequest));
        when(physicalProductRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        strategy.approvedAndUpdateProduct(requestId, productId);

        verify(physicalProductRequestRepository).save(mockRequest);
        verify(physicalProductRepository).save(mockProduct);
    }

    @Test
    void approveAndCreateProduct_RequestNotFound_ThrowsException() {
        int requestId = 1;
        when(physicalProductRequestRepository.findById(requestId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            strategy.approveAndCreateProduct(requestId);
        });
    }

    @Test
    void approvedAndUpdateProduct_ProductNotFound_ThrowsException() {
        int requestId = 1;
        int productId = 2;
        PhysicalProductRequestDAO mockRequest = new PhysicalProductRequestDAO();
        when(physicalProductRequestRepository.findById(requestId)).thenReturn(Optional.of(mockRequest));
        when(physicalProductRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            strategy.approvedAndUpdateProduct(requestId, productId);
        });
    }

}
