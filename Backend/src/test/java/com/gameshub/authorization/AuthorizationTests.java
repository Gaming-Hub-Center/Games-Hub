package com.gameshub.authorization;

import com.gameshub.controller.DTO.user.*;
import com.gameshub.controller.auth.*;
import com.gameshub.model.user.*;
import com.gameshub.repository.user.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorizationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    AuthorizationController authorizationController;

    @Autowired
    BuyerRepository buyerRepository;

    String validToken;
    String invalidToken;

    @BeforeEach
    public void setup() {
        buyerRepository.deleteAll();
        buyerRepository.resetAutoIncrement();
        BuyerDAO buyerDAO = new BuyerDAO("John Doe", "john.doe@example.com", "$2a$10$YJGLrNDJ0F.mE2E6IFWnDeDrkKlvQ3FuSYaOiUieGjTMkraZJoRBG", "+1234567890", "123 Elm Street", 1000);
        buyerRepository.save(buyerDAO);

        UserSignInDTO userSignInDTO = new UserSignInDTO();
        userSignInDTO.setEmail("john.doe@example.com");
        userSignInDTO.setPassword("password123");
        ResponseEntity<UserDTO> responseEntity = authenticationController.signin(userSignInDTO);
        validToken = responseEntity.getBody().getToken();
        invalidToken = "invalid";
    }

    @AfterEach
    public void finish() {
        buyerRepository.deleteAll();
        buyerRepository.resetAutoIncrement();
    }

    @Test
    public void testValidTokenPublicPath() throws Exception {
        String token = "Bearer " + validToken;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/public")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome Public")));
    }

    @Test
    public void testInvalidTokenPublicPath() throws Exception {
        String token = "Bearer " + invalidToken;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/public")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome Public")));
    }

    @Test
    public void testEmptyTokenPublicPath() throws Exception {
        String token = "Bearer ";

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/public")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome Public")));
    }

    @Test
    public void testValidTokenAuthorizedPath() throws Exception {
        String token = "Bearer " + validToken;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/authorized")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome Authorized")));
    }

    @Test
    public void testInvalidTokenAuthorizedPath() throws Exception {
        String token = "Bearer " + invalidToken;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/authorized")
                        .header("Authorization", token))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testEmptyTokenAuthorizedPath() throws Exception {  // TODO
        String token = "Bearer";

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/authorized")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome Authorized")));
    }

}
