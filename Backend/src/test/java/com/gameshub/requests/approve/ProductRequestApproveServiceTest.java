package com.gameshub.requests.approve;

import com.gameshub.service.admin.approve.DigitalProductApprovalStrategy;
import com.gameshub.service.admin.approve.PhysicalProductApprovalStrategy;
import com.gameshub.service.admin.approve.ProductRequestApproveService;
import com.gameshub.service.request.approve.*;
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
    void approvePhysicalProductCreation_Success() {
        int requestId = 1;
        productRequestApproveService.approveProductCreation("physical", requestId);
        verify(physicalProductApprovalStrategy).approveAndCreateProduct(requestId);
    }

    @Test
    void approveInvalidProductType_ThrowsException() {
        int requestId = 1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            productRequestApproveService.approveProductCreation("invalidType", requestId);
        });
    }
}
