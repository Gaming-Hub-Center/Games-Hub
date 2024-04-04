package com.gameshub.service.admin;

import com.gameshub.enums.ProductStatus;
import com.gameshub.exception.ResourceNotFoundException;
import com.gameshub.model.product.DigitalImageDAO;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.DigitalProductRequestImage;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.product.DigitalImageRepository;
import com.gameshub.repository.product.DigitalProductRepository;
import com.gameshub.repository.product.DigitalProductRequestImageRepository;
import com.gameshub.repository.product.DigitalProductRequestRepository;
import com.gameshub.service.user.UserService;
import com.sun.jdi.request.InvalidRequestStateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DigitalProductApprovalStrategy implements ProductApprovalStrategy {

    private final DigitalProductRequestRepository digitalProductRequestRepository;
    private final DigitalProductRepository digitalProductRepository;
    private final DigitalProductRequestImageRepository digitalProductRequestImageRepository;
    private final DigitalImageRepository digitalImageRepository;
    private final UserService userService;

    @Override
    @Transactional
    public void approveAndCreateProduct(int requestId) {
        DigitalProductRequestDAO request = fetchAndValidateRequest(requestId);
        validateSellerExists(request.getSellerId());
        request.setStatus(ProductStatus.APPROVED.name());
        request.setPostDate(LocalDate.now());
        DigitalProductDAO newProduct = mapToProductDAO(request);
        digitalProductRequestRepository.save(request);
        DigitalProductDAO savedProduct = digitalProductRepository.save(newProduct);

        List<DigitalProductRequestImage> requestImages = digitalProductRequestImageRepository.findByRequestId(requestId);

        for (DigitalProductRequestImage requestImage : requestImages) {
            DigitalImageDAO newImageDAO = new DigitalImageDAO(requestImage.getImageUrl(), savedProduct.getId());
            digitalImageRepository.save(newImageDAO);
        }
    }

    private void validateSellerExists(int sellerId) {
        SellerDAO seller = userService.getSellerById(sellerId);
        if(seller == null)
            throw new NullPointerException("No Seller");

        if (!userService.userExistsById(seller.getId()))
            throw new ResourceNotFoundException("Seller not found with ID: " + seller.getId());
    }

    private DigitalProductRequestDAO fetchAndValidateRequest(int requestId) {
        DigitalProductRequestDAO request = digitalProductRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Digital product request not found with ID: " + requestId));

        if (!ProductStatus.PENDING.name().equalsIgnoreCase(request.getStatus()))
            throw new InvalidRequestStateException("Request must be in Pending status, but was: " + request.getStatus());

        return request;
    }

    private DigitalProductDAO mapToProductDAO(DigitalProductRequestDAO request) {
        DigitalProductDAO product = new DigitalProductDAO();
        copyRequestToProduct(request, product);
        return product;
    }

    private void copyRequestToProduct(DigitalProductRequestDAO request, DigitalProductDAO product) {
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCount(request.getCount());
        product.setCategory(request.getCategory());
        product.setPostDate(LocalDate.now());
        product.setSellerId(request.getSellerId());
    }

}