package com.gameshub.service.product;

import com.gameshub.controller.DTO.request.*;
import com.gameshub.model.product.*;
import com.gameshub.repository.product.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final PhysicalProductRepository physicalProductRepository;
    private final DigitalProductRepository digitalProductRepository;

    public List<PhysicalProductDAO> getAllPhysicalProductsBySellerID(int sellerID) {
        return physicalProductRepository.findBySellerID(sellerID);
    }

    public List<DigitalProductDAO> getAllDigitalProductsBySellerID(int sellerID) {
        return digitalProductRepository.findBySellerID(sellerID);
    }

    public DigitalProductDAO getDigitalProductByProductID(int productID){
        Optional<DigitalProductDAO> foundProduct = digitalProductRepository.findById(productID);

        if(foundProduct.isPresent())
            return foundProduct.get();

        return new DigitalProductDAO();
    }

    public PhysicalProductDAO getPhysicalProductByProductID(int productID){
        Optional<PhysicalProductDAO> foundProduct = physicalProductRepository.findById(productID);

        if(foundProduct.isPresent())
            return foundProduct.get();

        return new PhysicalProductDAO();
    }

    public boolean deleteDigitalProductBySellerIdAndProductID(int sellerID, int productId){
        long numberOfDeletedProducts = digitalProductRepository.deleteByIdAndSellerID(productId, sellerID);
        return numberOfDeletedProducts != 0;
    }

    public boolean deletePhysicalProductBySellerIdAndProductID(int sellerID, int productId){
        long numberOfDeletedProducts = physicalProductRepository.deleteByIdAndSellerID(productId, sellerID);
        return numberOfDeletedProducts != 0;
    }

    public boolean updateDigitalProductByProductID(int productId, ProductPatchDTO patch){
        int numberOfUpdatedProducts = digitalProductRepository.updateById(productId, patch);
        return numberOfUpdatedProducts != 0;
    }

    public boolean updatePhysicalProductByProductID(int productId, ProductPatchDTO patch){
        int numberOfUpdatedProducts = physicalProductRepository.updateById(productId, patch);
        return numberOfUpdatedProducts != 0;
    }

}
