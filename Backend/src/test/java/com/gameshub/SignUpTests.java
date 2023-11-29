package com.gameshub;

import com.gameshub.Controller.RegistrationController;
import com.gameshub.Model.Users.DTOs.SellerRegistrationDTO;
import com.gameshub.Model.Users.DTOs.BuyerRegistrationDTO;
import com.gameshub.Utils.RandomIntegerGenerator;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class SignUpTests {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RegistrationController registrationController;

    @Test
    public void testNewBuyer(){
        BuyerRegistrationDTO newUserData = new BuyerRegistrationDTO();
        int newUserNumber = RandomIntegerGenerator.generateRandomIntegerInRange(1, Integer.MAX_VALUE);
        newUserData.setName("user" + newUserNumber);
        newUserData.setPassword(passwordEncoder.encode("password" + newUserNumber));
        newUserData.setEmail("user" + newUserNumber + "@example.com");

        assert registrationController.registerNewUser(newUserData).getBody().equals("Registered " + newUserData.getName() + " successfully!!");
    }

    @Test
    public void testNewSeller(){
        SellerRegistrationDTO newUserData = new SellerRegistrationDTO();
        int newUserNumber = RandomIntegerGenerator.generateRandomIntegerInRange(1, Integer.MAX_VALUE);
        newUserData.setName("user" + newUserNumber);
        newUserData.setPassword(passwordEncoder.encode("password" + newUserNumber));
        newUserData.setEmail("user" + newUserNumber + "@example.com");
        newUserData.setDescription("desc" + newUserNumber);

        assert registrationController.registerNewUser(newUserData).getBody().equals("Registered " + newUserData.getName() + " successfully!!");
    }

    @Test
    public void testExistingBuyer(){
        BuyerRegistrationDTO newUserData = new BuyerRegistrationDTO();
        newUserData.setName("John Doe");
        newUserData.setPassword(passwordEncoder.encode("password123"));
        newUserData.setEmail("john.doe@example.com");

        assert registrationController.registerNewUser(newUserData).getBody().equals("User John Doe already exists!!");
    }

    @Test
    public void testExistingSeller(){
        SellerRegistrationDTO newUserData = new SellerRegistrationDTO();
        newUserData.setName("Alice Blue");
        newUserData.setPassword(passwordEncoder.encode("alicepass"));
        newUserData.setEmail("alice.blue@example.com");
        assert registrationController.registerNewUser(newUserData).getBody().equals("User Alice Blue already exists!!");
    }

}
