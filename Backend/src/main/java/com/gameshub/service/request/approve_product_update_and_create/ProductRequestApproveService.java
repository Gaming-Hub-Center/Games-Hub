package com.gameshub.service.request.approve_product_update_and_create;

import com.gameshub.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductRequestApproveService {

    @Autowired
    private DigitalProductApprovalStrategy digitalProductApprovalStrategy;

    @Autowired
    private PhysicalProductApprovalStrategy physicalProductApprovalStrategy;

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
