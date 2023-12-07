package com.gameshub.controller.auth;

import com.gameshub.config.*;
import com.gameshub.controller.DTO.user.BuyerRegistrationDTO;
import com.gameshub.controller.DTO.user.SellerRegistrationDTO;
import com.gameshub.controller.DTO.user.UserDTO;
import com.gameshub.model.user.*;
import com.gameshub.service.user.UserService;
import com.gameshub.utils.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final JWTGenerator jwtGenerator;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/buyer")
    public ResponseEntity<UserDTO> registerNewBuyer(@RequestBody BuyerRegistrationDTO buyerRegistrationDTO){
        userService.saveNewBuyer(
            new BuyerDAO(
                buyerRegistrationDTO.getName(),
                buyerRegistrationDTO.getEmail(),
                passwordEncoder.encode(buyerRegistrationDTO.getPassword()),
                buyerRegistrationDTO.getPhone(),
                buyerRegistrationDTO.getAddress(),
                0
            )
        );
        String token = jwtGenerator.createToken(buyerRegistrationDTO.getEmail());
        UserDAO userDAO = userService.getByEmail(buyerRegistrationDTO.getEmail());
        UserDTO userDTO = userMapper.toUserDTO(userDAO);
        userDTO.setToken(token);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/seller")
    public ResponseEntity<UserDTO> registerNewSeller(@RequestBody SellerRegistrationDTO sellerRegistrationDTO){
        userService.saveNewSeller(
            new SellerDAO(
                    sellerRegistrationDTO.getName(),
                    sellerRegistrationDTO.getEmail(),
                    passwordEncoder.encode(sellerRegistrationDTO.getPassword()),
                    sellerRegistrationDTO.getPhone(),
                    sellerRegistrationDTO.getAddress(),
                    0,
                    sellerRegistrationDTO.getNationalID(),
                    LocalDate.now(),
                    sellerRegistrationDTO.getDescription(),
                    sellerRegistrationDTO.getVatRegistrationNumber()
            )
        );
        String token = jwtGenerator.createToken(sellerRegistrationDTO.getEmail());
        UserDAO userDAO = userService.getByEmail(sellerRegistrationDTO.getEmail());
        UserDTO userDTO = userMapper.toUserDTO(userDAO);
        userDTO.setToken(token);
        return ResponseEntity.ok(userDTO);
    }

}