package com.gameshub.authentication;

import com.gameshub.controller.DTO.user.*;
import com.gameshub.controller.auth.*;
import com.gameshub.exception.*;
import com.gameshub.model.user.*;
import com.gameshub.repository.user.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.*;

import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SignUpTests {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RegistrationController registrationController;

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    SellerRepository sellerRepository;

    public static int generateRandomIntegerInRange(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    @BeforeEach
    public void setup() {
        buyerRepository.deleteAll();
        sellerRepository.deleteAll();

        sellerRepository.resetAutoIncrement();
        buyerRepository.resetAutoIncrement();

        BuyerDAO buyerDAO = new BuyerDAO("John Doe", "john.doe@example.com", "$2a$10$YJGLrNDJ0F.mE2E6IFWnDeDrkKlvQ3FuSYaOiUieGjTMkraZJoRBG", "+1234567890", "123 Elm Street", 1000);
        SellerDAO sellerDAO = new SellerDAO("Alice Blue", "alice.blue@example.com", "$2a$10$HaID.XdQm../yady9rA2k.EoY4oiL/In32c/cLRa3DWyW/Nn6DXcG", "+1029384756", "101 Red Street", 10000, "ID12345A", LocalDate.parse("2023-01-01"), "Description about Alice", "123456789A");

        buyerRepository.save(buyerDAO);
        sellerRepository.save(sellerDAO);
    }

    @AfterEach
    public void finish() {
        buyerRepository.deleteAll();
        sellerRepository.deleteAll();

        sellerRepository.resetAutoIncrement();
        buyerRepository.resetAutoIncrement();
    }

    @Test
    public void testNewBuyer(){
        BuyerRegistrationDTO newUserData = new BuyerRegistrationDTO();
        int newUserNumber = generateRandomIntegerInRange(1, Integer.MAX_VALUE);
        newUserData.setName("user" + newUserNumber);
        newUserData.setPassword(passwordEncoder.encode("password" + newUserNumber));
        newUserData.setEmail("user" + newUserNumber + "@example.com");

        assert registrationController.registerNewBuyer(newUserData).getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    public void testNewSeller(){
        SellerRegistrationDTO newUserData = new SellerRegistrationDTO();
        int newUserNumber = generateRandomIntegerInRange(1, Integer.MAX_VALUE);
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
