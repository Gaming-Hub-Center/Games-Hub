package com.gameshub.service.request.approve;

import com.gameshub.exception.*;
import com.gameshub.model.product.*;
import com.gameshub.model.request.*;
import com.gameshub.repository.product.*;
import com.gameshub.repository.request.*;
import com.sun.jdi.request.InvalidRequestStateException;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.*;
import org.springframework.stereotype.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class DigitalProductApprovalStrategy implements ProductApprovalStrategy {

    private final DigitalProductRequestRepository digitalProductRequestRepository;

    private final DigitalProductRepository digitalProductRepository;

    @Override
    @Transactional
    public int approveAndCreateProduct(int requestId) {
        DigitalProductRequestDAO request = new DigitalProductRequestDAO();
        try {
            request = fetchAndValidateRequest(requestId);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return HttpStatus.NOT_FOUND.value();
        } catch (
            InvalidRequestStateException e) {
            System.out.println(e.getMessage());
            return HttpStatus.EXPECTATION_FAILED.value();
        }

        request.setStatus("Approved");
        DigitalProductDAO newProduct = mapToProductDAO(request);
        digitalProductRepository.save(newProduct);
        return HttpStatus.OK.value();
    }

    private DigitalProductRequestDAO fetchAndValidateRequest(int requestId) {
        DigitalProductRequestDAO request = digitalProductRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Digital product request not found with ID: " + requestId));

        if (!"Pending".equalsIgnoreCase(request.getStatus())) {
            throw new InvalidRequestStateException("Request must be in Pending status, but was: " + request.getStatus());
        }

        return request;
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
        product.setPostDate(LocalDate.now());
        try {
            product.setSellerID(request.getSeller().getId());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        product.setCode(request.getCode());
    }

}