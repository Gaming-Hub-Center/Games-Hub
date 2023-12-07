package com.gameshub.utils;

import com.gameshub.controller.DTO.PhysicalProductDTO;
import com.gameshub.controller.DTO.ProductDTO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.product.ProductDAO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    PhysicalProductDTO toPhysicalProductDTO(PhysicalProductDAO physicalProductDAO);
    ProductDTO toProductDTO(ProductDAO productDAO);
}
