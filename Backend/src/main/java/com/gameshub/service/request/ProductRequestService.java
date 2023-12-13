package com.gameshub.service.request;

import com.gameshub.controller.DTO.request.*;
import com.gameshub.exception.ResourceAlreadyFoundException;
import com.gameshub.model.request.*;
import com.gameshub.repository.request.*;
import com.gameshub.utils.ProductRequestMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class ProductRequestService {

    @Autowired
    private PhysicalProductRequestRepository physicalProductRequestRepository;

    @Autowired
    private DigitalProductRequestRepository digitalProductRequestRepository;

    @Autowired
    private ProductRequestMapper productRequestMapper;

    public void saveProductRequest(ProductRequestDTO productRequestDTO) {
        if (productRequestDTO instanceof PhysicalProductRequestDTO) {
            PhysicalProductRequestDAO productRequestDAO = productRequestMapper.toDAO((PhysicalProductRequestDTO) productRequestDTO);
            if (!physicalProductRequestRepository.existsByDescriptionAndTitle(productRequestDAO.getDescription(), productRequestDAO.getTitle())) {
                physicalProductRequestRepository.save(productRequestDAO);
            } else {
                throw new ResourceAlreadyFoundException("Duplicate request found");
            }
        } else if (productRequestDTO instanceof DigitalProductRequestDTO) {
            DigitalProductRequestDAO productRequestDAO = productRequestMapper.toDAO((DigitalProductRequestDTO) productRequestDTO);
            if (!digitalProductRequestRepository.existsByDescriptionAndTitle(productRequestDAO.getDescription(), productRequestDAO.getTitle())) {
                digitalProductRequestRepository.save(productRequestDAO);
            } else {
                throw new ResourceAlreadyFoundException("Duplicate request found");
            }
        } else {
            throw new RuntimeException("Unsupported request type");
        }
    }


}