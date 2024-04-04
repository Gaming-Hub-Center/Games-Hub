package com.gameshub.authentication;

import com.gameshub.dto.user.UserSignInDTO;
import com.gameshub.controller.auth.AuthenticationController;
import com.gameshub.exception.PasswordMismatchException;
import com.gameshub.model.user.BuyerDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.user.BuyerRepository;
import com.gameshub.repository.user.SellerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SignInTests {

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    SellerRepository sellerRepository;

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
    public void testBuyerValidSignIn() {
        UserSignInDTO userSignInDTO = new UserSignInDTO();
        userSignInDTO.setEmail("john.doe@example.com");
        userSignInDTO.setPassword("password123");
        ResponseEntity<?> responseEntity = authenticationController.signin(userSignInDTO);

        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    public void testSellerValidSignIn() {
        UserSignInDTO userSignInDTO = new UserSignInDTO();
        userSignInDTO.setEmail("alice.blue@example.com");
        userSignInDTO.setPassword("alicepass");
        ResponseEntity<?> responseEntity = authenticationController.signin(userSignInDTO);

        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    public void testInvalidEmail() {
        UserSignInDTO userSignInDTO = new UserSignInDTO();
        userSignInDTO.setEmail("invalid.email@example.com");
        userSignInDTO.setPassword("password123");

        assertThrows(InternalAuthenticationServiceException.class, () -> authenticationController.signin(userSignInDTO));
    }

    @Test
    public void testBuyerInvalidPassword() {
        UserSignInDTO userSignInDTO = new UserSignInDTO();
        userSignInDTO.setEmail("john.doe@example.com");
        userSignInDTO.setPassword("invalid_password");

        assertThrows(PasswordMismatchException.class, () -> authenticationController.signin(userSignInDTO));
    }

    @Test
    public void testSellerInvalidPassword() {
        UserSignInDTO userSignInDTO = new UserSignInDTO();
        userSignInDTO.setEmail("alice.blue@example.com");
        userSignInDTO.setPassword("invalid_password");

        assertThrows(PasswordMismatchException.class, () -> authenticationController.signin(userSignInDTO));
    }

}
