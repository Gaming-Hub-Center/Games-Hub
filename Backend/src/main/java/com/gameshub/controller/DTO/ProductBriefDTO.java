package com.gameshub.controller.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductBriefDTO {
    int id;
    float price;
    String title;
    byte[] image;

    public ProductBriefDTO(int id, float price, String title) {
        this.id = id;
        this.price = price;
        this.title = title;
    }
}