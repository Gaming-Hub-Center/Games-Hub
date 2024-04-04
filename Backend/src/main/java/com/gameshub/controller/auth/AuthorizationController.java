package com.gameshub.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthorizationController {

    @GetMapping("authorized")
    public String getAuthorized() {
        return "Welcome Authorized";
    }

    @GetMapping("public")
    public String getPublic() {
        return "Welcome Public";
    }

}
