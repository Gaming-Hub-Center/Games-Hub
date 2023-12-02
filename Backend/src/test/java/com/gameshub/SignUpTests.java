package com.gameshub;

import com.gameshub.Controller.*;
import com.gameshub.Exception.*;
import com.gameshub.Model.User.DTO.*;
import com.gameshub.Utils.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.*;

import static org.junit.jupiter.api.Assertions.*;

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

        assert registrationController.registerNewBuyer(newUserData).getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    public void testNewSeller(){
        SellerRegistrationDTO newUserData = new SellerRegistrationDTO();
        int newUserNumber = RandomIntegerGenerator.generateRandomIntegerInRange(1, Integer.MAX_VALUE);
        newUserData.setName("user" + newUserNumber);
        newUserData.setPassword(passwordEncoder.encode("password" + newUserNumber));
        newUserData.setEmail("user" + newUserNumber + "@example.com");
        newUserData.setDescription("desc" + newUserNumber);

        assert registrationController.registerNewSeller(newUserData).getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    public void testExistingBuyer(){
        BuyerRegistrationDTO newUserData = new BuyerRegistrationDTO();
        newUserData.setName("John Doe");
        newUserData.setPassword(passwordEncoder.encode("password123"));
        newUserData.setEmail("john.doe@example.com");

        assertThrows(ResourceAlreadyFoundException.class, () -> {
            registrationController.registerNewBuyer(newUserData);
        });
    }

    @Test
    public void testExistingSeller(){
        SellerRegistrationDTO newUserData = new SellerRegistrationDTO();
        newUserData.setName("Alice Blue");
        newUserData.setPassword(passwordEncoder.encode("alicepass"));
        newUserData.setEmail("alice.blue@example.com");

        assertThrows(ResourceAlreadyFoundException.class, () -> {
            registrationController.registerNewSeller(newUserData);
        });
    }

}
