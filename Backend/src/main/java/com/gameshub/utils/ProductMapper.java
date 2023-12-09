package com.gameshub.utils;

import com.gameshub.controller.DTO.DigitalProductDTO;
import com.gameshub.controller.DTO.PhysicalProductDTO;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    PhysicalProductDTO toPhysicalProductDTO(PhysicalProductDAO physicalProductDAO);
    PhysicalProductDAO toPhysicalProductDAO(PhysicalProductDTO physicalProductDTO);
    DigitalProductDTO toDigitalProductDTO(DigitalProductDAO digitalProductDAO);
    DigitalProductDAO toDigitalProductDAO(DigitalProductDTO digitalProductDTO);
}
