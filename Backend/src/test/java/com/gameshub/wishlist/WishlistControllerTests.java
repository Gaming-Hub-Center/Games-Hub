package com.gameshub.wishlist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameshub.controller.DTO.Wishlist.WishlistDTO;
import com.gameshub.controller.wishlist.WishlistController;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.user.BuyerDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.user.BuyerRepository;
import com.gameshub.repository.user.SellerRepository;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
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


    private BuyerDAO testBuyer;
    private SellerDAO testSeller;
    private PhysicalProductDAO testProductPhysical;
    private DigitalProductDAO testProductDigital;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private SellerRepository sellerRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void test_AddPhysicalWishlistItem() throws Exception {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setBuyerID(1);
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
        wishlistDTO.setBuyerID(1);
        wishlistDTO.setProductID(2);

        mockMvc.perform(post("/wishlist/digital/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(wishlistDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Digital wishlist item added successfully"));
    }

//    @Test
//    void testRemovePhysicalWishlistItem() throws Exception {
//        mockMvc.perform(delete("/wishlist/physical/remove")
//                        .param("buyerId", "1")
//                        .param("productId", "2"))
//                .andExpect(status().isOk());
//    }


//
//
//    @Test
//    void testRemoveDigitalCartItem() throws Exception {
//        mockMvc.perform(delete("/cart/digital/remove")
//                        .param("buyerId", "1")
//                        .param("productId", "2"))
//                .andExpect(status().isOk());
//
//        verify(cartService).removeDigitalCartItem(1, 2);
//    }
//
//    @Test
//    void testGetPhysicalCartItems() throws Exception {
//        when(cartService.getPhysicalCartItems(1)).thenReturn(Collections.emptyList());
//
//        mockMvc.perform(get("/cart/physical")
//                        .param("buyerId", "1"))
//                .andExpect(status().isOk());
//
//        verify(cartService).getPhysicalCartItems(1);
//    }
//
//    @Test
//    void testGetDigitalCartItems() throws Exception {
//        when(cartService.getDigitalCartItems(1)).thenReturn(Collections.emptyList());
//
//        mockMvc.perform(get("/cart/digital")
//                        .param("buyerId", "1"))
//                .andExpect(status().isOk());
//
//        verify(cartService).getDigitalCartItems(1);
//    }
//
//    @Test
//    void testAddOrUpdatePhysicalCartItemException() throws Exception {
//        WishlistDTO cartDTO = new WishlistDTO();
//        cartDTO.setBuyerID(1);
//        cartDTO.setProductID(2);
//        cartDTO.setCount(3);
//        doThrow(new RuntimeException("Server Error")).when(cartService)
//                .addOrUpdatePhysicalCartItem(anyInt(), anyInt(), anyInt());
//
//        mockMvc.perform(post("/cart/physical/addOrUpdate")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(cartDTO)))
//                .andExpect(status().isInternalServerError());
//
//        verify(cartService).addOrUpdatePhysicalCartItem(1, 2, 3);
//    }
//
//    @Test
//    void testAddOrUpdateDigitalCartItemException() throws Exception {
//        WishlistDTO cartDTO = new WishlistDTO();
//        cartDTO.setBuyerID(1);
//        cartDTO.setProductID(2);
//        cartDTO.setCount(3);
//        doThrow(new RuntimeException("Server Error")).when(cartService)
//                .addOrUpdateDigitalCartItem(anyInt(), anyInt(), anyInt());
//
//        mockMvc.perform(post("/cart/digital/addOrUpdate")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(cartDTO)))
//                .andExpect(status().isInternalServerError());
//
//        verify(cartService).addOrUpdateDigitalCartItem(1, 2, 3);
//    }
//
//    @Test
//    void testRemovePhysicalCartItemException() throws Exception {
//        doThrow(new RuntimeException("Server Error")).when(cartService)
//                .removePhysicalCartItem(anyInt(), anyInt());
//
//        mockMvc.perform(delete("/cart/physical/remove")
//                        .param("buyerId", "1")
//                        .param("productId", "2"))
//                .andExpect(status().isInternalServerError());
//
//        verify(cartService).removePhysicalCartItem(1, 2);
//    }
//
//    @Test
//    void testRemoveDigitalCartItemException() throws Exception {
//        doThrow(new RuntimeException("Server Error")).when(cartService)
//                .removeDigitalCartItem(anyInt(), anyInt());
//
//        mockMvc.perform(delete("/cart/digital/remove")
//                        .param("buyerId", "1")
//                        .param("productId", "2"))
//                .andExpect(status().isInternalServerError());
//
//        verify(cartService).removeDigitalCartItem(1, 2);
//    }
//
//    @Test
//    void testGetPhysicalCartItemsException() throws Exception {
//        doThrow(new RuntimeException("Server Error")).when(cartService)
//                .getPhysicalCartItems(anyInt());
//
//        mockMvc.perform(get("/cart/physical")
//                        .param("buyerId", "1"))
//                .andExpect(status().isInternalServerError());
//
//        verify(cartService).getPhysicalCartItems(1);
//    }
//
//    @Test
//    void testGetDigitalCartItemsException() throws Exception {
//        doThrow(new RuntimeException("Server Error")).when(cartService)
//                .getDigitalCartItems(anyInt());
//
//        mockMvc.perform(get("/cart/digital")
//                        .param("buyerId", "1"))
//                .andExpect(status().isInternalServerError());
//
//        verify(cartService).getDigitalCartItems(1);
//    }
}
