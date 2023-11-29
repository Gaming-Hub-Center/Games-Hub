package com.gameshub;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.gameshub.Controller.AuthController;
import com.gameshub.Model.Users.DAOs.BuyerDAO;
import com.gameshub.Model.Users.DAOs.SellerDAO;
import com.gameshub.Repository.BuyerRepository;
import com.gameshub.Repository.SellerRepository;
import com.gameshub.Service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Optional;

@WebMvcTest(AuthController.class)
public class SignInTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BuyerRepository buyerRepository;

    @MockBean
    private SellerRepository sellerRepository;

    @MockBean
    private BuyerDetailsService buyerDetailsService;

    @MockBean
    private SellerDetailsService sellerDetailsService;

    private BuyerDAO buyerDAO1 = new BuyerDAO(1, "John Doe", "john.doe@example.com", "password123", "+1234567890", "123 Elm Street", 3000);
    private BuyerDAO buyerDAO2 = new BuyerDAO(2, "Jane Smith", "jane.smith@example.com", "mypassword", "+9876543210", "456 Oak Avenue", 4000);
    private SellerDAO sellerDAO1 = new SellerDAO(1, "Alice Blue", "alice.blue@example.com", "alicepass", "+1029384756", "101 Red Street", 10000, "ID12345A", LocalDate.of(2023, 1, 1), "Description about Alice", "123456789A");
    private SellerDAO sellerDAO2 = new SellerDAO(2, "Bob Green", "bob.green@example.com", "bobpassword", "+5647382910", "202 Green Lane", 15000, "ID67890B", LocalDate.of(2023, 2, 2), "Description about Bob", "987654321B");

    @BeforeEach
    public void setUp() {
        Mockito.when(buyerRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(buyerDAO1));
        Mockito.when(buyerRepository.findByEmail("jane.smith@example.com")).thenReturn(Optional.of(buyerDAO2));
        Mockito.when(sellerRepository.findByEmail("alice.blue@example.com")).thenReturn(Optional.of(sellerDAO1));
        Mockito.when(sellerRepository.findByEmail("bob.green@example.com")).thenReturn(Optional.of(sellerDAO2));
    }

    @Test
    public void testValidSignIn() throws Exception {
        String signInRequestJson = "{\"email\":\"john.doe@example.com\",\"password\":\"password123\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signInRequestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testInvalidEmail() throws Exception {
        String signInRequestJson = "{\"email\":\"invalid_email@example.com\",\"password\":\"pass\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signInRequestJson))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testInvalidPassword() throws Exception {
        String signInRequestJson = "{\"email\":\"john.doe@example.com\",\"password\":\"invalid_pass\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signInRequestJson))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testInvalidFormat() throws Exception {
        String signInRequestJson = "{\"email\":\"john.doe@example\",\"password\":\"password123\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signInRequestJson))
                .andExpect(status().isBadRequest());
    }
}
