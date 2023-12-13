package com.gameshub.service.request.approve;

import com.gameshub.exception.ResourceNotFoundException;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.repository.product.PhysicalProductRepository;
import com.gameshub.repository.request.PhysicalProductRequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PhysicalProductApprovalStrategy implements ProductApprovalStrategy{

    @Autowired
    private PhysicalProductRequestRepository physicalProductRequestRepository;

    @Autowired
    private PhysicalProductRepository physicalProductRepository;

    @Override
    @Transactional
    public void approveAndCreateProduct(int requestId) {
        PhysicalProductRequestDAO request = fetchAndValidateRequest(requestId);
        request.setStatus("Approved");
        PhysicalProductDAO newProduct = mapToProductDAO(request);
        physicalProductRepository.save(newProduct);
    }

    @Override
    @Transactional
    public void approvedAndUpdateProduct(int requestId, int productId) {
        PhysicalProductRequestDAO request = fetchAndValidateRequest(requestId);
        request.setStatus("Approved");

        PhysicalProductDAO product = physicalProductRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Physical product not found with ID: " + productId));

        mapToProductDAO(request, product);
        physicalProductRepository.save(product);
    }

    private PhysicalProductRequestDAO fetchAndValidateRequest(int requestId) {
        return physicalProductRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Physical product request not found with ID: " + requestId));
    }

    private PhysicalProductDAO mapToProductDAO(PhysicalProductRequestDAO request) {
        PhysicalProductDAO product = new PhysicalProductDAO();
        copyRequestToProduct(request, product);
        return product;
    }

    private void mapToProductDAO(PhysicalProductRequestDAO request, PhysicalProductDAO product) {
        copyRequestToProduct(request, product);
    }

    private void copyRequestToProduct(PhysicalProductRequestDAO request, PhysicalProductDAO product) {
        // Update the product's fields with information from the request
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCount(request.getCount());
        try {
            product.setSellerID(request.getSeller().getId());
        } catch (NullPointerException e) {
            System.out.println("NULL :(");
        }
        product.setCategory(request.getCategory());
    }
}