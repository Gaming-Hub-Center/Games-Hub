//package com.gameshub.requests.approve;
//
//import com.gameshub.exception.ResourceNotFoundException;
//import com.gameshub.model.product.PhysicalProductDAO;
//import com.gameshub.model.request.PhysicalProductRequestDAO;
//import com.gameshub.repository.product.PhysicalProductRepository;
//import com.gameshub.repository.request.PhysicalProductRequestRepository;
//import com.gameshub.service.request.approve.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class PhysicalProductApprovalStrategyTest {
//
//    @Mock
//    private PhysicalProductRequestRepository physicalProductRequestRepository;
//
//    @Mock
//    private PhysicalProductRepository physicalProductRepository;
//
//    @InjectMocks
//    private PhysicalProductApprovalStrategy strategy;
//
//    @Captor
//    private ArgumentCaptor<PhysicalProductDAO> productCaptor;
//
//    private PhysicalProductRequestDAO requestDAO;
//    private PhysicalProductDAO productDAO;
//
//    @BeforeEach
//    void setUp() {
//        requestDAO = new PhysicalProductRequestDAO();
//        productDAO = new PhysicalProductDAO();
//        // Setup the initial state of requestDAO and productDAO
//    }
//
//    @Test
//    void approveAndCreateProduct_NormalOperation() {
//        when(physicalProductRequestRepository.findById(anyInt())).thenReturn(Optional.of(requestDAO));
//
//        strategy.approveAndCreateProduct(1);
//
//        verify(physicalProductRepository).save(productCaptor.capture());
//        PhysicalProductDAO capturedProduct = productCaptor.getValue();
//
//        // Assertions to check if capturedProduct has the right properties
//        assertEquals(requestDAO.getTitle(), capturedProduct.getTitle());
//        // ...other assertions
//    }
//
//    @Test
//    void approveAndCreateProduct_RequestNotFound() {
//        when(physicalProductRequestRepository.findById(anyInt())).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class, () -> strategy.approveAndCreateProduct(1));
//    }
//
//
//}
//
