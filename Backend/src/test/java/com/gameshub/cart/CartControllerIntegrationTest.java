package com.gameshub.cart;

import com.fasterxml.jackson.databind.*;
import com.gameshub.controller.cart.*;
import com.gameshub.controller.DTO.cart.*;
import com.gameshub.service.cart.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CartControllerIntegrationTest {

    private MockMvc mockMvc;

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController controller;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    // Test for POST /physical/addOrUpdate
    @Test
    void testAddOrUpdatePhysicalCartItem() throws Exception {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setBuyerID(1);
        cartDTO.setProductID(2);
        cartDTO.setCount(3);

        mockMvc.perform(post("/cart/physical/addOrUpdate")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cartDTO)))
                .andExpect(status().isOk());

        verify(cartService).addOrUpdatePhysicalCartItem(1, 2, 3);
    }

    // Test for POST /digital/addOrUpdate
    @Test
    void testAddOrUpdateDigitalCartItem() throws Exception {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setBuyerID(1);
        cartDTO.setProductID(2);
        cartDTO.setCount(3);

        mockMvc.perform(post("/cart/digital/addOrUpdate")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cartDTO)))
                .andExpect(status().isOk());

        verify(cartService).addOrUpdateDigitalCartItem(1, 2, 3);
    }

    // Test for DELETE /physical/remove
    @Test
    void testRemovePhysicalCartItem() throws Exception {
        mockMvc.perform(delete("/cart/physical/remove")
                        .param("buyerId", "1")
                        .param("productId", "2"))
                .andExpect(status().isOk());

        verify(cartService).removePhysicalCartItem(1, 2);
    }

    // Test for DELETE /digital/remove
    @Test
    void testRemoveDigitalCartItem() throws Exception {
        mockMvc.perform(delete("/cart/digital/remove")
                        .param("buyerId", "1")
                        .param("productId", "2"))
                .andExpect(status().isOk());

        verify(cartService).removeDigitalCartItem(1, 2);
    }

    // Test for GET /physical
    @Test
    void testGetPhysicalCartItems() throws Exception {
        when(cartService.getPhysicalCartItems(1)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/cart/physical")
                        .param("buyerId", "1"))
                .andExpect(status().isOk());

        verify(cartService).getPhysicalCartItems(1);
    }

    // Test for GET /digital
    @Test
    void testGetDigitalCartItems() throws Exception {
        when(cartService.getDigitalCartItems(1)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/cart/digital")
                        .param("buyerId", "1"))
                .andExpect(status().isOk());

        verify(cartService).getDigitalCartItems(1);
    }

    // Test for POST /physical/addOrUpdate with exception
    @Test
    void testAddOrUpdatePhysicalCartItemException() throws Exception {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setBuyerID(1);
        cartDTO.setProductID(2);
        cartDTO.setCount(3);
        doThrow(new RuntimeException("Server Error")).when(cartService)
                .addOrUpdatePhysicalCartItem(anyInt(), anyInt(), anyInt());

        mockMvc.perform(post("/cart/physical/addOrUpdate")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cartDTO)))
                .andExpect(status().isInternalServerError());

        verify(cartService).addOrUpdatePhysicalCartItem(1, 2, 3);
    }

    // Test for POST /digital/addOrUpdate with exception
    @Test
    void testAddOrUpdateDigitalCartItemException() throws Exception {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setBuyerID(1);
        cartDTO.setProductID(2);
        cartDTO.setCount(3);
        doThrow(new RuntimeException("Server Error")).when(cartService)
                .addOrUpdateDigitalCartItem(anyInt(), anyInt(), anyInt());

        mockMvc.perform(post("/cart/digital/addOrUpdate")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cartDTO)))
                .andExpect(status().isInternalServerError());

        verify(cartService).addOrUpdateDigitalCartItem(1, 2, 3);
    }

    // Test for DELETE /physical/remove with exception
    @Test
    void testRemovePhysicalCartItemException() throws Exception {
        doThrow(new RuntimeException("Server Error")).when(cartService)
                .removePhysicalCartItem(anyInt(), anyInt());

        mockMvc.perform(delete("/cart/physical/remove")
                        .param("buyerId", "1")
                        .param("productId", "2"))
                .andExpect(status().isInternalServerError());

        verify(cartService).removePhysicalCartItem(1, 2);
    }

    // Test for DELETE /digital/remove with exception
    @Test
    void testRemoveDigitalCartItemException() throws Exception {
        doThrow(new RuntimeException("Server Error")).when(cartService)
                .removeDigitalCartItem(anyInt(), anyInt());

        mockMvc.perform(delete("/cart/digital/remove")
                        .param("buyerId", "1")
                        .param("productId", "2"))
                .andExpect(status().isInternalServerError());

        verify(cartService).removeDigitalCartItem(1, 2);
    }

    // Test for GET /physical with exception
    @Test
    void testGetPhysicalCartItemsException() throws Exception {
        doThrow(new RuntimeException("Server Error")).when(cartService)
                .getPhysicalCartItems(anyInt());

        mockMvc.perform(get("/cart/physical")
                        .param("buyerId", "1"))
                .andExpect(status().isInternalServerError());

        verify(cartService).getPhysicalCartItems(1);
    }

    // Test for GET /digital with exception
    @Test
    void testGetDigitalCartItemsException() throws Exception {
        doThrow(new RuntimeException("Server Error")).when(cartService)
                .getDigitalCartItems(anyInt());

        mockMvc.perform(get("/cart/digital")
                        .param("buyerId", "1"))
                .andExpect(status().isInternalServerError());

        verify(cartService).getDigitalCartItems(1);
    }

}
