package com.gameshub.controller.DTO.request;

import com.gameshub.controller.DTO.product.DigitalProductDTO;
import com.gameshub.model.product.DigitalProductDAO;
import lombok.*;

@Data
@NoArgsConstructor
public class DigitalProductRequestDTO extends ProductRequestDTO {
    DigitalProductDTO digitalProductDTO;
}
