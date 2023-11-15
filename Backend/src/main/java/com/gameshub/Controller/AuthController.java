package com.gameshub.Controller;

import com.gameshub.User.*;
import com.gameshub.Utils.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5173")
public class AuthController {

    @PostMapping("/signin")
    public ResponseEntity<Boolean> authenticateUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("ehab");
        SignIn signIn = new SignIn(loginRequest.getEmail(), loginRequest.getPassword());
        boolean authentication;
        try {
            authentication = signIn.authenticate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).header(e.getMessage()).body(false);
        }
        return ResponseEntity.ok(authentication);
    }

}
