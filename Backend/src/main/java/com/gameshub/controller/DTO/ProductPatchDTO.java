package com.gameshub.controller.DTO;

import lombok.Data;

@Data
public class ProductPatchDTO {
    private String title;
    private String description;

    public ProductPatchDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
