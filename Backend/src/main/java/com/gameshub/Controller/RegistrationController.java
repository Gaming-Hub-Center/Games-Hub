package com.gameshub.Controller;

import com.gameshub.Model.Users.BuyerDAO;
import com.gameshub.Model.Users.DTOs.SellerRegistrationDTO;
import com.gameshub.Model.Users.DTOs.UserRegistrationDTO;
import com.gameshub.Model.Users.SellerDAO;
import com.gameshub.Repository.BuyerRepository;
import com.gameshub.Repository.SellerRepository;
import com.gameshub.Service.BuyerDetailsService;
import com.gameshub.Service.SellerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    BuyerDetailsService buyerDetailsService;

    @Autowired
    SellerDetailsService sellerDetailsService;

    @PostMapping("/user")
    public ResponseEntity<String> registerNewUser(@RequestBody UserRegistrationDTO userProvidedInfo){
        if(buyerDetailsService.userExists(userProvidedInfo.getEmail())) //user exists
            return new ResponseEntity<String>("User " + userProvidedInfo.getUserName() + " already exists!!" ,HttpStatus.BAD_REQUEST);

        buyerDetailsService.saveNewUser(new BuyerDAO(userProvidedInfo.getUserName(), userProvidedInfo.getEmail(), passwordEncoder.encode(userProvidedInfo.getPassword()),
                userProvidedInfo.getPhone(), userProvidedInfo.getAddress()));

        return new ResponseEntity<String>("Registered " + userProvidedInfo.getUserName() + " successfully!!" ,HttpStatus.OK);
    }

    @PostMapping("/seller")
    public ResponseEntity<String> registerNewUser(@RequestBody SellerRegistrationDTO sellerProvidedInfo){
        if(sellerDetailsService.userExists(sellerProvidedInfo.getEmail())) //user exists
            return new ResponseEntity<String>("User " + sellerProvidedInfo.getUserName() + " already exists!!" ,HttpStatus.BAD_REQUEST);

        sellerDetailsService.saveNewUser(new SellerDAO(sellerProvidedInfo.getUserName(), sellerProvidedInfo.getEmail(), passwordEncoder.encode(sellerProvidedInfo.getPassword()),
                sellerProvidedInfo.getPhone(), sellerProvidedInfo.getAddress(), sellerProvidedInfo.getNationalID(), LocalDate.now(), sellerProvidedInfo.getDescription(), sellerProvidedInfo.getVatRegistrationNumber()));

        return new ResponseEntity<String>("Registered " + sellerProvidedInfo.getUserName() + " successfully!!" ,HttpStatus.OK);
    }

}
