package com.gameshub.controller.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductBriefDTO {
    private int id;
    private float price;
    private String title;
    private String description;
    private String url;
    private String productType;


    public ProductBriefDTO(int id, float price, String title, String description) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.description = description;
    }
}
