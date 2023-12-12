package com.gameshub.requests.approve;

import com.gameshub.service.request.approve_product_update_and_create.DigitalProductApprovalStrategy;
import com.gameshub.service.request.approve_product_update_and_create.PhysicalProductApprovalStrategy;
import com.gameshub.service.request.approve_product_update_and_create.ProductRequestApproveService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ProductRequestApproveServiceTest {

    @Autowired
    private ProductRequestApproveService productRequestApproveService;

    @MockBean
    private DigitalProductApprovalStrategy digitalProductApprovalStrategy;

    @MockBean
    private PhysicalProductApprovalStrategy physicalProductApprovalStrategy;

    @Test
    void approveDigitalProductCreation_Success() {
        int requestId = 1;
        productRequestApproveService.approveProductCreation("digital", requestId);
        verify(digitalProductApprovalStrategy).approveAndCreateProduct(requestId);
    }

    @Test
    void approveDigitalProductUpdate_Success() {
        int requestId = 1;
        int productId = 2;
        productRequestApproveService.approveProductUpdate("digital", requestId, productId);
        verify(digitalProductApprovalStrategy).approvedAndUpdateProduct(requestId, productId);
    }

    @Test
    void approvePhysicalProductCreation_Success() {
        int requestId = 1;
        productRequestApproveService.approveProductCreation("physical", requestId);
        verify(physicalProductApprovalStrategy).approveAndCreateProduct(requestId);
    }

    @Test
    void approvePhysicalProductUpdate_Success() {
        int requestId = 1;
        int productId = 2;
        productRequestApproveService.approveProductUpdate("physical", requestId, productId);
        verify(physicalProductApprovalStrategy).approvedAndUpdateProduct(requestId, productId);
    }

    @Test
    void approveInvalidProductType_ThrowsException() {
        int requestId = 1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            productRequestApproveService.approveProductCreation("invalidType", requestId);
        });
    }
}
