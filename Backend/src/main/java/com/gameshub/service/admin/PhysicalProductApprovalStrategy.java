package com.gameshub.service.admin;

import com.gameshub.enums.ProductStatus;
import com.gameshub.exception.ResourceNotFoundException;
import com.gameshub.model.product.PhysicalImageDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestImage;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.product.PhysicalImageRepository;
import com.gameshub.repository.product.PhysicalProductRepository;
import com.gameshub.repository.product.PhysicalProductRequestImageRepository;
import com.gameshub.repository.product.PhysicalProductRequestRepository;
import com.gameshub.service.user.UserService;
import com.sun.jdi.request.InvalidRequestStateException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PhysicalProductApprovalStrategy implements ProductApprovalStrategy{

    private final PhysicalProductRequestRepository physicalProductRequestRepository;
    private final PhysicalProductRepository physicalProductRepository;
    private final PhysicalProductRequestImageRepository physicalProductRequestImageRepository;
    private final PhysicalImageRepository physicalImageRepository;
    private final UserService userService;

    @Override
    @Transactional
    public void approveAndCreateProduct(int requestId) {
        PhysicalProductRequestDAO request = fetchAndValidateRequest(requestId);
        validateSellerExists(request.getSellerId());
        request.setStatus(ProductStatus.APPROVED.name());
        request.setPostDate(LocalDate.now());
        PhysicalProductDAO newProduct = mapToProductDAO(request);
        physicalProductRequestRepository.save(request);
        PhysicalProductDAO savedProduct = physicalProductRepository.save(newProduct);

        List<PhysicalProductRequestImage> requestImages = physicalProductRequestImageRepository.findByRequestId(requestId);

        for (PhysicalProductRequestImage requestImage : requestImages) {
            PhysicalImageDAO newImageDAO = new PhysicalImageDAO(requestImage.getImageUrl(), savedProduct.getId());
            physicalImageRepository.save(newImageDAO);
        }
    }

    private void validateSellerExists(int sellerId) {
        SellerDAO seller = userService.getSellerById(sellerId);
        if(seller == null)
            throw new NullPointerException("No Seller");

        if (!userService.userExistsById(sellerId))
            throw new ResourceNotFoundException("Seller not found with ID: " + seller.getId());
    }

    private PhysicalProductRequestDAO fetchAndValidateRequest(int requestId) {
        PhysicalProductRequestDAO request = physicalProductRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Physical product request not found with ID: " + requestId));

        if (ProductStatus.PENDING.name().equalsIgnoreCase(request.getStatus()))
            throw new InvalidRequestStateException("Request must be in Pending status, but was: " + request.getStatus());

        return request;
    }

    private PhysicalProductDAO mapToProductDAO(PhysicalProductRequestDAO request) {
        PhysicalProductDAO product = new PhysicalProductDAO();
        copyRequestToProduct(request, product);
        return product;
    }

    private void copyRequestToProduct(PhysicalProductRequestDAO request, PhysicalProductDAO product) {
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCount(request.getCount());
        product.setCategory(request.getCategory());
        product.setPostDate(LocalDate.now());
        product.setSellerId(request.getSellerId());
    }

}