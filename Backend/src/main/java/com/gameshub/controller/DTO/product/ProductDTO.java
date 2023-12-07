package com.gameshub.controller.DTO.product;

import lombok.*;

import java.time.LocalDate;

@Data
public class ProductDTO {
    private int id;
    private String title;
    private float price;
    private String description;
    private String physicalOrDigital;
    private LocalDate postDate;
    private int count;
}