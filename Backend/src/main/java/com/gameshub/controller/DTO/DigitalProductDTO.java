package com.gameshub.controller.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DigitalProductDTO extends ProductDTO {
    private String code;
}
