package com.gameshub.controller.DTO.request;

import com.gameshub.controller.DTO.product.ProductDTO;
import com.gameshub.model.product.ProductDAO;
import lombok.*;

import java.time.LocalDate;

@Data
public class ProductRequestDTO {

    private int requestId;
    private LocalDate dateReceived;
    private String status;
    private ProductDTO product;

}
