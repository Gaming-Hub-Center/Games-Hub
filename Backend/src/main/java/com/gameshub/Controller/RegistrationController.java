package com.gameshub.Controller;

import com.gameshub.Model.Users.BuyerDAO;
import com.gameshub.Model.Users.DTOs.SellerRegistrationDTO;
import com.gameshub.Model.Users.DTOs.BuyerRegistrationDTO;
import com.gameshub.Model.Users.SellerDAO;
import com.gameshub.Service.BuyerDetailsService;
import com.gameshub.Service.SellerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public ResponseEntity<String> registerNewUser(@RequestBody BuyerRegistrationDTO buyerProvidedInfo){
        try{
            buyerDetailsService.saveNewUser(new BuyerDAO(buyerProvidedInfo.getName(), buyerProvidedInfo.getEmail(), passwordEncoder.encode(buyerProvidedInfo.getPassword()),
                    buyerProvidedInfo.getPhone(), buyerProvidedInfo.getAddress()));
            return new ResponseEntity<String>("Registered " + buyerProvidedInfo.getName() + " successfully!!" ,HttpStatus.OK);
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<String>("User " + buyerProvidedInfo.getName() + " already exists!!" ,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/seller")
    public ResponseEntity<String> registerNewUser(@RequestBody SellerRegistrationDTO sellerProvidedInfo){
        try{
            sellerDetailsService.saveNewUser(new SellerDAO(sellerProvidedInfo.getName(), sellerProvidedInfo.getEmail(), passwordEncoder.encode(sellerProvidedInfo.getPassword()),
                    sellerProvidedInfo.getPhone(), sellerProvidedInfo.getAddress(), sellerProvidedInfo.getNationalID(), LocalDate.now(), sellerProvidedInfo.getDescription(), sellerProvidedInfo.getVatRegistrationNumber()));

            return new ResponseEntity<String>("Registered " + sellerProvidedInfo.getName() + " successfully!!" ,HttpStatus.OK);
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<String>("User " + sellerProvidedInfo.getName() + " already exists!!" ,HttpStatus.BAD_REQUEST);
        }



    }

}
