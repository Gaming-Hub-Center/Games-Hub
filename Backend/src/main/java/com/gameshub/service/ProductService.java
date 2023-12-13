package com.gameshub.service;

import com.gameshub.controller.DTO.DigitalProductDTO;
import com.gameshub.controller.DTO.PhysicalProductDTO;
import com.gameshub.controller.DTO.ProductBriefDTO;
import com.gameshub.exception.ResourceNotFoundException;
import com.gameshub.model.product.DigitalImageDAO;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalImageDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.repository.DigitalImageRepository;
import com.gameshub.repository.DigitalProductRepository;
import com.gameshub.repository.PhysicalImageRepository;
import com.gameshub.repository.PhysicalProductRepository;
import com.gameshub.utils.ProductMapper;
import jakarta.persistence.EntityManager;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final PhysicalProductRepository physicalProductRepository;
    private final DigitalProductRepository digitalProductRepository;
    private final PhysicalImageRepository physicalImageRepository;
    private final DigitalImageRepository digitalImageRepository;
    private final ProductMapper productMapper;


    public PhysicalProductDTO getPhysicalByID(int id) {
        PhysicalProductDAO productDAO = physicalProductRepository.findById(id).orElse(null);
        PhysicalProductDTO productDTO = productMapper.toPhysicalProductDTO(productDAO);
        if(productDAO != null) {
            List<byte[]> list = physicalImageRepository.findAllByProduct_id(id).orElse(null);
            productDTO.setImages(list);
        } else {
            throw new ResourceNotFoundException("Product with ID: " + id + " is not found!");
        }
        return productDTO;
    }

    public DigitalProductDTO getDigitalByID(int id) {
        DigitalProductDAO productDAO = digitalProductRepository.findById(id).orElse(null);
        DigitalProductDTO productDTO = productMapper.toDigitalProductDTO(productDAO);
        if(productDAO != null) {
            List<byte[]> list = digitalImageRepository.findAllByProduct_id(id).orElse(null);
            productDTO.setImages(list);
        } else {
            throw new ResourceNotFoundException("Product with ID: " + id + " is not found!");
        }
        return productDTO;
    }

    public List<ProductBriefDTO> searchPhysicalByTitle(String key) {
        List<ProductBriefDTO> productDTOs = physicalProductRepository.findAllByTitleContainingIgnoreCase(key).orElse(null);
        if(productDTOs == null || productDTOs.isEmpty()) return productDTOs;
        for(ProductBriefDTO productDTO: productDTOs) {
            byte[] image = physicalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setImage(image);
        }
        return productDTOs;
    }

    public List<ProductBriefDTO> searchDigitalByTitle(String key) {
        List<ProductBriefDTO> productDTOs = digitalProductRepository.findAllByTitleContainingIgnoreCase(key).orElse(null);
        if(productDTOs == null || productDTOs.isEmpty()) return productDTOs;
        for(ProductBriefDTO productDTO: productDTOs) {
            byte[] image = digitalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setImage(image);
        }
        return productDTOs;
    }

    public List<ProductBriefDTO> filterPhysical(float lowerBound, float upperBound, String category) {
        category = (category == null) ? null : category.toLowerCase();
        List<ProductBriefDTO> productDTOs = physicalProductRepository.filterPhysical(lowerBound, upperBound, category);
        if(productDTOs == null || productDTOs.isEmpty()) return productDTOs;
        for(ProductBriefDTO productDTO: productDTOs) {
            byte[] image = physicalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setImage(image);
        }
        return productDTOs;
    }

    public List<ProductBriefDTO> filterDigital(float lowerBound, float upperBound, String category) {
        category = (category == null) ? null : category.toLowerCase();
        List<ProductBriefDTO> productDTOs = digitalProductRepository.filterPhysical(lowerBound, upperBound, category);
        if(productDTOs == null || productDTOs.isEmpty()) return productDTOs;
        for(ProductBriefDTO productDTO: productDTOs) {
            byte[] image = digitalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setImage(image);
        }
        return productDTOs;
    }

    public List<ProductBriefDTO> sortPhysical(boolean ascending) {
        List<ProductBriefDTO> productDTOs = physicalProductRepository.getOrderedByPrice().orElse(null);
        if(productDTOs == null || productDTOs.isEmpty()) return productDTOs;
        for(ProductBriefDTO productDTO: productDTOs) {
            byte[] image = physicalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setImage(image);
        }
        return ascending ? productDTOs : productDTOs.reversed();
    }

    public List<ProductBriefDTO> sortDigital(boolean ascending) {
        List<ProductBriefDTO> productDTOs = digitalProductRepository.getOrderedByPrice().orElse(null);
        if(productDTOs == null || productDTOs.isEmpty()) return productDTOs;
        for(ProductBriefDTO productDTO: productDTOs) {
            byte[] image = digitalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setImage(image);
        }
        return ascending ? productDTOs : productDTOs.reversed();
    }

   /* public void save(PhysicalProductDAO physicalProductDAO, List<PhysicalImageDAO> list) {
        physicalProductRepository.save(physicalProductDAO);
        for (PhysicalImageDAO imageDAO: list) {
            imageDAO.setProduct_id(physicalProductDAO.getId());
            physicalImageRepository.save(imageDAO);
        }
    }*/

    /*public void save(DigitalProductDAO digitalProductDAO, List<DigitalImageDAO> list) {
        digitalProductRepository.save(digitalProductDAO);
        for (DigitalImageDAO imageDAO: list) {
            imageDAO.setProduct_id(digitalProductDAO.getId());
            digitalImageRepository.save(imageDAO);
        }
    }*/
}
