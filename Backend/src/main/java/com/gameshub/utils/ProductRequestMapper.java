package com.gameshub.utils;

import com.gameshub.controller.DTO.request.DigitalProductRequestDTO;
import com.gameshub.controller.DTO.request.PhysicalProductRequestDTO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {

    DigitalProductRequestDAO toDAO (DigitalProductRequestDTO digitalProductRequestDTO);

    DigitalProductRequestDTO toDTO (DigitalProductRequestDAO digitalProductRequestDAO);

    PhysicalProductRequestDAO toDAO (PhysicalProductRequestDTO physicalProductRequestDTO);

    PhysicalProductRequestDTO toDTO (PhysicalProductRequestDTO physicalProductRequestDAO);

}
