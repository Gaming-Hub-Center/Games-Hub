package com.gameshub.controller.DTO.user;

import lombok.*;

@Data
@NoArgsConstructor
public class BuyerDTO extends UserDTO {
    private String address;
    private float balance;
}
