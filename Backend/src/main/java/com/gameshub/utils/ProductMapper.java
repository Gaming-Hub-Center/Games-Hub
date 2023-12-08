package com.gameshub.utils;

import com.gameshub.controller.DTO.product.*;
import com.gameshub.model.product.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    DigitalProductDTO toProductDTO(DigitalProductDAO digitalProductDAO);

    PhysicalProductDTO toProductDTO(PhysicalProductDAO physicalProductDAO);

    // Methods to convert from DTO to DAO
    DigitalProductDAO toProductDAO(DigitalProductDTO digitalProductDTO);

    PhysicalProductDAO toProductDAO(PhysicalProductDTO physicalProductDTO);

}
