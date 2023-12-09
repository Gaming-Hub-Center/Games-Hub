package com.gameshub.service;

import com.gameshub.controller.DTO.DigitalProductDTO;
import com.gameshub.controller.DTO.PhysicalProductDTO;
import com.gameshub.controller.DTO.ProductBriefDTO;
import com.gameshub.exception.ResourceNotFoundException;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalImageDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.repository.DigitalImageRepository;
import com.gameshub.repository.DigitalProductRepository;
import com.gameshub.repository.PhysicalImageRepository;
import com.gameshub.repository.PhysicalProductRepository;
import com.gameshub.utils.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void save(PhysicalProductDAO physicalProductDAO, List<PhysicalImageDAO> list) {
        physicalProductRepository.save(physicalProductDAO);
        for (PhysicalImageDAO imageDAO: list) {
            imageDAO.setProduct_id(physicalProductDAO.getId());
            physicalImageRepository.save(imageDAO);
        }
    }

    public void save(PhysicalProductDAO physicalProductDAO) {
        physicalProductRepository.save(physicalProductDAO);
    }

    /*public List<PhysicalProductDTO> searchByTitle(String key) {
        List<PhysicalProductDAO> productsDAOs = physicalProductRepository.findAllByTitleContainingIgnoreCase(key).orElse(null);
        if(productsDAOs == null) return null;
        List<PhysicalProductDTO> productDTOS = new ArrayList<>();
        for(PhysicalProductDAO productDAO: productsDAOs) {
            List<byte[]> imagesList = physicalImageRepository.findAllByProduct_id(productDAO.getId()).orElse(null);
            PhysicalProductDTO productDTO = productMapper.toPhysicalProductDTO(productDAO);
            productDTO.setImages(imagesList);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }*/

    public List<ProductBriefDTO> searchByTitle(String key) {
        List<ProductBriefDTO> productDTOs = physicalProductRepository.findAllByTitleContainingIgnoreCase(key).orElse(null);
        if(productDTOs == null || productDTOs.isEmpty()) return productDTOs;
        for(ProductBriefDTO productDTO: productDTOs) {
            List<byte[]> images = physicalImageRepository.findAllByProduct_id(productDTO.getId()).orElse(null);
            if(images == null || images.isEmpty()) continue;
            productDTO.setImage(images.get(0));
        }
        return productDTOs;
    }

}
