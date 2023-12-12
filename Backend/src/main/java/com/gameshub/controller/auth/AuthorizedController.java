package com.gameshub.controller.auth;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authorized")
public class AuthorizedController {

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome authorized</h1>");
    }

}