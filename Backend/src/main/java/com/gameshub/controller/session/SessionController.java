package com.gameshub.controller.session;

import com.gameshub.dto.user.UserDTO;
import com.gameshub.config.JWTGenerator;
import com.gameshub.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("session")
public class SessionController {

    private final JWTGenerator jwtGenerator;
    private final UserService userService;

    @GetMapping("refresh/token")
    public ResponseEntity<String> refreshToken(@AuthenticationPrincipal int userId) {
        String newToken = jwtGenerator.createToken(userService.getUserById(userId));
        return ResponseEntity.ok(newToken);
    }

    @GetMapping("refresh/user")
    public ResponseEntity<UserDTO> refreshUser(@AuthenticationPrincipal int userId) {
        return ResponseEntity.ok(userService.getUserDTOById(userId));
    }

}
