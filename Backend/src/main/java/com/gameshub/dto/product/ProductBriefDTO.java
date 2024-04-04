package com.gameshub.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductBriefDTO {

    public ProductBriefDTO(int id, float price, String title, String description) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.description = description;
    }

    private int id;
    private float price;
    private String title;
    private String description;
    private String url;
    private String productType;

}
