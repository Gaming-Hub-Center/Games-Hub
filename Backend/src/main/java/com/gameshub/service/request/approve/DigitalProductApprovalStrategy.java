package com.gameshub.service.request.approve;

import com.gameshub.exception.ResourceNotFoundException;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.repository.product.DigitalProductRepository;
import com.gameshub.repository.request.DigitalProductRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;

// ... other necessary imports ...

@Component
public class DigitalProductApprovalStrategy implements ProductApprovalStrategy {

    @Autowired
    private DigitalProductRequestRepository digitalProductRequestRepository;

    @Autowired
    private DigitalProductRepository digitalProductRepository;

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
            product.setSeller(request.getSeller());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        product.setCode(request.getCode());
    }
}