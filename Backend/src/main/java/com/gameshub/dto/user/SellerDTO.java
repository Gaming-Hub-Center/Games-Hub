package com.gameshub.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SellerDTO extends UserDTO {
<<<<<<< Updated upstream:Backend/src/main/java/com/gameshub/controller/DTO/user/SellerDTO.java
    private String nationalID;
=======
    private String address;
    private float balance;
    private String nationalId;
>>>>>>> Stashed changes:Backend/src/main/java/com/gameshub/dto/user/SellerDTO.java
    private LocalDate dateJoined;
    private String sellerDescription;
    private String vatRegistrationNumber;
}
