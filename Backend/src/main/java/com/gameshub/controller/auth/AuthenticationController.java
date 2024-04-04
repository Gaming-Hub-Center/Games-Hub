package com.gameshub.controller.auth;

<<<<<<< Updated upstream
import com.gameshub.config.*;
import com.gameshub.controller.DTO.user.*;
import com.gameshub.model.user.*;
import com.gameshub.service.user.*;
import com.gameshub.utils.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.web.bind.annotation.*;
=======
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
>>>>>>> Stashed changes

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthenticationController {

<<<<<<< Updated upstream
    private final UserService userService;
    private final UserMapper userMapper;
=======
>>>>>>> Stashed changes
    private final JWTGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("signin")
    public ResponseEntity<String> signin(@RequestBody UserSignInDTO userSignInDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userSignInDTO.getEmail(), userSignInDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
<<<<<<< Updated upstream
        String token = jwtGenerator.createToken(authentication.getName());
        UserDAO userDAO = userService.getByEmail(authentication.getName());
        UserDTO userDTO = userMapper.toUserDTO(userDAO);
        userDTO.setToken(token);
        return ResponseEntity.ok(userDTO);
=======
        String email = authentication.getName();
        String token = jwtGenerator.createToken(userService.getUserByEmail(email));
        return ResponseEntity.ok(token);
>>>>>>> Stashed changes
    }

}
