package com.gameshub.controller.DTO.request;

import lombok.*;

import java.time.*;
import java.util.List;

@Data
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
