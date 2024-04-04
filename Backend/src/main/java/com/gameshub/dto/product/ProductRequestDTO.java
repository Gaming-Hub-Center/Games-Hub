package com.gameshub.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequestDTO {
    private int id;
    private LocalDate dateReceived;
    private String status;
    private String requestType;
    private String title;
    private float price;
    private String description;
    private LocalDate postDate;
    private int count;
    private int sellerId;
    private String category;
    private List<String> images;
}
