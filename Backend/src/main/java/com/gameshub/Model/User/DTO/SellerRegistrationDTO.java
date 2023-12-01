package com.gameshub.Model.User.DTO;

import lombok.*;

@Data
public class SellerRegistrationDTO extends BuyerRegistrationDTO {
    private String description;
    private String nationalID;
    private String vatRegistrationNumber;
}
