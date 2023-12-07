package com.gameshub.controller.DTO.request;

import com.gameshub.controller.DTO.product.DigitalProductDTO;
import com.gameshub.controller.DTO.product.ProductDTO;
import lombok.*;

@Data
public class DigitalProductRequestDTO extends ProductRequestDTO {
    private String code;
    private DigitalProductDTO digitalProductDTO;
}
