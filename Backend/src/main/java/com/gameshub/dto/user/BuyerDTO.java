package com.gameshub.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BuyerDTO extends UserDTO {
    private String address;
    private float balance;
}
