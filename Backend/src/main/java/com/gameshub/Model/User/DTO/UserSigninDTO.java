package com.gameshub.Model.User.DTO;

import lombok.*;

@Data
@AllArgsConstructor
public class UserSigninDTO {
    private String email;
    private String password;
}
