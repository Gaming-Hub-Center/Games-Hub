package com.gameshub.service.admin;

import com.gameshub.exception.*;
import com.gameshub.model.product.*;
import com.gameshub.model.request.*;
import com.gameshub.model.request.image.DigitalProductRequestImage;
import com.gameshub.model.request.image.PhysicalProductRequestImage;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.product.*;
import com.gameshub.repository.request.*;
import com.gameshub.repository.user.SellerRepository;
import com.sun.jdi.request.InvalidRequestStateException;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.*;
import org.springframework.stereotype.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DigitalProductApprovalStrategy implements ProductApprovalStrategy {
    private final DigitalProductRequestRepository digitalProductRequestRepository;
    private final DigitalProductRepository digitalProductRepository;
    private final SellerRepository sellerRepository;
    private final DigitalProductRequestImageRepository digitalProductRequestImageRepository;
    private final DigitalImageRepository digitalImageRepository;

    @Override
    @Transactional
    public void approveAndCreateProduct(int requestId) {
        DigitalProductRequestDAO request = fetchAndValidateRequest(requestId);
        validateSellerExists(request.getSeller());
        request.setStatus("Approved");
        request.setPostDate(LocalDate.now());
        DigitalProductDAO newProduct = mapToProductDAO(request);
        digitalProductRequestRepository.save(request);
        DigitalProductDAO savedProduct = digitalProductRepository.save(newProduct);

        List<DigitalProductRequestImage> requestImages = digitalProductRequestImageRepository.findByDigitalProductRequest_Id(requestId);
        // Map and save the images to the image repository
        for (DigitalProductRequestImage requestImage : requestImages) {
            DigitalImageDAO newImageDAO = new DigitalImageDAO();
            newImageDAO.setUrl(requestImage.getImageUrl());
            newImageDAO.setProduct_id(savedProduct.getId());
            digitalImageRepository.save(newImageDAO);
        }
    }

    private void validateSellerExists(SellerDAO seller) {
        if(seller == null) {
            throw new NullPointerException("No Seller");
        }

        if (!sellerRepository.existsById(seller.getId())) {
            throw new ResourceNotFoundException("Seller not found with ID: " + seller.getId());
        }
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