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

    private final PhysicalProductRequestRepository physicalProductRequestRepository;
    private final DigitalProductRequestRepository digitalProductRequestRepository;
    private final ProductRequestMapper productRequestMapper;

    public void saveProductRequest(ProductRequestDTO productRequestDTO) {
        if (productRequestDTO instanceof PhysicalProductRequestDTO) {
            PhysicalProductRequestDAO productRequestDAO = productRequestMapper.toDAO((PhysicalProductRequestDTO) productRequestDTO);
            if (isNotDuplicate(productRequestDTO)) {
                productRequestDAO.setRequestType("Pending");
                physicalProductRequestRepository.save(productRequestDAO);
            }
        } else if (productRequestDTO instanceof DigitalProductRequestDTO) {
            DigitalProductRequestDAO productRequestDAO = productRequestMapper.toDAO((DigitalProductRequestDTO) productRequestDTO);
            if (isNotDuplicate(productRequestDTO)) {
                productRequestDAO.setStatus("Pending");
                digitalProductRequestRepository.save(productRequestDAO);
            }
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
                    "Pending")
                    || physicalProductRequestRepository.existsByDescriptionAndTitleAndSellerIdAndStatus(
                    productRequestDTO.getDescription(),
                    productRequestDTO.getTitle(),
                    productRequestDTO.getSellerId(),
                    "Approved");
        else if (productRequestDTO instanceof DigitalProductRequestDTO)
            return !digitalProductRequestRepository.existsByDescriptionAndTitleAndSellerIdAndStatus(
                    productRequestDTO.getDescription(),
                    productRequestDTO.getTitle(),
                    productRequestDTO.getSellerId(),
                    "Pending")
                    || digitalProductRequestRepository.existsByDescriptionAndTitleAndSellerIdAndStatus(
                    productRequestDTO.getDescription(),
                    productRequestDTO.getTitle(),
                    productRequestDTO.getSellerId(),
                    "Approved");
        else
            throw new ResourceAlreadyFoundException("Duplicate Found");
    }

    public DigitalProductRequestDAO getDigitalProductRequestByProductID(int productID){
        Optional<DigitalProductRequestDAO> foundProduct = digitalProductRequestRepository.findById(productID);

        if(foundProduct.isPresent())
            return foundProduct.get();

        return new DigitalProductRequestDAO();
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
            if(productRequest.getStatus().equals("Pending"))
                pendingPhysicalProducts.add(productRequest);
        }

        return pendingPhysicalProducts;
    }

    public List<DigitalProductRequestDAO> getAllPendingDigitalProductRequestsBySellerID(int sellerID) {
        List<DigitalProductRequestDAO> allDigitalProductRequests = digitalProductRequestRepository.findBySellerId(sellerID);
        List<DigitalProductRequestDAO> pendingDigitalProductRequests = new ArrayList<DigitalProductRequestDAO>(allDigitalProductRequests.size());

        for (DigitalProductRequestDAO productRequest : allDigitalProductRequests){
            if(productRequest.getStatus().equals("Pending"))
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