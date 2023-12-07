package com.gameshub.utils;

import com.gameshub.controller.DTO.product.DigitalProductDTO;
import com.gameshub.controller.DTO.product.PhysicalProductDTO;
import com.gameshub.controller.DTO.request.DigitalProductRequestDTO;
import com.gameshub.controller.DTO.request.PhysicalProductRequestDTO;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapper {

    @Autowired
    ProductMapper productMapper;

    public PhysicalProductRequestDTO toDTO (PhysicalProductRequestDAO physicalProductRequestDAO) {
        if (physicalProductRequestDAO == null) {
            throw new IllegalArgumentException("The provided PhysicalProductRequestDAO is null");
        }

        PhysicalProductRequestDTO physicalProductRequestDTO = new PhysicalProductRequestDTO();
        PhysicalProductDTO physicalProductDTO = productMapper.toProductDTO(physicalProductRequestDAO.getProduct());

        physicalProductRequestDTO.setPhysicalProductDTO(physicalProductDTO);
        physicalProductRequestDTO.setRequestId(physicalProductRequestDAO.getRequestId());
        physicalProductRequestDTO.setDateReceived(physicalProductRequestDAO.getDateReceived());
        physicalProductRequestDTO.setStatus(physicalProductRequestDAO.getStatus());

        physicalProductRequestDTO.setProductType("PHYSICAL");
        physicalProductRequestDTO.setRequestType(physicalProductRequestDAO.getRequestType());

        return physicalProductRequestDTO;

    }

    public DigitalProductRequestDTO toDTO (DigitalProductRequestDAO digitalProductRequestDAO) {
        // Check if the DAO object is null
        if (digitalProductRequestDAO == null) {
            throw new IllegalArgumentException("The provided DigitalProductRequestDAO is null");
        }

        // Create a new DTO instance
        DigitalProductRequestDTO digitalProductRequestDTO = new DigitalProductRequestDTO();
        DigitalProductDTO digitalProductDTO = productMapper.toProductDTO(digitalProductRequestDAO.getProduct());

        digitalProductRequestDTO.setDigitalProductDTO(digitalProductDTO);
        digitalProductRequestDTO.setRequestId(digitalProductRequestDAO.getRequestId());
        digitalProductRequestDTO.setStatus(digitalProductRequestDAO.getStatus());
        digitalProductRequestDTO.setDateReceived(digitalProductRequestDAO.getDateReceived());
        digitalProductRequestDTO.setProductType("DIGITAL");
        digitalProductRequestDTO.setRequestType(digitalProductRequestDAO.getRequestType());

        return digitalProductRequestDTO;
    }


    public PhysicalProductRequestDAO toDAO (PhysicalProductRequestDTO physicalProductRequestDTO) {
        if (physicalProductRequestDTO == null) {
            throw new IllegalArgumentException("PhysicalProductRequestDTO is null");
        }

        PhysicalProductRequestDAO physicalProductRequestDAO = new PhysicalProductRequestDAO();
        PhysicalProductDAO physicalProductDAO = productMapper.toProductDAO(physicalProductRequestDTO.getPhysicalProductDTO());


        physicalProductRequestDAO.setProduct(physicalProductDAO);
        physicalProductRequestDAO.setRequestId(physicalProductRequestDTO.getRequestId());
        physicalProductRequestDAO.setStatus(physicalProductRequestDTO.getStatus());
        physicalProductRequestDAO.setDateReceived(physicalProductRequestDTO.getDateReceived());
        physicalProductRequestDAO.setRequestType(physicalProductRequestDTO.getRequestType());

        return physicalProductRequestDAO;
    }

    public DigitalProductRequestDAO toDAO (DigitalProductRequestDTO digitalProductRequestDTO) {
        if (digitalProductRequestDTO == null) {
            throw new IllegalArgumentException("DigitalProductRequestDTO is null");
        }

        DigitalProductRequestDAO digitalProductRequestDAO = new DigitalProductRequestDAO();
        DigitalProductDAO digitalProductDAO = productMapper.toProductDAO(digitalProductRequestDTO.getDigitalProductDTO());

        digitalProductRequestDAO.setProduct(digitalProductDAO);
        digitalProductRequestDAO.setRequestId(digitalProductRequestDTO.getRequestId());
        digitalProductRequestDAO.setStatus(digitalProductRequestDTO.getStatus());
        digitalProductRequestDAO.setDateReceived(digitalProductRequestDTO.getDateReceived());
        digitalProductRequestDAO.setRequestType(digitalProductRequestDTO.getRequestType());

        return digitalProductRequestDAO;
    }
}
