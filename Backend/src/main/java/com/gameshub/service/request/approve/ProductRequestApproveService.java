package com.gameshub.service.request.approve;

import lombok.*;
import org.springframework.stereotype.*;

@RequiredArgsConstructor
@Service
public class ProductRequestApproveService {

    private final DigitalProductApprovalStrategy digitalProductApprovalStrategy;
    private final PhysicalProductApprovalStrategy physicalProductApprovalStrategy;

    public void approveProductCreation(String productType, int requestId) {
        ProductApprovalStrategy strategy = getProductApprovalStrategy(productType);
        strategy.approveAndCreateProduct(requestId);
    }

    public void approveProductUpdate(String productType, int requestId, int productId) {
        ProductApprovalStrategy strategy = getProductApprovalStrategy(productType);
        strategy.approvedAndUpdateProduct(requestId, productId);
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