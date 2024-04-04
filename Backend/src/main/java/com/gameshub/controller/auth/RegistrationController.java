package com.gameshub.controller.auth;

import com.gameshub.config.JWTGenerator;
import com.gameshub.dto.user.BuyerRegistrationDTO;
import com.gameshub.dto.user.SellerRegistrationDTO;
import com.gameshub.model.user.BuyerDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("registration")
public class RegistrationController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final JWTGenerator jwtGenerator;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("buyer")
    public ResponseEntity<String> registerNewBuyer(@RequestBody BuyerRegistrationDTO buyerRegistrationDTO) {
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
<<<<<<< Updated upstream
        String token = jwtGenerator.createToken(buyerRegistrationDTO.getEmail());
        UserDAO userDAO = userService.getByEmail(buyerRegistrationDTO.getEmail());
        UserDTO userDTO = userMapper.toUserDTO(userDAO);
        userDTO.setToken(token);
        return ResponseEntity.ok(userDTO);
=======

        String email = buyerRegistrationDTO.getEmail();
        String token = jwtGenerator.createToken(userService.getUserByEmail(email));
        return ResponseEntity.ok(token);
>>>>>>> Stashed changes
    }

    @PostMapping("seller")
    public ResponseEntity<String> registerNewSeller(@RequestBody SellerRegistrationDTO sellerRegistrationDTO) {
        userService.saveNewSeller(
            new SellerDAO(
                sellerRegistrationDTO.getName(),
                sellerRegistrationDTO.getEmail(),
                passwordEncoder.encode(sellerRegistrationDTO.getPassword()),
                sellerRegistrationDTO.getPhone(),
                sellerRegistrationDTO.getAddress(),
                0,
                sellerRegistrationDTO.getNationalId(),
                LocalDate.now(),
                sellerRegistrationDTO.getDescription(),
                sellerRegistrationDTO.getVatRegistrationNumber()
            )
        );
<<<<<<< Updated upstream
        String token = jwtGenerator.createToken(sellerRegistrationDTO.getEmail());
        UserDAO userDAO = userService.getByEmail(sellerRegistrationDTO.getEmail());
        UserDTO userDTO = userMapper.toUserDTO(userDAO);
        userDTO.setToken(token);
        return ResponseEntity.ok(userDTO);
=======

        String email = sellerRegistrationDTO.getEmail();
        String token = jwtGenerator.createToken(userService.getUserByEmail(email));
        return ResponseEntity.ok(token);
>>>>>>> Stashed changes
    }

}