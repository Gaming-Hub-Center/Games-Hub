package com.gameshub.controller.DTO;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ProductDTO {
    private int id;
    private float price;
    private String description;
    private String title;
    private int count;
    private int sellerID;
    private LocalDate postDate;
    private String category;
    private List<String> urls;
}