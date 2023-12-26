package com.gameshub.requests;

import com.fasterxml.jackson.databind.*;
import com.gameshub.controller.request.AdminController;
import com.gameshub.service.request.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;

@WebMvcTest(AdminController.class)
public class ProductRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRequestService productRequestService;

    private String convertToJson(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

//    @Test
//    public void createDigitalProduct_Success() throws Exception {
//        DigitalProductRequestDTO dto = new DigitalProductRequestDTO();
//
//        dto.setRequestType("Type");
//        dto.setCategory("CAT");
//        dto.setStatus("STAT");
//        dto.setCount(10);
//        dto.setDescription("DESC");
//        dto.setPrice(100);
//        dto.setTitle("TITLE");
//        dto.setSellerId(1);
//        dto.setCode("CODE");
//
//        String requestJson = convertToJson(dto);
//
//        Mockito.doNothing().when(productRequestService).saveProductRequest(dto);
//
//        mockMvc.perform(post("/product-request/create/digital")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestJson))
//                .andExpect(status().isForbidden());
//    }

//    @Test
//    public void createPhysicalProduct_Success() throws Exception {
//        PhysicalProductRequestDTO dto = new PhysicalProductRequestDTO();
//
//        dto.setRequestType("Type");
//        dto.setCategory("CAT");
//        dto.setStatus("STAT");
//        dto.setCount(10);
//        dto.setDescription("DESC");
//        dto.setPrice(100);
//        dto.setTitle("TITLE");
//        dto.setSellerId(1);
//
//        String requestJson = convertToJson(dto);
//
//        Mockito.doNothing().when(productRequestService).saveProductRequest(dto);
//
//        mockMvc.perform(post("/product-request/create/physical")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestJson))
//                .andExpect(status().isForbidden());
//    }

}
