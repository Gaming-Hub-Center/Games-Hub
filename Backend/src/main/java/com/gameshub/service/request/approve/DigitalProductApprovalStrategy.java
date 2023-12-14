package com.gameshub.service.request.approve;

import com.gameshub.exception.*;
import com.gameshub.model.product.*;
import com.gameshub.model.request.*;
import com.gameshub.repository.product.*;
import com.gameshub.repository.request.*;
import lombok.*;
import org.springframework.transaction.annotation.*;
import org.springframework.stereotype.*;

@RequiredArgsConstructor
@Component
public class DigitalProductApprovalStrategy implements ProductApprovalStrategy {

    private final DigitalProductRequestRepository digitalProductRequestRepository;
    private final DigitalProductRepository digitalProductRepository;

    @Override
    @Transactional
    public void approveAndCreateProduct(int requestId) {
        DigitalProductRequestDAO request = fetchAndValidateRequest(requestId);
        request.setStatus("Approved");
        DigitalProductDAO newProduct = mapToProductDAO(request);
        digitalProductRepository.save(newProduct);
    }

    @Override
    @Transactional
    public void approvedAndUpdateProduct(int requestId, int productId) {
        DigitalProductRequestDAO request = fetchAndValidateRequest(requestId);
        request.setStatus("Approved");

        DigitalProductDAO product = digitalProductRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Digital product not found with ID: " + productId));

        mapToProductDAO(request, product);
        digitalProductRepository.save(product);
    }

    private DigitalProductRequestDAO fetchAndValidateRequest(int requestId) {
        return digitalProductRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Digital product request not found with ID: " + requestId));
    }

    private DigitalProductDAO mapToProductDAO(DigitalProductRequestDAO request) {
        DigitalProductDAO product = new DigitalProductDAO();
        copyRequestToProduct(request, product);
        return product;
    }

    private void mapToProductDAO(DigitalProductRequestDAO request, DigitalProductDAO product) {
        copyRequestToProduct(request, product);
    }

    private void copyRequestToProduct(DigitalProductRequestDAO request, DigitalProductDAO product) {
        // Update the product's fields with information from the request
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCount(request.getCount());
        product.setCategory(request.getCategory());
        try {
            product.setSellerID(request.getSeller().getId());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        product.setCode(request.getCode());
    }

}