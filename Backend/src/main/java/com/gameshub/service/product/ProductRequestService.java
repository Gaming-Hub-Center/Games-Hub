package com.gameshub.service.product;

import com.gameshub.dto.product.DigitalProductRequestDTO;
import com.gameshub.dto.product.PhysicalProductRequestDTO;
import com.gameshub.dto.product.ProductPatchDTO;
import com.gameshub.dto.product.ProductRequestDTO;
import com.gameshub.enums.ProductStatus;
import com.gameshub.exception.ResourceAlreadyFoundException;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.DigitalProductRequestImage;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestImage;
import com.gameshub.repository.product.DigitalProductRequestImageRepository;
import com.gameshub.repository.product.DigitalProductRequestRepository;
import com.gameshub.repository.product.PhysicalProductRequestImageRepository;
import com.gameshub.repository.product.PhysicalProductRequestRepository;
import com.gameshub.utils.ProductRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductRequestService {

    private final PhysicalProductRequestRepository physicalProductRequestRepository;
    private final DigitalProductRequestRepository digitalProductRequestRepository;
    private final ProductRequestMapper productRequestMapper;
    private final PhysicalProductRequestImageRepository physicalProductRequestImageRepository;
    private final DigitalProductRequestImageRepository digitalProductRequestImageRepository;

    public void saveProductRequest(ProductRequestDTO productRequestDTO) {
        if (productRequestDTO instanceof PhysicalProductRequestDTO) {
            PhysicalProductRequestDAO productRequestDAO = productRequestMapper.toDAO((PhysicalProductRequestDTO) productRequestDTO);
            if (isNotDuplicate(productRequestDTO)) {
                productRequestDAO.setRequestType(ProductStatus.PENDING.name());
                PhysicalProductRequestDAO savedRequest = physicalProductRequestRepository.save(productRequestDAO);

                List<String> imageUrls = (productRequestDTO).getImages();
                if(imageUrls!=null) {
                    for (String imageUrl : imageUrls) {
                        PhysicalProductRequestImage imageDAO = new PhysicalProductRequestImage();
                        imageDAO.setImageUrl(imageUrl);
                        productRequestDAO.setId(savedRequest.getId());
                        imageDAO.setRequestId(savedRequest.getId());
                        physicalProductRequestImageRepository.save(imageDAO);
                    }
                }
            } else
                throw new ResourceAlreadyFoundException("Duplicate Found");
        } else if (productRequestDTO instanceof DigitalProductRequestDTO) {
            DigitalProductRequestDAO productRequestDAO = productRequestMapper.toDAO((DigitalProductRequestDTO) productRequestDTO);
            if (isNotDuplicate(productRequestDTO)) {
                productRequestDAO.setStatus(ProductStatus.PENDING.name());
                DigitalProductRequestDAO savedRequest = digitalProductRequestRepository.save(productRequestDAO);

                // Save image URLs
                List<String> imageUrls = (productRequestDTO).getImages();
                if (imageUrls != null) {
                    for (String imageUrl : imageUrls) {
                        DigitalProductRequestImage imageDAO = new DigitalProductRequestImage();
                        imageDAO.setImageUrl(imageUrl);
                        productRequestDAO.setId(savedRequest.getId());
                        imageDAO.setRequestId(savedRequest.getId());
                        digitalProductRequestImageRepository.save(imageDAO);
                    }
                }
            } else
                throw new ResourceAlreadyFoundException("Duplicate Found");
        } else {
            throw new RuntimeException("Unsupported request type");
        }
    }

    private Boolean isNotDuplicate(ProductRequestDTO productRequestDTO) {
        if (productRequestDTO instanceof PhysicalProductRequestDTO)
            return !physicalProductRequestRepository.existsByDescriptionAndTitleAndSellerIdAndStatus(
                    productRequestDTO.getDescription(),
                    productRequestDTO.getTitle(),
                    productRequestDTO.getSellerId(),
                    ProductStatus.PENDING.name())
                    && !physicalProductRequestRepository.existsByDescriptionAndTitleAndSellerIdAndStatus(
                    productRequestDTO.getDescription(),
                    productRequestDTO.getTitle(),
                    productRequestDTO.getSellerId(),
                    ProductStatus.APPROVED.name());
        else if (productRequestDTO instanceof DigitalProductRequestDTO)
            return !digitalProductRequestRepository.existsByDescriptionAndTitleAndSellerIdAndStatus(
                    productRequestDTO.getDescription(),
                    productRequestDTO.getTitle(),
                    productRequestDTO.getSellerId(),
                    ProductStatus.PENDING.name())
                    && !digitalProductRequestRepository.existsByDescriptionAndTitleAndSellerIdAndStatus(
                    productRequestDTO.getDescription(),
                    productRequestDTO.getTitle(),
                    productRequestDTO.getSellerId(),
                    ProductStatus.APPROVED.name());
        return false;
    }

    public DigitalProductRequestDAO getDigitalProductRequestByProductId(int productId) {
        Optional<DigitalProductRequestDAO> foundProduct = digitalProductRequestRepository.findById(productId);
        return foundProduct.orElse(null);
    }

    public PhysicalProductRequestDAO getPhysicalProductRequestByProductId(int productId) {
        Optional<PhysicalProductRequestDAO> foundProduct = physicalProductRequestRepository.findById(productId);
        return foundProduct.orElse(null);
    }

    public List<PhysicalProductRequestDAO> getAllPendingPhysicalProductRequestsBySellerId(int sellerId) {
        List<PhysicalProductRequestDAO> allPhysicalProductRequests = physicalProductRequestRepository.findBySellerId(sellerId);
        List<PhysicalProductRequestDAO> pendingPhysicalProducts = new ArrayList<>(allPhysicalProductRequests.size());

        for (PhysicalProductRequestDAO productRequest : allPhysicalProductRequests)
            if(ProductStatus.PENDING.name().equals(productRequest.getStatus()))
                pendingPhysicalProducts.add(productRequest);

        return pendingPhysicalProducts;
    }

    public List<DigitalProductRequestDAO> getAllPendingDigitalProductRequestsBySellerId(int sellerId) {
        List<DigitalProductRequestDAO> allDigitalProductRequests = digitalProductRequestRepository.findBySellerId(sellerId);
        List<DigitalProductRequestDAO> pendingDigitalProductRequests = new ArrayList<>(allDigitalProductRequests.size());
        
        for (DigitalProductRequestDAO productRequest : allDigitalProductRequests)
            if(productRequest.getStatus().equals(ProductStatus.PENDING.name()))
                pendingDigitalProductRequests.add(productRequest);

        return pendingDigitalProductRequests;
    }

    public boolean deletePhysicalProductRequestBySellerIdAndProductId(int sellerId, int productId){
        long numberOfDeletedProducts = physicalProductRequestRepository.deleteByIdAndSellerId(productId, sellerId);
        return numberOfDeletedProducts != 0;
    }

    public boolean deleteDigitalProductRequestBySellerIdAndProductId(int sellerId, int productId) {
        long numberOfDeletedProducts = digitalProductRequestRepository.deleteByIdAndSellerId(productId, sellerId);
        return numberOfDeletedProducts != 0;
    }

    public boolean updateDigitalProductRequestByProductId(int productId, ProductPatchDTO patch) {
        int numberOfUpdatedProducts = digitalProductRequestRepository.updateById(productId, patch);
        return numberOfUpdatedProducts != 0;
    }

    public boolean updatePhysicalProductRequestByProductId(int productId, ProductPatchDTO patch) {
        int numberOfUpdatedProducts = physicalProductRequestRepository.updateById(productId, patch);
        return numberOfUpdatedProducts != 0;
    }

}