package com.gameshub.service.admin.approve;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@RequiredArgsConstructor
@Service
public class ProductRequestApproveService {

    @Autowired
    private DigitalProductApprovalStrategy digitalProductApprovalStrategy;
    @Autowired
    private PhysicalProductApprovalStrategy physicalProductApprovalStrategy;

    public int approveProductCreation(String productType, int requestId) {
        ProductApprovalStrategy strategy = getProductApprovalStrategy(productType);
        return strategy.approveAndCreateProduct(requestId);
    }

    // Factory of strategies
    private ProductApprovalStrategy getProductApprovalStrategy(String productType) {
        if ("digital".equalsIgnoreCase(productType)) {
            return digitalProductApprovalStrategy;
        } else if ("physical".equalsIgnoreCase(productType)) {
            return physicalProductApprovalStrategy;
        } else {
            throw new IllegalArgumentException("Invalid product type");
        }
    }

}