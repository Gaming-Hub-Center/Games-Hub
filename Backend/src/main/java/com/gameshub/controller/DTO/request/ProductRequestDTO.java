package com.gameshub.controller.DTO.request;

import lombok.*;

import java.time.LocalDate;

@Data
public class ProductRequestDTO {

    private LocalDate dateReceived;
    private String status;
    private String requestType;
    private String title;
    private int price;
    private String description;
    private LocalDate postDate;
    private int count;
    private int sellerId;

}
