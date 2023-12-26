package com.gameshub.service.admin;

import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.model.request.ProductRequestDAO;
import com.gameshub.repository.request.DigitalProductRequestRepository;
import com.gameshub.repository.request.PhysicalProductRequestRepository;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class AdminProductsService {
    private final DigitalProductApprovalStrategy digitalProductApprovalStrategy;
    private final PhysicalProductApprovalStrategy physicalProductApprovalStrategy;
    private final DigitalProductRequestRepository digitalProductRequestRepository;
    private final PhysicalProductRequestRepository physicalProductRequestRepository;


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