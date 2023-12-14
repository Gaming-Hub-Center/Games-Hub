package com.gameshub.controller.DTO;

import lombok.*;

@Data
public class SellerRegistrationDTO extends BuyerRegistrationDTO {
    private String description;
    private String nationalID;
    private String vatRegistrationNumber;
}
