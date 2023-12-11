package com.gameshub.service.product;

import com.gameshub.controller.DTO.product.*;
import com.gameshub.model.product.*;
import com.gameshub.repository.product.*;
import com.gameshub.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private PhysicalProductRepository physicalProductRepository;

    @Autowired
    private DigitalProductRepository digitalProductRepository;

    @Autowired
    private ProductMapper productMapper;

    public void saveProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            throw new IllegalArgumentException("ProductDTO cannot be null");
        }

        if (productDTO instanceof PhysicalProductDTO) {
            savePhysicalProduct((PhysicalProductDTO) productDTO);
        } else if (productDTO instanceof DigitalProductDTO) {
            saveDigitalProduct((DigitalProductDTO) productDTO);
        } else {
            throw new IllegalArgumentException("Unsupported product type");
        }
    }

    private void savePhysicalProduct(PhysicalProductDTO physicalProductDTO) {
        PhysicalProductDAO physicalProductDAO = productMapper.toProductDAO(physicalProductDTO);
        physicalProductRepository.save(physicalProductDAO);
    }

    private void saveDigitalProduct(DigitalProductDTO digitalProductDTO) {
        DigitalProductDAO digitalProductDAO = productMapper.toProductDAO(digitalProductDTO);
        digitalProductRepository.save(digitalProductDAO);
    }

    public List<ProductDAO> getAllProductsBySellerID(int sellerID) {
        List<? extends ProductDAO> physicalProducts = physicalProductRepository.findBySellerId(sellerID);
        List<? extends ProductDAO> digitalProducts = digitalProductRepository.findBySellerId(sellerID);

        List<ProductDAO> allProducts = new ArrayList<>(physicalProducts.size() + digitalProducts.size());
        allProducts.addAll(physicalProducts);
        allProducts.addAll(digitalProducts);

        return allProducts;
    }

    public List<PhysicalProductDAO> getAllPhysicalProductsBySellerID(int sellerID) {
        return physicalProductRepository.findBySellerId(sellerID);
    }

    public List<DigitalProductDAO> getAllDigitalProductsBySellerID(int sellerID) {
        return digitalProductRepository.findBySellerId(sellerID);
    }

    public DigitalProductDAO getDigitalProductByProductID(int productID){
        return digitalProductRepository.findById(productID);
    }

    public PhysicalProductDAO getPhysicalProductByProductID(int productID){
        return physicalProductRepository.findById(productID);
    }

    public boolean deleteDigitalProductBySellerIdAndProductID(int sellerID, int productId){
        long numberOfDeletedProducts = digitalProductRepository.deleteByIdAndSellerId(productId, sellerID);
        return numberOfDeletedProducts != 0;
    }

    public boolean deletePhysicalProductBySellerIdAndProductID(int sellerID, int productId){
        long numberOfDeletedProducts = physicalProductRepository.deleteByIdAndSellerId(productId, sellerID);
        return numberOfDeletedProducts != 0;
    }

}
