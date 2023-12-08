package com.gameshub.controller.DTO.product;

import lombok.*;

import java.sql.Blob;
import java.time.LocalDate;

@Data
public class ProductDTO {
    private int id;
    private String title;
    private int price;
    private String description;
    private LocalDate postDate;
    private int count;
    private int sellerId;
    private Blob image;
}