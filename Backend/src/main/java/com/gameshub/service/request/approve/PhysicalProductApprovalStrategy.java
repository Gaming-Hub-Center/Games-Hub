package com.gameshub.service.request.approve;

import com.gameshub.exception.*;
import com.gameshub.model.product.*;
import com.gameshub.model.request.*;
import com.gameshub.repository.product.*;
import com.gameshub.repository.request.*;
import com.sun.jdi.request.InvalidRequestStateException;
import jakarta.transaction.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class PhysicalProductApprovalStrategy implements ProductApprovalStrategy{

    @Autowired
    private PhysicalProductRequestRepository physicalProductRequestRepository;
    @Autowired
    private PhysicalProductRepository physicalProductRepository;

    @Override
    @Transactional
    public int approveAndCreateProduct(int requestId) {
        PhysicalProductRequestDAO request = new PhysicalProductRequestDAO();
        try {
             fetchAndValidateRequest(requestId);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return HttpStatus.NOT_FOUND.value();
        } catch (InvalidRequestStateException e) {
            System.out.println(e.getMessage());
            return HttpStatus.EXPECTATION_FAILED.value();
        }
        request.setStatus("Approved");
        request.setPostDate(LocalDate.now());
        PhysicalProductDAO newProduct = mapToProductDAO(request);
        physicalProductRepository.save(newProduct);
        return HttpStatus.OK.value();
    }

    private PhysicalProductRequestDAO fetchAndValidateRequest(int requestId) {
        PhysicalProductRequestDAO request = physicalProductRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Physical product request not found with ID: " + requestId));

        if (!"Pending".equalsIgnoreCase(request.getStatus())) {
            throw new InvalidRequestStateException("Request must be in Pending status, but was: " + request.getStatus());
        }

        return request;
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
        product.setCategory(request.getCategory());
        product.setPostDate(LocalDate.now());
        try {
            product.setSellerID(request.getSeller().getId());
        } catch (NullPointerException e) {
            System.out.println("NULL :(");
        }
        product.setCategory(request.getCategory());
    }

}