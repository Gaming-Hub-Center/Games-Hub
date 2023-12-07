package com.gameshub;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gameshub.controller.*;
import com.gameshub.controller.DTO.*;
import com.gameshub.model.user.*;
import com.gameshub.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;

import java.time.*;

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
        BuyerDAO buyerDAO = new BuyerDAO("John Doe", "john.doe@example.com", "$2a$10$YJGLrNDJ0F.mE2E6IFWnDeDrkKlvQ3FuSYaOiUieGjTMkraZJoRBG", "+1234567890", "123 Elm Street", 3000);
        SellerDAO sellerDAO = new SellerDAO("Alice Blue", "alice.blue@example.com", "$2a$10$HaID.XdQm../yady9rA2k.EoY4oiL/In32c/cLRa3DWyW/Nn6DXcG", "+1029384756", "101 Red Street", 10000, "ID12345A", LocalDate.parse("2023-01-01"), "Description about Alice", "123456789A");
        buyerRepository.save(buyerDAO);
        sellerRepository.save(sellerDAO);
    }

    @Test
    public void testBuyerValidSignIn() throws Exception {
        UserSignInDTO userSigninDTO = new UserSignInDTO();
        userSigninDTO.setEmail("john.doe@example.com");
        userSigninDTO.setPassword("password123");
        ResponseEntity<?> responseEntity = authenticationController.signin(userSigninDTO);

        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    public void testSellerValidSignIn() throws Exception {
        UserSignInDTO userSigninDTO = new UserSignInDTO();
        userSigninDTO.setEmail("alice.blue@example.com");
        userSigninDTO.setPassword("alicepass");
        ResponseEntity<?> responseEntity = authenticationController.signin(userSigninDTO);

        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    public void testInvalidEmail() throws Exception {
        UserSignInDTO userSigninDTO = new UserSignInDTO();
        userSigninDTO.setEmail("invalid.email@example.com");
        userSigninDTO.setPassword("password123");

        assertThrows(InternalAuthenticationServiceException.class, () -> {
            authenticationController.signin(userSigninDTO);
        });
    }

    @Test
    public void testBuyerInvalidPassword() throws Exception {
        UserSignInDTO userSigninDTO = new UserSignInDTO();
        userSigninDTO.setEmail("john.doe@example.com");
        userSigninDTO.setPassword("invalid_password");

        assertThrows(BadCredentialsException.class, () -> {
            authenticationController.signin(userSigninDTO);
        });
    }

    @Test
    public void testSellerInvalidPassword() throws Exception {
        UserSignInDTO userSigninDTO = new UserSignInDTO();
        userSigninDTO.setEmail("alice.blue@example.com");
        userSigninDTO.setPassword("invalid_password");

        assertThrows(BadCredentialsException.class, () -> {
            authenticationController.signin(userSigninDTO);
        });
    }

}
