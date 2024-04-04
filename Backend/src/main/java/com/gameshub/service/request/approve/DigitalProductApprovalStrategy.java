package com.gameshub.service.request.approve;

<<<<<<< Updated upstream:Backend/src/main/java/com/gameshub/service/request/approve/DigitalProductApprovalStrategy.java
import com.gameshub.exception.*;
import com.gameshub.model.product.*;
import com.gameshub.model.request.*;
import com.gameshub.repository.product.*;
import com.gameshub.repository.request.*;
import lombok.*;
import org.springframework.transaction.annotation.*;
import org.springframework.stereotype.*;
=======
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
>>>>>>> Stashed changes:Backend/src/main/java/com/gameshub/service/admin/DigitalProductApprovalStrategy.java

@RequiredArgsConstructor
@Component
public class DigitalProductApprovalStrategy implements ProductApprovalStrategy {

    private final DigitalProductRequestRepository digitalProductRequestRepository;
    private final DigitalProductRepository digitalProductRepository;
<<<<<<< Updated upstream:Backend/src/main/java/com/gameshub/service/request/approve/DigitalProductApprovalStrategy.java
=======
    private final DigitalProductRequestImageRepository digitalProductRequestImageRepository;
    private final DigitalImageRepository digitalImageRepository;
    private final UserService userService;
>>>>>>> Stashed changes:Backend/src/main/java/com/gameshub/service/admin/DigitalProductApprovalStrategy.java

    @Override
    @Transactional
    public void approveAndCreateProduct(int requestId) {
        DigitalProductRequestDAO request = fetchAndValidateRequest(requestId);
<<<<<<< Updated upstream:Backend/src/main/java/com/gameshub/service/request/approve/DigitalProductApprovalStrategy.java
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
=======
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
>>>>>>> Stashed changes:Backend/src/main/java/com/gameshub/service/admin/DigitalProductApprovalStrategy.java
    }

    private DigitalProductRequestDAO fetchAndValidateRequest(int requestId) {
        return digitalProductRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Digital product request not found with ID: " + requestId));
<<<<<<< Updated upstream:Backend/src/main/java/com/gameshub/service/request/approve/DigitalProductApprovalStrategy.java
=======

        if (!ProductStatus.PENDING.name().equalsIgnoreCase(request.getStatus()))
            throw new InvalidRequestStateException("Request must be in Pending status, but was: " + request.getStatus());

        return request;
>>>>>>> Stashed changes:Backend/src/main/java/com/gameshub/service/admin/DigitalProductApprovalStrategy.java
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
<<<<<<< Updated upstream:Backend/src/main/java/com/gameshub/service/request/approve/DigitalProductApprovalStrategy.java
        try {
            product.setSellerID(request.getSeller().getId());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        product.setCode(request.getCode());
=======
        product.setPostDate(LocalDate.now());
        product.setSellerId(request.getSellerId());
>>>>>>> Stashed changes:Backend/src/main/java/com/gameshub/service/admin/DigitalProductApprovalStrategy.java
    }

}