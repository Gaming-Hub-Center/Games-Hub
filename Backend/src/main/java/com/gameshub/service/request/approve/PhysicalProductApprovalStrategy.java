package com.gameshub.service.request.approve;

import com.gameshub.exception.*;
import com.gameshub.model.product.*;
import com.gameshub.model.request.*;
import com.gameshub.repository.product.*;
import com.gameshub.repository.request.*;
import jakarta.transaction.*;
import lombok.*;
import org.springframework.stereotype.*;

@RequiredArgsConstructor
@Component
public class PhysicalProductApprovalStrategy implements ProductApprovalStrategy{

    private final PhysicalProductRequestRepository physicalProductRequestRepository;
    private final PhysicalProductRepository physicalProductRepository;

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