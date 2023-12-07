package com.gameshub.service;

import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.product.ProductDAO;
import com.gameshub.repository.PhysicalProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final PhysicalProductRepository physicalProductRepository;

    public PhysicalProductDAO getByID(int id) {
        Optional<PhysicalProductDAO> productDAO = physicalProductRepository.findById(id);
        return productDAO.orElse(null);
    }
}
