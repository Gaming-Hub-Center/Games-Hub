package com.gameshub.DataAccessLayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameshub.config.JWTGenerator;
import com.gameshub.controller.DTO.CartDTO;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.user.BuyerDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CartControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    JWTGenerator jwt;

    @Autowired
    private BuyerRepository buyerRepository;

    private String token;
    private BuyerDAO TestBuyer;

    private SellerDAO TestSeller;
    private PhysicalProductDAO TestProductPhysical;
    private DigitalProductDAO TestProductDigital;

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private PhysicalProductRepository physicalProductRepository;
    @Autowired
    private DigitalProductRepository digitalProductRepository;
    @Autowired
    private PhysicalCartRepository physicalCartRepository;
    @Autowired
    private DigitalCartRepository digitalCartRepository;

    @BeforeEach
    void setUp() {
        // Create a test seller
        TestSeller = new SellerDAO(100, "Test Seller", "seller@example.com", "password", "1234567890", "Seller Address", 100.0f, "123456789", LocalDate.now(), "Test Description", "VAT123456");
        sellerRepository.save(TestSeller);

        // Create a test buyer
        TestBuyer = new BuyerDAO(4, "buyer", "buyer", "email@gmail.com", "pw", "0100", 50);
        buyerRepository.save(TestBuyer);

        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Create a test product and associate it with the test seller
        TestProductPhysical = new PhysicalProductDAO(4, 50.0f, 10, "Test Product", "Description", "Category", 100, date);
        physicalProductRepository.save(TestProductPhysical);

        TestProductDigital = new DigitalProductDAO(4, 50.0f, 10, "Test Product", "Description", "Category", 100, date, "888");
        digitalProductRepository.save(TestProductDigital);

        // Generate a real token for the test user
        token = "Bearer " + jwt.createToken(TestBuyer.getEmail());
    }

    @AfterEach
    void tearDown() {
        // Clean up any resources after each test
        buyerRepository.deleteAll();
        sellerRepository.deleteAll();
        physicalProductRepository.deleteAll();
        digitalProductRepository.deleteAll();
        physicalCartRepository.deleteAll();
        digitalCartRepository.deleteAll();
    }

    @Test
    void whenAddPhysicalCartItemWithInvalidData_thenReturnsServerError() throws Exception {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setBuyerID(1);
        cartDTO.setProductID(101);
        cartDTO.setCount(5);

        mockMvc.perform(post("/cart/physical/addOrUpdate")
                        .header("Authorization", token)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cartDTO)))
                .andExpect(status().isInternalServerError());
    }

//    @Test
//    void addSuccessfullyPhysicalCartItem() throws Exception {
//        CartDTO cartDTO = new CartDTO();
//        cartDTO.setBuyerID(TestBuyer.getId());
//        cartDTO.setProductID(TestProductPhysical.getProductID());
//        cartDTO.setCount(5);
//
//        System.out.println(TestBuyer.getId());
//
//        mockMvc.perform(post("/cart/physical/addOrUpdate")
//                        .header("Authorization", token)
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(cartDTO)))
//                .andExpect(status().isOk());
//
//        BuyerDAO retrievedBuyer = buyerRepository.findById(TestBuyer.getId()).orElseThrow();
//        assertEquals(retrievedBuyer.getId(), TestBuyer.getId());
//
//        PhysicalProductDAO retrievedProduct = physicalProductRepository.findById(TestProductPhysical.getProductID()).orElseThrow();
//        assertEquals(retrievedProduct.getProductID(), TestProductPhysical.getProductID());
//    }

    @Test
    void unAuthorized() throws Exception {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setBuyerID(5);
        cartDTO.setProductID(1);
        cartDTO.setCount(5);

        mockMvc.perform(post("/cart/physical/addOrUpdate")
                        .header("Authorization", "InvalidToken")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cartDTO)))
                .andExpect(status().isUnauthorized());
    }
}
