//package com.gameshub.admin;
//
//import com.gameshub.exception.ResourceNotFoundException;
//import com.gameshub.model.request.PhysicalProductRequestDAO;
//import com.gameshub.model.user.SellerDAO;
//import com.gameshub.repository.product.PhysicalProductRepository;
//import com.gameshub.repository.product.PhysicalProductRequestRepository;
//import com.gameshub.repository.user.SellerRepository;
//import com.gameshub.service.admin.PhysicalProductApprovalStrategy;
//import com.sun.jdi.request.InvalidRequestStateException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class PhysicalProductApprovalStrategyTest {
//
//    @Mock
//    private PhysicalProductRequestRepository physicalProductRequestRepository;
//
//    @Mock
//    private PhysicalProductRepository physicalProductRepository;
//
//    @Mock
//    private SellerRepository sellerRepository;
//
//    @InjectMocks
//    private PhysicalProductApprovalStrategy strategy;
//
//    private PhysicalProductRequestDAO request;
//    private SellerDAO seller;
//
//    @BeforeEach
//    void setUp() {
//        seller = new SellerDAO("name", "email", "Pass", "Phone", "Add", 10, "National", LocalDate.now(), "Desc", "Vat");
//        seller.setId(1);
//
//        request = new PhysicalProductRequestDAO(LocalDate.now(), "Pending", "Type", "Title", 10, "Desc", LocalDate.now(), 10, "Cat", seller);
//        request.setId(1);
//    }
//
//    @Test
//    void approveAndCreateProduct_SellerNotFound() {
//        when(physicalProductRequestRepository.findById(request.getId())).thenReturn(java.util.Optional.of(request));
//        when(sellerRepository.existsById(seller.getId())).thenReturn(false);
//
//        assertThrows(ResourceNotFoundException.class, () -> strategy.approveAndCreateProduct(request.getId()));
//    }
//
//    @Test
//    void approveAndCreateProduct_InvalidRequestStatus() {
//        request.setStatus("Approved");
//        when(physicalProductRequestRepository.findById(request.getId())).thenReturn(java.util.Optional.of(request));
//
//        assertThrows(InvalidRequestStateException.class, () -> strategy.approveAndCreateProduct(request.getId()));
//    }
//
//    @Test
//    void approveAndCreateProduct_NullSeller() {
//        request.setSeller(null);
//        when(physicalProductRequestRepository.findById(request.getId())).thenReturn(java.util.Optional.of(request));
//
//        assertThrows(NullPointerException.class, () -> strategy.approveAndCreateProduct(request.getId()));
//    }
//
//}
