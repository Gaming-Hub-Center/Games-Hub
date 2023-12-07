package com.gameshub.controller.DTO.request;

import com.gameshub.controller.DTO.product.ProductDTO;
import com.gameshub.model.product.ProductDAO;
import lombok.*;

import java.time.LocalDate;

@Data
public class ProductRequestDTO {
    private Long requestId;
    private LocalDate dateReceived;
    private String status;
}
