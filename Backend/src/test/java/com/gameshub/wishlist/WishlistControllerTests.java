package com.gameshub.wishlist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameshub.controller.DTO.Wishlist.WishlistDTO;
import com.gameshub.controller.wishlist.WishlistController;
import com.gameshub.model.wishlist.DigitalWishlistDAO;
import com.gameshub.model.wishlist.PhysicalWishlistDAO;
import com.gameshub.repository.wishlist.DigitalWishlistRepository;
import com.gameshub.repository.wishlist.PhysicalWishlistRepository;
import com.gameshub.service.wishlist.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class WishlistControllerTests {
    private MockMvc mockMvc;
    @Mock
    private WishlistService wishlistService;
    @InjectMocks
    private WishlistController controller;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private DigitalWishlistRepository digitalWishlistRepository;
    @Autowired
    private PhysicalWishlistRepository physicalWishlistRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void test_AddPhysicalWishlistItem() throws Exception {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setBuyerID(2);
        wishlistDTO.setProductID(2);

        mockMvc.perform(post("/wishlist/physical/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(wishlistDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Physical wishlist item added successfully"));
    }

    @Test
    void test_AddDigitalWishlistItem() throws Exception {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setBuyerID(2);
        wishlistDTO.setProductID(2);

        mockMvc.perform(post("/wishlist/digital/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(wishlistDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Digital wishlist item added successfully"));
    }

    @Test
    void test_AddPhysicalWishlistItem1() throws Exception {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setBuyerID(1);
        wishlistDTO.setProductID(2);

        mockMvc.perform(post("/wishlist/physical/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wishlistDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Physical wishlist item added successfully"));

        verify(wishlistService).addPhysicalWishlistProduct(1, 2);
    }

    @Test
    void test_AddDigitalWishlistItem1() throws Exception {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setBuyerID(1);
        wishlistDTO.setProductID(2);

        mockMvc.perform(post("/wishlist/digital/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wishlistDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Digital wishlist item added successfully"));

        verify(wishlistService).addDigitalWishlistProduct(1, 2);
    }

    @Test
    void test_DeletePhysicalWishlistItem() throws Exception {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setBuyerID(1);
        wishlistDTO.setProductID(2);

        mockMvc.perform(delete("/wishlist/physical/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wishlistDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Physical wishlist item deleted successfully"));

        verify(wishlistService).deletePhysicalWishlistProduct(1, 2);
    }

    @Test
    void test_DeleteDigitalWishlistItem() throws Exception {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setBuyerID(1);
        wishlistDTO.setProductID(2);

        mockMvc.perform(delete("/wishlist/digital/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wishlistDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Digital wishlist item deleted successfully"));

        verify(wishlistService).deleteDigitalWishlistProduct(1, 2);
    }

    @Test
    void test_GetPhysicalWishlistItems() throws Exception {
        when(wishlistService.getPhysicalWishlistItems(1)).thenReturn(List.of(new PhysicalWishlistDAO()));

        mockMvc.perform(get("/wishlist/physical")
                        .param("buyerId", "1"))
                .andExpect(status().isOk());

        verify(wishlistService).getPhysicalWishlistItems(1);
    }

    @Test
    void test_GetDigitalWishlistItems() throws Exception {
        when(wishlistService.getDigitalWishlistItems(1)).thenReturn(List.of(new DigitalWishlistDAO()));

        mockMvc.perform(get("/wishlist/digital")
                        .param("buyerId", "1"))
                .andExpect(status().isOk());

        verify(wishlistService).getDigitalWishlistItems(1);
    }

    @Test
    void test_AddPhysicalWishlistItem_InvalidBuyerProduct() throws Exception {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setBuyerID(999); // Assuming this buyer does not exist
        wishlistDTO.setProductID(2);

        doThrow(new DuplicateKeyException("Product already exists in the wishlist"))
                .when(wishlistService).addPhysicalWishlistProduct(999, 2);

        mockMvc.perform(post("/wishlist/physical/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wishlistDTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Error: No buyer or Physical_Product exists with that ID"));
    }

    @Test
    void test_AddDigitalWishlistItem_InvalidBuyerProduct() throws Exception {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setBuyerID(999); // Assuming this buyer does not exist
        wishlistDTO.setProductID(2);

        doThrow(new DuplicateKeyException("Product already exists in the wishlist"))
                .when(wishlistService).addDigitalWishlistProduct(999, 2);

        mockMvc.perform(post("/wishlist/digital/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wishlistDTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Error: No buyer or Digital_Product exists with that ID"));
    }
}
