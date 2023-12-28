package com.gameshub.service.admin;

import com.gameshub.controller.DTO.request.ProductRequestDTO;
import com.gameshub.model.product.ProductDAO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.model.request.ProductRequestDAO;
import com.gameshub.model.request.image.DigitalProductRequestImage;
import com.gameshub.model.request.image.PhysicalProductRequestImage;
import com.gameshub.repository.request.DigitalProductRequestImageRepository;
import com.gameshub.repository.request.DigitalProductRequestRepository;
import com.gameshub.repository.request.PhysicalProductRequestImageRepository;
import com.gameshub.repository.request.PhysicalProductRequestRepository;
import com.gameshub.utils.ProductRequestMapper;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.ArrayList;
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
    private final PhysicalProductRequestImageRepository physicalProductRequestImageRepository;
    private final DigitalProductRequestImageRepository digitalProductRequestImageRepository;
    private final ProductRequestMapper productRequestMapper;



    public void approveProductCreation(String productType, int requestId) {
        ProductApprovalStrategy strategy = getProductApprovalStrategy(productType);
        strategy.approveAndCreateProduct(requestId);
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

//    public List<ProductRequestDTO> getAdminProducts(String productType, String status) {
//        List<? extends ProductRequestDAO> productRequests;
//
//        if ("digital".equalsIgnoreCase(productType)) {
//            productRequests = digitalProductRequestRepository.findByStatus(status);
//        } else if ("physical".equalsIgnoreCase(productType)) {
//            productRequests = physicalProductRequestRepository.findByStatus(status);
//        } else {
//            throw new IllegalArgumentException("Invalid product type");
//        }
//
//        return productRequests.stream()
//                .map(dao -> {
//                    if (dao instanceof DigitalProductRequestDAO) {
//                        return productRequestMapper.toDTO((DigitalProductRequestDAO) dao);
//                    } else if (dao instanceof PhysicalProductRequestDAO) {
//                        return productRequestMapper.toDTO((PhysicalProductRequestDAO) dao);
//                    } else {
//                        throw new IllegalStateException("Unknown subclass of ProductRequestDAO");
//                    }
//                })
//                .collect(Collectors.toList());
//    }


    public List<ProductRequestDTO> getAdminProducts(String productType, String status) {
        List<? extends ProductRequestDAO> productRequests;

        if ("digital".equalsIgnoreCase(productType)) {
            productRequests = digitalProductRequestRepository.findByStatus(status);
        } else if ("physical".equalsIgnoreCase(productType)) {
            productRequests = physicalProductRequestRepository.findByStatus(status);
        } else {
            throw new IllegalArgumentException("Invalid product type");
        }

        return productRequests.stream()
                .map(dao -> {
                    ProductRequestDTO dto;
                    if (dao instanceof DigitalProductRequestDAO) {
                        dto = productRequestMapper.toDTO((DigitalProductRequestDAO) dao);
                        List<String> imageUrls = digitalProductRequestImageRepository.findByDigitalProductRequest_Id(dao.getId())
                                .stream()
                                .map(DigitalProductRequestImage::getImageUrl)
                                .collect(Collectors.toList());
                        dto.setImages(imageUrls);
                    } else if (dao instanceof PhysicalProductRequestDAO) {
                        dto = productRequestMapper.toDTO((PhysicalProductRequestDAO) dao);
                        List<String> imageUrls = physicalProductRequestImageRepository.findByPhysicalProductRequest_Id(dao.getId())
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


    @Transactional
    public void declineProductCreation(String productType, int requestId) {
        if ("digital".equalsIgnoreCase(productType)) {
            declineDigitalProduct(requestId);
        } else if ("physical".equalsIgnoreCase(productType)) {
            declinePhysicalProduct(requestId);
        } else {
            throw new IllegalArgumentException("Invalid product type");
        }
    }

    // compact them
    private void declineDigitalProduct(int requestId) {
        DigitalProductRequestDAO request = digitalProductRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Digital product request not found"));

        if ("pending".equalsIgnoreCase(request.getStatus())) {
            request.setStatus("Declined");
            digitalProductRequestRepository.save(request);
        } else {
            throw new IllegalStateException("Digital product request is not in pending status");
        }
    }

    private void declinePhysicalProduct(int requestId) {
        PhysicalProductRequestDAO request = physicalProductRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Physical product request not found"));

        if ("pending".equalsIgnoreCase(request.getStatus())) {
            request.setStatus("Declined");
            physicalProductRequestRepository.save(request);
        } else {
            throw new IllegalStateException("Physical product request is not in pending status");
        }
    }

}