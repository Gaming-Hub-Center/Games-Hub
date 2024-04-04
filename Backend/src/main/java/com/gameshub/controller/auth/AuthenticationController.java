package com.gameshub.controller.auth;

import com.gameshub.config.JWTGenerator;
import com.gameshub.dto.user.UserSignInDTO;
import com.gameshub.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final JWTGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("signin")
    public ResponseEntity<String> signin(@RequestBody UserSignInDTO userSignInDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userSignInDTO.getEmail(), userSignInDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String email = authentication.getName();
        String token = jwtGenerator.createToken(userService.getUserByEmail(email));
        return ResponseEntity.ok(token);
    }

}
