package com.gameshub;

import static org.junit.jupiter.api.Assertions.*;

import com.gameshub.controller.DTO.user.UserSignInDTO;
import com.gameshub.controller.auth.AuthenticationController;
import com.gameshub.model.user.*;
import com.gameshub.repository.user.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.jdbc.core.*;
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

    @Autowired
    JdbcTemplate jdbcTemplate;

    private void resetAutoIncrement(String tableName) {
        jdbcTemplate.execute("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1");
    }

    @BeforeEach
    public void setup() {
        sellerRepository.resetAutoIncrement();
        buyerRepository.resetAutoIncrement();
        buyerRepository.deleteAll();
        sellerRepository.deleteAll();
        BuyerDAO buyerDAO = new BuyerDAO("John Doe", "john.doe@example.com", "$2a$10$YJGLrNDJ0F.mE2E6IFWnDeDrkKlvQ3FuSYaOiUieGjTMkraZJoRBG", "+1234567890", "123 Elm Street", 1000);
        SellerDAO sellerDAO = new SellerDAO("Alice Blue", "alice.blue@example.com", "$2a$10$HaID.XdQm../yady9rA2k.EoY4oiL/In32c/cLRa3DWyW/Nn6DXcG", "+1029384756", "101 Red Street", 10000, "ID12345A", LocalDate.parse("2023-01-01"), "Description about Alice", "123456789A");

        buyerRepository.save(buyerDAO);
        sellerRepository.save(sellerDAO);
    }

//    @AfterEach
//    public void finish() {
//        buyerRepository.deleteAll();
//        sellerRepository.deleteAll();
//        sellerRepository.resetAutoIncrement();
//        buyerRepository.resetAutoIncrement();
//    }

    @Test
    public void testBuyerValidSignIn() throws Exception {
        UserSignInDTO userSignInDTO = new UserSignInDTO();
        userSignInDTO.setEmail("john.doe@example.com");
        userSignInDTO.setPassword("password123");
        ResponseEntity<?> responseEntity = authenticationController.signin(userSignInDTO);

        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    public void testSellerValidSignIn() throws Exception {
        UserSignInDTO userSignInDTO = new UserSignInDTO();
        userSignInDTO.setEmail("alice.blue@example.com");
        userSignInDTO.setPassword("alicepass");
        ResponseEntity<?> responseEntity = authenticationController.signin(userSignInDTO);

        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    public void testInvalidEmail() throws Exception {
        UserSignInDTO userSignInDTO = new UserSignInDTO();
        userSignInDTO.setEmail("invalid.email@example.com");
        userSignInDTO.setPassword("password123");

        assertThrows(InternalAuthenticationServiceException.class, () -> {
            authenticationController.signin(userSignInDTO);
        });
    }

    @Test
    public void testBuyerInvalidPassword() throws Exception {
        UserSignInDTO userSignInDTO = new UserSignInDTO();
        userSignInDTO.setEmail("john.doe@example.com");
        userSignInDTO.setPassword("invalid_password");

        assertThrows(BadCredentialsException.class, () -> {
            authenticationController.signin(userSignInDTO);
        });
    }

    @Test
    public void testSellerInvalidPassword() throws Exception {
        UserSignInDTO userSignInDTO = new UserSignInDTO();
        userSignInDTO.setEmail("alice.blue@example.com");
        userSignInDTO.setPassword("invalid_password");

        assertThrows(BadCredentialsException.class, () -> {
            authenticationController.signin(userSignInDTO);
        });
    }

}
