package com.gameshub;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.*;
import com.gameshub.Controller.*;
import com.gameshub.Model.Users.*;
import com.gameshub.Repository.*;
import org.mockito.*;
import org.junit.jupiter.api.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.setup.*;

import java.time.*;

public class SignInTests {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private BuyerRepository buyerRepository;
    @Mock
    private SellerRepository sellerRepository;

    @InjectMocks
    private AuthController authController;

    Buyer buyer1 = new Buyer(1, "John Doe", "john.doe@example.com", "password123", "+1234567890", "123 Elm Street", 3000);
    Buyer buyer2 = new Buyer(2, "Jane Smith", "jane.smith@example.com", "mypassword", "+9876543210", "456 Oak Avenue", 4000);
    Seller seller1 = new Seller(1, "Alice Blue", "alice.blue@example.com", "alicepass", "+1029384756", "101 Red Street", 10000, "ID12345A", LocalDate.of(2023, 1, 1), "Description about Alice", "123456789A");
    Seller seller2 = new Seller(2, "Bob Green", "bob.green@example.com", "bobpassword", "+5647382910", "202 Green Lane", 15000, "ID67890B", LocalDate.of(2023, 2, 2), "Description about Bob", "987654321B");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();

        when(buyerRepository.findByEmail("john.doe@example.com")).thenReturn(buyer1);
        when(buyerRepository.findByEmail("jane.smith@example.com")).thenReturn(buyer2);
        when(sellerRepository.findByEmail("alice.blue@example.com")).thenReturn(seller1);
        when(sellerRepository.findByEmail("bob.green@example.com")).thenReturn(seller2);
    }

    @Test
    public void testValidSignIn() throws Exception {
        String signInRequestJson = "{\"email\":\"john.doe@example.com\",\"password\":\"password123\"}";

        mockMvc.perform(MockMvcRequestBuilders
            .post("/signin")
            .contentType(MediaType.APPLICATION_JSON)
            .content(signInRequestJson))
            .andExpect(status().isOk());
    }

    @Test
    public void testInvalidEmail() throws Exception {
        String signInRequestJson = "{\"email\":\"invalid_email@example.com\",\"password\":\"pass\"}";

        mockMvc.perform(MockMvcRequestBuilders
            .post("/signin")
            .contentType(MediaType.APPLICATION_JSON)
            .content(signInRequestJson))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testInvalidPassword() throws Exception {
        String signInRequestJson = "{\"email\":\"john.doe@example.com\",\"password\":\"invalid_pass\"}";

        mockMvc.perform(MockMvcRequestBuilders
            .post("/signin")
            .contentType(MediaType.APPLICATION_JSON)
            .content(signInRequestJson))
            .andExpect(status().isUnauthorized());
    }

    @Test
    public void testInvalidFormat() throws Exception {
        String signInRequestJson = "{\"email\":\"john.doe@example\",\"password\":\"password123\"}";

        mockMvc.perform(MockMvcRequestBuilders
            .post("/signin")
            .contentType(MediaType.APPLICATION_JSON)
            .content(signInRequestJson))
            .andExpect(status().isBadRequest());
    }

}
