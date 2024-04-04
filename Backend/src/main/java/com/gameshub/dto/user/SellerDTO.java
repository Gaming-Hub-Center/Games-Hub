package com.gameshub.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SellerDTO extends UserDTO {
    private String address;
    private float balance;
    private String nationalId;
    private LocalDate dateJoined;
    private String sellerDescription;
    private String vatRegistrationNumber;
}
