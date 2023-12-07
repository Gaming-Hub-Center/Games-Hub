package com.gameshub.service.request;

import com.gameshub.controller.DTO.request.*;
import com.gameshub.model.request.*;
import com.gameshub.repository.request.*;
import com.gameshub.utils.ProductRequestMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class ProductRequestManager {

    @Autowired
    private PhysicalProductRequestRepository physicalProductRequestRepository;

    @Autowired
    private DigitalProductRequestRepository digitalProductRequestRepository;


    @Autowired
    private ProductRequestMapper productRequestMapper;


    public void saveProductRequest(ProductRequestDTO productRequestDTO) {
        if (productRequestDTO instanceof PhysicalProductRequestDTO) {
            PhysicalProductRequestDAO physicalProductRequestDAO = productRequestMapper.toDAO((PhysicalProductRequestDTO) productRequestDTO);
            physicalProductRequestRepository.save(physicalProductRequestDAO);
        } else if (productRequestDTO instanceof DigitalProductRequestDTO) {
            DigitalProductRequestDAO digitalProductRequestDAO = productRequestMapper.toDAO((DigitalProductRequestDTO) productRequestDTO);
            digitalProductRequestRepository.save(digitalProductRequestDAO);
        }
    }

}
