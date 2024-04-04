package com.gameshub.service.admin;

import com.gameshub.dto.product.ProductRequestDTO;
import com.gameshub.enums.ProductStatus;
import com.gameshub.enums.ProductType;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.model.request.ProductRequestDAO;
import com.gameshub.model.request.DigitalProductRequestImage;
import com.gameshub.model.request.PhysicalProductRequestImage;
import com.gameshub.repository.product.DigitalProductRequestImageRepository;
import com.gameshub.repository.product.DigitalProductRequestRepository;
import com.gameshub.repository.product.PhysicalProductRequestImageRepository;
import com.gameshub.repository.product.PhysicalProductRequestRepository;
import com.gameshub.utils.ProductRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminProductsService {

    private final DigitalProductApprovalStrategy digitalProductApprovalStrategy;
    private final PhysicalProductApprovalStrategy physicalProductApprovalStrategy;
    private final DigitalProductRequestRepository digitalProductRequestRepository;
    private final PhysicalProductRequestRepository physicalProductRequestRepository;
    private final PhysicalProductRequestImageRepository physicalProductRequestImageRepository;
    private final DigitalProductRequestImageRepository digitalProductRequestImageRepository;
    private final ProductRequestMapper productRequestMapper;


    @Transactional
    public void approveProductCreation(String productType, int requestId) {
        ProductApprovalStrategy strategy = getProductApprovalStrategy(productType);
        strategy.approveAndCreateProduct(requestId);
    }

    private ProductApprovalStrategy getProductApprovalStrategy(String productType) {
        if (ProductType.DIGITAL.name().equalsIgnoreCase(productType)) {
            return digitalProductApprovalStrategy;
        } else if (ProductType.PHYSICAL.name().equalsIgnoreCase(productType)) {
            return physicalProductApprovalStrategy;
        } else {
            throw new IllegalArgumentException("Invalid product type");
        }
    }

    @Transactional
    public void declineProductCreation(String productType, int requestId) {
        if (ProductType.DIGITAL.name().equalsIgnoreCase(productType)) {
            declineDigitalProduct(requestId);
        } else if (ProductType.PHYSICAL.name().equalsIgnoreCase(productType)) {
            declinePhysicalProduct(requestId);
        } else {
            throw new IllegalArgumentException("Invalid product type");
        }
    }

    private void declineDigitalProduct(int requestId) {
        DigitalProductRequestDAO request = digitalProductRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Digital product request not found"));

        if (ProductStatus.PENDING.name().equalsIgnoreCase(request.getStatus())) {
            request.setStatus(ProductStatus.DECLINED.name());
            digitalProductRequestRepository.save(request);
        } else {
            throw new IllegalStateException("Digital product request is not in pending status");
        }
    }

    private void declinePhysicalProduct(int requestId) {
        PhysicalProductRequestDAO request = physicalProductRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Physical product request not found"));

        if (ProductStatus.PENDING.name().equalsIgnoreCase(request.getStatus())) {
            request.setStatus(ProductStatus.DECLINED.name());
            physicalProductRequestRepository.save(request);
        } else {
            throw new IllegalStateException("Physical product request is not in pending status");
        }
    }

    public List<ProductRequestDTO> getAdminProducts(String productType, String status) {
        List<? extends ProductRequestDAO> productRequests;

        if (ProductType.DIGITAL.name().equalsIgnoreCase(productType)) {
            productRequests = digitalProductRequestRepository.findByStatus(status);
        } else if (ProductType.PHYSICAL.name().equalsIgnoreCase(productType)) {
            productRequests = physicalProductRequestRepository.findByStatus(status);
        } else {
            throw new IllegalArgumentException("Invalid product type");
        }

        return productRequests.stream()
                .map(dao -> {
                    ProductRequestDTO dto;
                    if (dao instanceof DigitalProductRequestDAO) {
                        dto = productRequestMapper.toDTO((DigitalProductRequestDAO) dao);
                        List<String> imageUrls = digitalProductRequestImageRepository.findByRequestId(dao.getId())
                                .stream()
                                .map(DigitalProductRequestImage::getImageUrl)
                                .collect(Collectors.toList());
                        dto.setImages(imageUrls);
                    } else if (dao instanceof PhysicalProductRequestDAO) {
                        dto = productRequestMapper.toDTO((PhysicalProductRequestDAO) dao);
                        List<String> imageUrls = physicalProductRequestImageRepository.findByRequestId(dao.getId())
                                .stream()
                                .map(PhysicalProductRequestImage::getImageUrl)
                                .collect(Collectors.toList());
                        dto.setImages(imageUrls);
                    } else {
                        throw new IllegalStateException("Unknown subclass of ProductRequestDAO");
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

}