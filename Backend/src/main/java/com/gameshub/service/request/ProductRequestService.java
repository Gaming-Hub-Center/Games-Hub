package com.gameshub.service.request;

import com.gameshub.controller.DTO.ProductPatchDTO;
import com.gameshub.controller.DTO.request.*;
import com.gameshub.model.request.*;
import com.gameshub.repository.request.*;
import com.gameshub.utils.ProductRequestMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductRequestService {

    @Autowired
    private PhysicalProductRequestRepository physicalProductRequestRepository;

    @Autowired
    private DigitalProductRequestRepository digitalProductRequestRepository;


    @Autowired
    private ProductRequestMapper productRequestMapper;

    public void saveProductRequest(ProductRequestDTO productRequestDTO) {
        if (productRequestDTO instanceof PhysicalProductRequestDTO) {
            PhysicalProductRequestDAO productRequestDAO = productRequestMapper.toDAO((PhysicalProductRequestDTO) productRequestDTO);
            physicalProductRequestRepository.save(productRequestDAO);
        } else if (productRequestDTO instanceof DigitalProductRequestDTO) {
            DigitalProductRequestDAO productRequestDAO = productRequestMapper.toDAO((DigitalProductRequestDTO) productRequestDTO);
            digitalProductRequestRepository.save(productRequestDAO);
        }
    }

    public DigitalProductRequestDAO getDigitalProductByProductID(int productID){
        Optional<DigitalProductRequestDAO> foundProduct = digitalProductRequestRepository.findById(productID);

        if(foundProduct.isPresent())
            return foundProduct.get();

        return new DigitalProductRequestDAO();
    }

    public PhysicalProductRequestDAO getPhysicalProductByProductID(int productID){
        Optional<PhysicalProductRequestDAO> foundProduct = physicalProductRequestRepository.findById(productID);

        if(foundProduct.isPresent())
            return foundProduct.get();

        return new PhysicalProductRequestDAO();
    }

    public List<PhysicalProductRequestDAO> getAllPendingPhysicalProductsBySellerID(int sellerID) {
        List<PhysicalProductRequestDAO> allPhysicalProductRequests = physicalProductRequestRepository.findBySellerId(sellerID);
        List<PhysicalProductRequestDAO> pendingPhysicalProducts = new ArrayList<PhysicalProductRequestDAO>(allPhysicalProductRequests.size());

        for (PhysicalProductRequestDAO productRequest : allPhysicalProductRequests){
            if(productRequest.getStatus().equals("Pending"))
                pendingPhysicalProducts.add(productRequest);
        }

        return pendingPhysicalProducts;
    }

    public List<DigitalProductRequestDAO> getAllPendingDigitalProductsBySellerID(int sellerID) {
        List<DigitalProductRequestDAO> allDigitalProductRequests = digitalProductRequestRepository.findBySellerId(sellerID);
        List<DigitalProductRequestDAO> pendingDigitalProductRequests = new ArrayList<DigitalProductRequestDAO>(allDigitalProductRequests.size());

        for (DigitalProductRequestDAO productRequest : allDigitalProductRequests){
            if(productRequest.getStatus().equals("Pending"))
                pendingDigitalProductRequests.add(productRequest);
        }

        return pendingDigitalProductRequests;
    }

    public boolean deleteDigitalProductBySellerIdAndProductID(int sellerID, int productId){
        long numberOfDeletedProducts = digitalProductRequestRepository.deleteByIdAndSellerId(productId, sellerID);
        return numberOfDeletedProducts != 0;
    }

    public boolean deletePhysicalProductBySellerIdAndProductID(int sellerID, int productId){
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