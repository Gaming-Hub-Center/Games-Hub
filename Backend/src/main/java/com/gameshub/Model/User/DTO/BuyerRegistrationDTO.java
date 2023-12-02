package com.gameshub.Model.User.DTO;

import lombok.*;

@Data
public class BuyerRegistrationDTO {
    private String name;
    private String email;
    private String password;
    private String imageID;
    private String phone;
    private String address;
}
