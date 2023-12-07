package com.gameshub.controller.DTO.product;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DigitalProductDTO extends ProductDTO {
    private String code;
}
