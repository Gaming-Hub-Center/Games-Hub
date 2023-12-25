package com.gameshub.controller.DTO.user;

import lombok.*;

import java.time.*;

@Data
@NoArgsConstructor
public class SellerDTO extends UserDTO {
    private String nationalID;
    private LocalDate dateJoined;
    private String sellerDescription;
    private String vatRegistrationNumber;
}
