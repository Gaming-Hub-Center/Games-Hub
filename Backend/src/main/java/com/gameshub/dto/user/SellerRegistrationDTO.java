package com.gameshub.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SellerRegistrationDTO extends UserRegistrationDTO {
    private String address;
    private String description;
    private String nationalId;
    private String vatRegistrationNumber;
}
