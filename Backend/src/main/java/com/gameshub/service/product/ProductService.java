package com.gameshub.service.product;

import com.gameshub.controller.DTO.DigitalProductDTO;
import com.gameshub.controller.DTO.PhysicalProductDTO;
import com.gameshub.controller.DTO.ProductBriefDTO;
import com.gameshub.controller.DTO.request.*;
import com.gameshub.exception.ResourceNotFoundException;
import com.gameshub.model.product.*;
import com.gameshub.repository.product.*;
import com.gameshub.utils.ProductMapper;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final PhysicalProductRepository physicalProductRepository;
    private final DigitalProductRepository digitalProductRepository;
    private final PhysicalImageRepository physicalImageRepository;
    private final DigitalImageRepository digitalImageRepository;
    private final ProductMapper productMapper;

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

    public PhysicalProductDTO getPhysicalByID(int id) {
        PhysicalProductDAO productDAO = physicalProductRepository.findById(id).orElse(null);
        PhysicalProductDTO productDTO = productMapper.toPhysicalProductDTO(productDAO);
        if(productDAO != null) {
            List<String> list = physicalImageRepository.findAllByProduct_id(id).orElse(null);
            productDTO.setUrls(list);
        } else {
            throw new ResourceNotFoundException("Product with ID: " + id + " is not found!");
        }
        return productDTO;
    }

    public DigitalProductDTO getDigitalByID(int id) {
        DigitalProductDAO productDAO = digitalProductRepository.findById(id).orElse(null);
        DigitalProductDTO productDTO = productMapper.toDigitalProductDTO(productDAO);
        if(productDAO != null) {
            List<String> list = digitalImageRepository.findAllByProduct_id(id).orElse(null);
            productDTO.setUrls(list);
        } else {
            throw new ResourceNotFoundException("Product with ID: " + id + " is not found!");
        }
        return productDTO;
    }

    public List<ProductBriefDTO> searchPhysicalByTitle(String key) {
        List<ProductBriefDTO> productDTOs = physicalProductRepository.findAllByTitleContainingIgnoreCase(key).orElse(null);
        if(productDTOs == null || productDTOs.isEmpty()) return productDTOs;
        for(ProductBriefDTO productDTO: productDTOs) {
            String url = physicalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setUrl(url);
            productDTO.setProductType("physical");
        }
        return productDTOs;
    }

    public List<ProductBriefDTO> searchDigitalByTitle(String key) {
        List<ProductBriefDTO> productDTOs = digitalProductRepository.findAllByTitleContainingIgnoreCase(key).orElse(null);
        if(productDTOs == null || productDTOs.isEmpty()) return productDTOs;
        for(ProductBriefDTO productDTO: productDTOs) {
            String url = digitalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setUrl(url);
            productDTO.setProductType("digital");
        }
        return productDTOs;
    }

    public List<ProductBriefDTO> filterPhysical(float lowerBound, float upperBound, String category) {
        category = (category == null) ? null : category.toLowerCase();
        List<ProductBriefDTO> productDTOs = physicalProductRepository.filterPhysical(lowerBound, upperBound, category);
        for(ProductBriefDTO productDTO: productDTOs) {
            String url = physicalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setUrl(url);
            productDTO.setProductType("physical");
        }
        return productDTOs;
    }

    public List<ProductBriefDTO> filterDigital(float lowerBound, float upperBound, String category) {
        category = (category == null) ? null : category.toLowerCase();
        List<ProductBriefDTO> productDTOs = digitalProductRepository.filterPhysical(lowerBound, upperBound, category);
        for(ProductBriefDTO productDTO: productDTOs) {
            String url = digitalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setUrl(url);
            productDTO.setProductType("digital");
        }
        return productDTOs;
    }

    public List<ProductBriefDTO> getAllPhysical() {
        List<ProductBriefDTO> productDTOs = physicalProductRepository.findAllProducts().orElse(null);
        for(ProductBriefDTO productDTO: productDTOs) {
            String url = physicalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setUrl(url);
            productDTO.setProductType("physical");
        }
        return productDTOs;
    }

    public List<ProductBriefDTO> getAllDigital() {
        List<ProductBriefDTO> productDTOs = digitalProductRepository.findAllProducts().orElse(null);
        for(ProductBriefDTO productDTO: productDTOs) {
            String url = digitalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setUrl(url);
            productDTO.setProductType("digital");
        }
        return productDTOs;
    }

    public List<ProductBriefDTO> sortPhysical(boolean ascending) {
        List<ProductBriefDTO> productDTOs = physicalProductRepository.getOrderedByPrice().orElse(null);
        if(productDTOs == null || productDTOs.isEmpty()) return productDTOs;
        for(ProductBriefDTO productDTO: productDTOs) {
            String url = physicalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setUrl(url);
            productDTO.setProductType("physical");
        }
        return ascending ? productDTOs : productDTOs.reversed();
    }

    public List<ProductBriefDTO> sortDigital(boolean ascending) {
        List<ProductBriefDTO> productDTOs = digitalProductRepository.getOrderedByPrice().orElse(null);
        if(productDTOs == null || productDTOs.isEmpty()) return productDTOs;
        for(ProductBriefDTO productDTO: productDTOs) {
            String url = digitalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setUrl(url);
            productDTO.setProductType("digital");
        }
        return ascending ? productDTOs : productDTOs.reversed();
    }

//    public void save(PhysicalProductDAO physicalProductDAO, List<PhysicalImageDAO> list) {
//        physicalProductRepository.save(physicalProductDAO);
//        for (PhysicalImageDAO imageDAO: list) {
//            imageDAO.setProduct_id(physicalProductDAO.getId());
//            physicalImageRepository.save(imageDAO);
//        }
//    }
//
//    public void save(DigitalProductDAO digitalProductDAO, List<DigitalImageDAO> list) {
//        digitalProductRepository.save(digitalProductDAO);
//        for (DigitalImageDAO imageDAO: list) {
//            imageDAO.setProduct_id(digitalProductDAO.getId());
//            digitalImageRepository.save(imageDAO);
//        }
//    }

//    public void savePhysicalImage(String url, int productId) {
//        physicalImageRepository.save(new PhysicalImageDAO(url, productId));
//    }
//
//    public void saveDigitalImage(String url, int productId) {
//        digitalImageRepository.save(new DigitalImageDAO(url, productId));
//    }

    public void decreasePhysicalProductCount(int productID, int count) {
        PhysicalProductDAO productDAO = getPhysicalProductByProductID(productID);
        productDAO.setCount(productDAO.getCount() - count);
        physicalProductRepository.save(productDAO);
    }

    public void decreaseDigitalProductCount(int productID, int count) {
        DigitalProductDAO productDAO = getDigitalProductByProductID(productID);
        productDAO.setCount(productDAO.getCount() - count);
        digitalProductRepository.save(productDAO);
    }

}