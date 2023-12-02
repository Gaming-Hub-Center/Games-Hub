package com.gameshub;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SignInTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testBuyerValidSignIn() throws Exception {
        String signInRequestJson = "{\"email\":\"john.doe@example.com\",\"password\":\"password123\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signInRequestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testSellerValidSignIn() throws Exception {
        String signInRequestJson = "{\"email\":\"alice.blue@example.com\",\"password\":\"alicepass\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signInRequestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testInvalidEmail() throws Exception {
        String signInRequestJson = "{\"email\":\"invalid.email@example.com\",\"password\":\"password123\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signInRequestJson))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testBuyerInvalidPassword() throws Exception {
        String signInRequestJson = "{\"email\":\"john.doe@example.com\",\"password\":\"invalid_password\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signInRequestJson))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testSellerInvalidPassword() throws Exception {
        String signInRequestJson = "{\"email\":\"alice.blue@example.com\",\"password\":\"invalid_password\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signInRequestJson))
                .andExpect(status().isInternalServerError());
    }

}
