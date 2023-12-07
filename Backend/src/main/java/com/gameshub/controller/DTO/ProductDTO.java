package com.gameshub.controller.DTO;

import lombok.Data;
import java.sql.Blob;

@Data
public class ProductDTO {
    private int id;
    private float price;
    private Blob image;
    private String description;
    private int sellerID;
}
