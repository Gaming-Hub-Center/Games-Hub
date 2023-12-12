package com.gameshub.controller.DTO.request;

import lombok.*;

@Data
@NoArgsConstructor
public class DigitalProductRequestDTO extends ProductRequestDTO {
    private String code;
}
