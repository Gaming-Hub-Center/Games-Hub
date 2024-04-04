package com.gameshub.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private int id;
    private float price;
    private String description;
    private String title;
    private int count;
    private int sellerId;
    private LocalDate postDate;
    private String category;
    private List<String> urls;
}
