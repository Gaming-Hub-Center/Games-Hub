package com.gameshub.controller.DTO.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminDTO {
    private int id;
    private String name;
    private String email;
    private String phone;
}
