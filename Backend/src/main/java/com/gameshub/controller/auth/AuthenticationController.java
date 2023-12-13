package com.gameshub.controller.auth;

import com.gameshub.config.*;
import com.gameshub.controller.DTO.user.UserDTO;
import com.gameshub.controller.DTO.user.UserSignInDTO;
import com.gameshub.model.user.*;
import com.gameshub.service.user.UserService;
import com.gameshub.utils.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final JWTGenerator jwtGenerator;

    private final AuthenticationManager authenticationManager;

    @PostMapping("signin")
    public ResponseEntity<UserDTO> signin(@RequestBody UserSignInDTO userSignInDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userSignInDTO.getEmail(), userSignInDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.createToken(authentication.getName());
        UserDAO userDAO = userService.getByEmail(authentication.getName());
        UserDTO userDTO = userMapper.toUserDTO(userDAO);
        userDTO.setToken(token);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    // =========== Testing ===========

    @GetMapping("user")
    public UserDAO getUser(@RequestParam String email) {
        return userService.getByEmail(email);
    }

    @GetMapping("users")
    public List<UserDAO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("buyers")
    public List<BuyerDAO> getAllBuyers() {
        return userService.getAllBuyers();
    }

    @GetMapping("sellers")
    public List<SellerDAO> getAllSellers() {
        return userService.getAllSellers();
    }
  
    // ===============================
}