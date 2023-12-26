package com.gameshub.service.request;

import com.gameshub.controller.DTO.request.*;
import com.gameshub.exception.ResourceAlreadyFoundException;
import com.gameshub.model.request.*;
import com.gameshub.repository.request.*;
import com.gameshub.utils.ProductRequestMapper;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ProductRequestService {

    public static final String PENDING = "Pending";
    private final PhysicalProductRequestRepository physicalProductRequestRepository;
    private final DigitalProductRequestRepository digitalProductRequestRepository;
    private final ProductRequestMapper productRequestMapper;

    public void saveProductRequest(ProductRequestDTO productRequestDTO) {
        if (productRequestDTO instanceof PhysicalProductRequestDTO) {
            PhysicalProductRequestDAO productRequestDAO = productRequestMapper.toDAO((PhysicalProductRequestDTO) productRequestDTO);
            if (isNotDuplicate(productRequestDTO)) {
                productRequestDAO.setRequestType(PENDING);
                physicalProductRequestRepository.save(productRequestDAO);
            } else
                throw new ResourceAlreadyFoundException("Duplicate Found");
        } else if (productRequestDTO instanceof DigitalProductRequestDTO) {
            DigitalProductRequestDAO productRequestDAO = productRequestMapper.toDAO((DigitalProductRequestDTO) productRequestDTO);
            if (isNotDuplicate(productRequestDTO)) {
                productRequestDAO.setStatus(PENDING);
                digitalProductRequestRepository.save(productRequestDAO);
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
                    PENDING)
                    && !physicalProductRequestRepository.existsByDescriptionAndTitleAndSellerIdAndStatus(
                    productRequestDTO.getDescription(),
                    productRequestDTO.getTitle(),
                    productRequestDTO.getSellerId(),
                    "approved");
        else if (productRequestDTO instanceof DigitalProductRequestDTO)
            return !digitalProductRequestRepository.existsByDescriptionAndTitleAndSellerIdAndStatus(
                    productRequestDTO.getDescription(),
                    productRequestDTO.getTitle(),
                    productRequestDTO.getSellerId(),
                    PENDING)
                    && !digitalProductRequestRepository.existsByDescriptionAndTitleAndSellerIdAndStatus(
                    productRequestDTO.getDescription(),
                    productRequestDTO.getTitle(),
                    productRequestDTO.getSellerId(),
                    "approved");
        return false;
    }

    public DigitalProductRequestDAO getDigitalProductRequestByProductID(int productID){
        Optional<DigitalProductRequestDAO> foundProduct = digitalProductRequestRepository.findById(productID);

        return foundProduct.orElseGet(DigitalProductRequestDAO::new);

    }

    public PhysicalProductRequestDAO getPhysicalProductRequestByProductID(int productID){
        Optional<PhysicalProductRequestDAO> foundProduct = physicalProductRequestRepository.findById(productID);

        if(foundProduct.isPresent())
            return foundProduct.get();

        return new PhysicalProductRequestDAO();
    }

    public List<PhysicalProductRequestDAO> getAllPendingPhysicalProductRequestsBySellerID(int sellerID) {
        List<PhysicalProductRequestDAO> allPhysicalProductRequests = physicalProductRequestRepository.findBySellerId(sellerID);
        List<PhysicalProductRequestDAO> pendingPhysicalProducts = new ArrayList<PhysicalProductRequestDAO>(allPhysicalProductRequests.size());

        for (PhysicalProductRequestDAO productRequest : allPhysicalProductRequests){
            if(PENDING.equals(productRequest.getStatus()))
                pendingPhysicalProducts.add(productRequest);
        }

        return pendingPhysicalProducts;
    }

    public List<DigitalProductRequestDAO> getAllPendingDigitalProductRequestsBySellerID(int sellerID) {
        List<DigitalProductRequestDAO> allDigitalProductRequests = digitalProductRequestRepository.findBySellerId(sellerID);
        List<DigitalProductRequestDAO> pendingDigitalProductRequests = new ArrayList<DigitalProductRequestDAO>(allDigitalProductRequests.size());
        
        for (DigitalProductRequestDAO productRequest : allDigitalProductRequests){
            if(productRequest.getStatus().equals(PENDING))
                pendingDigitalProductRequests.add(productRequest);
        }

        return pendingDigitalProductRequests;
    }

    public boolean deleteDigitalProductRequestBySellerIdAndProductID(int sellerID, int productId){
        long numberOfDeletedProducts = digitalProductRequestRepository.deleteByIdAndSellerId(productId, sellerID);
        return numberOfDeletedProducts != 0;
    }

    public boolean deletePhysicalProductRequestBySellerIdAndProductID(int sellerID, int productId){
        long numberOfDeletedProducts = physicalProductRequestRepository.deleteByIdAndSellerId(productId, sellerID);
        return numberOfDeletedProducts != 0;
    }

    public boolean updateDigitalProductRequestByProductID(int productId, ProductPatchDTO patch){
        int numberOfUpdatedProducts = digitalProductRequestRepository.updateById(productId, patch);
        return numberOfUpdatedProducts != 0;
    }

    public boolean updatePhysicalProductRequestByProductID(int productId, ProductPatchDTO patch){
        int numberOfUpdatedProducts = physicalProductRequestRepository.updateById(productId, patch);
        return numberOfUpdatedProducts != 0;
    }

}