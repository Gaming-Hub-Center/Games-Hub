package com.gameshub.controller.DTO.request;

import com.gameshub.controller.DTO.product.PhysicalProductDTO;
import com.gameshub.controller.DTO.product.ProductDTO;
import lombok.*;

@Data
public class PhysicalProductRequestDTO extends ProductRequestDTO {
    private PhysicalProductDTO physicalProductDTO;
}
