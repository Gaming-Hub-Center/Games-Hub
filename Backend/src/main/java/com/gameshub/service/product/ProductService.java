package com.gameshub.service.product;

import com.gameshub.controller.DTO.ProductPatchDTO;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.product.ProductDAO;
import com.gameshub.repository.product.DigitalProductRepository;
import com.gameshub.repository.product.PhysicalProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private PhysicalProductRepository physicalProductRepository;

    @Autowired
    private DigitalProductRepository digitalProductRepository;

    public List<PhysicalProductDAO> getAllPhysicalProductsBySellerID(int sellerID) {
        return physicalProductRepository.findBySellerId(sellerID);
    }

    public List<DigitalProductDAO> getAllDigitalProductsBySellerID(int sellerID) {
        return digitalProductRepository.findBySellerId(sellerID);
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
        long numberOfDeletedProducts = digitalProductRepository.deleteByIdAndSellerId(productId, sellerID);
        return numberOfDeletedProducts != 0;
    }

    public boolean deletePhysicalProductBySellerIdAndProductID(int sellerID, int productId){
        long numberOfDeletedProducts = physicalProductRepository.deleteByIdAndSellerId(productId, sellerID);
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
