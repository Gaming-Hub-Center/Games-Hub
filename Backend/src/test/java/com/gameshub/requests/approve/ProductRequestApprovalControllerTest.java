package com.gameshub.requests.approve;

import com.gameshub.controller.request.ProductRequestApprovalController;
import com.gameshub.exception.ResourceNotFoundException;
import com.gameshub.service.request.approve_product_update_and_create.ProductRequestApproveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class ProductRequestApprovalControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductRequestApproveService productRequestApproveService;

    @InjectMocks
    private ProductRequestApprovalController productRequestApprovalController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productRequestApprovalController).build();
    }

    @Test
    void approveProductCreation_Success() throws Exception {
        mockMvc.perform(post("/products/approve/create")
                        .param("productType", "physical")
                        .param("requestId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Product creation request approved successfully."));
    }

    @Test
    void approveProductCreation_NotFound() throws Exception {
        doThrow(new ResourceNotFoundException("Not Found")).when(productRequestApproveService).approveProductCreation(anyString(), anyInt());

        mockMvc.perform(post("/products/approve/create")
                        .param("productType", "physical")
                        .param("requestId", "1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void approveProductCreation_BadRequest() throws Exception {
        doThrow(new IllegalArgumentException("Bad Request")).when(productRequestApproveService).approveProductCreation(anyString(), anyInt());

        mockMvc.perform(post("/products/approve/create")
                        .param("productType", "physical")
                        .param("requestId", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Bad Request"));
    }

    @Test
    void approveProductUpdate_Success() throws Exception {
        mockMvc.perform(post("/products/approve/update")
                        .param("productType", "physical")
                        .param("requestId", "1")
                        .param("productId", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Product update request approved successfully."));
    }

    @Test
    void approveProductUpdate_NotFound() throws Exception {
        doThrow(new ResourceNotFoundException("Not Found")).when(productRequestApproveService).approveProductUpdate(anyString(), anyInt(), anyInt());

        mockMvc.perform(post("/products/approve/update")
                        .param("productType", "physical")
                        .param("requestId", "1")
                        .param("productId", "2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void approveProductUpdate_BadRequest() throws Exception {
        doThrow(new IllegalArgumentException("Bad Request")).when(productRequestApproveService).approveProductUpdate(anyString(), anyInt(), anyInt());

        mockMvc.perform(post("/products/approve/update")
                        .param("productType", "physical")
                        .param("requestId", "1")
                        .param("productId", "2"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Bad Request"));
    }


}
