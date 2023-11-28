package com.gameshub;

import com.gameshub.Controller.RegistrationController;
import com.gameshub.Model.Users.DTOs.SellerRegistrationDTO;
import com.gameshub.Model.Users.DTOs.UserRegistrationDTO;
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
        UserRegistrationDTO newUserData = new UserRegistrationDTO();
        int newUserNumber = RandomIntegerGenerator.generateRandomIntegerInRange(1, Integer.MAX_VALUE);
        newUserData.setUserName("user" + newUserNumber);
        newUserData.setPassword(passwordEncoder.encode("password" + newUserNumber));
        newUserData.setEmail("user" + newUserNumber + "@example.com");

        assert registrationController.registerNewUser(newUserData).getBody().equals("Registered " + newUserData.getUserName() + " successfully!!");
    }

    @Test
    public void testNewSeller(){
        SellerRegistrationDTO newUserData = new SellerRegistrationDTO();
        int newUserNumber = RandomIntegerGenerator.generateRandomIntegerInRange(1, Integer.MAX_VALUE);
        newUserData.setUserName("user" + newUserNumber);
        newUserData.setPassword(passwordEncoder.encode("password" + newUserNumber));
        newUserData.setEmail("user" + newUserNumber + "@example.com");
        newUserData.setDescription("desc" + newUserNumber);

        assert registrationController.registerNewUser(newUserData).getBody().equals("Registered " + newUserData.getUserName() + " successfully!!");
    }

    @Test
    public void testExistingBuyer(){
        UserRegistrationDTO newUserData = new UserRegistrationDTO();
        newUserData.setUserName("John Doe");
        newUserData.setPassword(passwordEncoder.encode("password123"));
        newUserData.setEmail("john.doe@example.com");

        assert registrationController.registerNewUser(newUserData).getBody().equals("User John Doe already exists!!");
    }

    @Test
    public void testExistingSeller(){
        SellerRegistrationDTO newUserData = new SellerRegistrationDTO();
        newUserData.setUserName("Alice Blue");
        newUserData.setPassword(passwordEncoder.encode("alicepass"));
        newUserData.setEmail("alice.blue@example.com");
        assert registrationController.registerNewUser(newUserData).getBody().equals("User Alice Blue already exists!!");
    }

}
