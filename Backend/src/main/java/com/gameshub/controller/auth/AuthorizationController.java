package com.gameshub.controller.auth;

import lombok.*;
import org.springframework.web.bind.annotation.*;

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
