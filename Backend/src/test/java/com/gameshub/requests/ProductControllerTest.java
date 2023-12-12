package com.gameshub.requests;

import com.gameshub.controller.DTO.request.DigitalProductRequestDTO;
import com.gameshub.controller.DTO.request.PhysicalProductRequestDTO;
import com.gameshub.controller.request.ProductRequestController;
import com.gameshub.service.request.ProductRequestService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProductRequestController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRequestService productRequestService;

    private String convertToJson(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    public void createDigitalProduct_Success() throws Exception {
        DigitalProductRequestDTO dto = new DigitalProductRequestDTO();

        dto.setRequestType("Type");
        dto.setCategory("CAT");
        dto.setStatus("STAT");
        dto.setCount(10);
        dto.setDescription("DESC");
        dto.setPrice(100);
        dto.setTitle("TITLE");
        dto.setSellerId(1);
        dto.setCode("CODE");

        String requestJson = convertToJson(dto);

        Mockito.doNothing().when(productRequestService).saveProductRequest(dto);

        mockMvc.perform(post("/product-request/create/digital")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isForbidden());
    }

    @Test
    public void createPhysicalProduct_Success() throws Exception {
        PhysicalProductRequestDTO dto = new PhysicalProductRequestDTO();

        dto.setRequestType("Type");
        dto.setCategory("CAT");
        dto.setStatus("STAT");
        dto.setCount(10);
        dto.setDescription("DESC");
        dto.setPrice(100);
        dto.setTitle("TITLE");
        dto.setSellerId(1);

        String requestJson = convertToJson(dto);

        Mockito.doNothing().when(productRequestService).saveProductRequest(dto);

        mockMvc.perform(post("/product-request/create/physical")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isForbidden());
    }


}
