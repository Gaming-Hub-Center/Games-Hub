package com.gameshub.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDTO {
    private String name;
    private String email;
    private String password;
    private String phone;
}
