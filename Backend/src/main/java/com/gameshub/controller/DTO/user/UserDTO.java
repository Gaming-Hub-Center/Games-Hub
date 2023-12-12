package com.gameshub.controller.DTO.user;

import lombok.*;

@Data
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private float balance;
    private String token;
}
