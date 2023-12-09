package com.gameshub.service.product;

import com.gameshub.controller.DTO.product.*;
import com.gameshub.model.product.*;
import com.gameshub.repository.product.*;
import com.gameshub.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
