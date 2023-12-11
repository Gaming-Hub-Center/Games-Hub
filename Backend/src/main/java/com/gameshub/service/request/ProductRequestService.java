package com.gameshub.service.request;

import com.gameshub.controller.DTO.request.*;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.product.ProductDAO;
import com.gameshub.model.request.*;
import com.gameshub.repository.request.*;
import com.gameshub.utils.ProductRequestMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.ArrayList;
import java.util.List;

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

    public List<PhysicalProductDAO> getAllPendingPhysicalProductsBySellerID(int sellerID) {
        List<PhysicalProductRequestDAO> allPhysicalProductRequests = physicalProductRequestRepository.findBySellerId(sellerID);
        List<PhysicalProductDAO> pendingPhysicalProducts = new ArrayList<PhysicalProductDAO>(allPhysicalProductRequests.size());

        for (PhysicalProductRequestDAO productRequest : allPhysicalProductRequests){
            if(productRequest.getStatus().equals("Pending"))
                pendingPhysicalProducts.add(productRequest.getProduct());
        }

        return pendingPhysicalProducts;
    }

    public List<DigitalProductDAO> getAllPendingDigitalProductsBySellerID(int sellerID) {
        List<DigitalProductRequestDAO> allDigitalProductRequests = digitalProductRequestRepository.findBySellerId(sellerID);
        List<DigitalProductDAO> pendingDigitalProductRequests = new ArrayList<DigitalProductDAO>(allDigitalProductRequests.size());

        for (DigitalProductRequestDAO productRequest : allDigitalProductRequests){
            if(productRequest.getStatus().equals("Pending"))
                pendingDigitalProductRequests.add(productRequest.getProduct());
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

}