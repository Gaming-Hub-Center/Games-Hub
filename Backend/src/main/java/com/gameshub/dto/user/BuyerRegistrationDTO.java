package com.gameshub.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BuyerRegistrationDTO extends UserRegistrationDTO {
    private String address;
}
