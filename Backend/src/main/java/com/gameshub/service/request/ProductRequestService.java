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
            if (isNotDuplicate(productRequestDTO)) {
                productRequestDAO.setRequestType("Pending");
                physicalProductRequestRepository.save(productRequestDAO);
            }
        } else if (productRequestDTO instanceof DigitalProductRequestDTO) {
            DigitalProductRequestDAO productRequestDAO = productRequestMapper.toDAO((DigitalProductRequestDTO) productRequestDTO);
            if (isNotDuplicate(productRequestDTO)) {
                productRequestDAO.setStatus("Pending");
                digitalProductRequestRepository.save(productRequestDAO);
            }
        } else {
            throw new RuntimeException("Unsupported request type");
        }
    }

    private Boolean isNotDuplicate(ProductRequestDTO productRequestDTO) {
        if (productRequestDTO instanceof PhysicalProductRequestDTO)
            return !physicalProductRequestRepository.existsByDescriptionAndTitleAndSellerIdAndStatus(
                    productRequestDTO.getDescription(),
                    productRequestDTO.getTitle(),
                    productRequestDTO.getSellerId(),
                    "Pending")
                    || physicalProductRequestRepository.existsByDescriptionAndTitleAndSellerIdAndStatus(
                    productRequestDTO.getDescription(),
                    productRequestDTO.getTitle(),
                    productRequestDTO.getSellerId(),
                    "Approved");
        else if (productRequestDTO instanceof DigitalProductRequestDTO)
            return !digitalProductRequestRepository.existsByDescriptionAndTitleAndSellerIdAndStatus(
                    productRequestDTO.getDescription(),
                    productRequestDTO.getTitle(),
                    productRequestDTO.getSellerId(),
                    "Pending")
                    || digitalProductRequestRepository.existsByDescriptionAndTitleAndSellerIdAndStatus(
                    productRequestDTO.getDescription(),
                    productRequestDTO.getTitle(),
                    productRequestDTO.getSellerId(),
                    "Approved");
        else
            throw new ResourceAlreadyFoundException("Duplicate Found");
    }

}