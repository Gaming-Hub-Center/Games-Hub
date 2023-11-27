package com.gameshub;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.*;
import com.gameshub.Controller.*;
import com.gameshub.Model.Users.*;
import com.gameshub.Service.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.setup.*;

import java.time.*;

public class SignInTests {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private BuyerDetailsService buyerDetailsService;
    @Mock
    private SellerDetailsService sellerDetailsService;

    @InjectMocks
    private AuthController authController;

    BuyerDAO buyerDAO1 = new BuyerDAO(1, "John Doe", "john.doe@example.com", "password123", "+1234567890", "123 Elm Street", 3000);
    BuyerDAO buyerDAO2 = new BuyerDAO(2, "Jane Smith", "jane.smith@example.com", "mypassword", "+9876543210", "456 Oak Avenue", 4000);
    SellerDAO sellerDAO1 = new SellerDAO(1, "Alice Blue", "alice.blue@example.com", "alicepass", "+1029384756", "101 Red Street", 10000, "ID12345A", LocalDate.of(2023, 1, 1), "Description about Alice", "123456789A");
    SellerDAO sellerDAO2 = new SellerDAO(2, "Bob Green", "bob.green@example.com", "bobpassword", "+5647382910", "202 Green Lane", 15000, "ID67890B", LocalDate.of(2023, 2, 2), "Description about Bob", "987654321B");

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();

        when(buyerDetailsService.getByEmail("john.doe@example.com")).thenReturn(buyerDAO1);
        when(buyerDetailsService.getByEmail("jane.smith@example.com")).thenReturn(buyerDAO2);
        when(sellerDetailsService.getByEmail("alice.blue@example.com")).thenReturn(sellerDAO1);
        when(sellerDetailsService.getByEmail("bob.green@example.com")).thenReturn(sellerDAO2);
    }

    @Test
    public void testValidSignIn() throws Exception {
        String signInRequestJson = "{\"email\":\"john.doe@example.com\",\"password\":\"password123\"}";

        mockMvc.perform(MockMvcRequestBuilders
            .post("/login")
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
