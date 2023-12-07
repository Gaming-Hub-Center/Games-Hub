package com.gameshub.utils;

import com.gameshub.controller.DTO.request.*;
import com.gameshub.model.request.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {
    PhysicalProductRequestDAO toProductDAO(PhysicalProductRequestDTO physicalProductRequestDTO);

    DigitalProductRequestDAO toProductDAO(DigitalProductRequestDTO digitalProductRequestDTO);
}
