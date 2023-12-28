package com.gameshub.product;

import com.gameshub.controller.*;
import com.gameshub.controller.DTO.*;
import com.gameshub.service.product.ProductService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import java.time.LocalDate;
import java.util.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.gameshub.controller.DTO.PhysicalProductDTO;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasItems;

@SpringBootTest
public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;
    @Mock
    private ProductService productService;
    private MockMvc mockMvc;

    @AfterEach
    void cleanUp() {
        mockMvc = null;
    }

    @Test
    public void testGetPhysicalProductByID() throws Exception {
        int productId = 1;
        PhysicalProductDTO mockProductDTO = new PhysicalProductDTO();
        mockProductDTO.setId(productId);
        mockProductDTO.setPrice(105);
        mockProductDTO.setDescription("Mock Description");
        mockProductDTO.setTitle("Mock Title");
        mockProductDTO.setCount(5);
        mockProductDTO.setSellerID(6);
        mockProductDTO.setPostDate(LocalDate.parse("2023-12-08"));

        when(productService.getPhysicalByID(productId)).thenReturn(mockProductDTO);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(get("/product/physical/getdetails")
                        .param("ID", String.valueOf(productId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(productId))
                .andExpect(jsonPath("$.price").value(105))
                .andExpect(jsonPath("$.description").value("Mock Description"))
                .andExpect(jsonPath("$.title").value("Mock Title"))
                .andExpect(jsonPath("$.count").value(5))
                .andExpect(jsonPath("$.sellerID").value(6))
                .andExpect(jsonPath("$.postDate", hasItems(2023, 12, 8)))
                .andExpect(jsonPath("$.image").doesNotExist());

        verify(productService, times(1)).getPhysicalByID(productId);
    }


    @Test
    public void testGetDigitalProductByID() throws Exception {
        int productId = 1;
        DigitalProductDTO mockProductDTO = new DigitalProductDTO();
        mockProductDTO.setId(productId);
        mockProductDTO.setPrice(105);
        mockProductDTO.setDescription("Mock Description");
        mockProductDTO.setTitle("Mock Title");
        mockProductDTO.setCount(5);
        mockProductDTO.setSellerID(6);
        mockProductDTO.setPostDate(LocalDate.parse("2023-12-08"));
        mockProductDTO.setCode("Mock Code");

        when(productService.getDigitalByID(productId)).thenReturn(mockProductDTO);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(get("/product/digital/getdetails")
                        .param("ID", String.valueOf(productId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(productId))
                .andExpect(jsonPath("$.price").value(105))
                .andExpect(jsonPath("$.description").value("Mock Description"))
                .andExpect(jsonPath("$.title").value("Mock Title"))
                .andExpect(jsonPath("$.count").value(5))
                .andExpect(jsonPath("$.sellerID").value(6))
                .andExpect(jsonPath("$.postDate", hasItems(2023, 12, 8)))
                .andExpect(jsonPath("$.image").doesNotExist())
                .andExpect(jsonPath("$.code").value("Mock Code"));

        verify(productService, times(1)).getDigitalByID(productId);
    }

    @Test
    public void testSearchPhysicalProduct() throws Exception {
        String searchKey = "Mock";
        List<ProductBriefDTO> mockProductList = new ArrayList<>();

        ProductBriefDTO mockProduct1 = new ProductBriefDTO();
        mockProduct1.setId(1);
        mockProduct1.setTitle("Mock Title 1");
        mockProduct1.setPrice(50);
        mockProduct1.setUrl("123");

        ProductBriefDTO mockProduct2 = new ProductBriefDTO();
        mockProduct2.setId(2);
        mockProduct2.setTitle("Mock Title 2");
        mockProduct2.setPrice(75);
        mockProduct2.setUrl("456");

        mockProductList.add(mockProduct1);
        mockProductList.add(mockProduct2);

        when(productService.searchPhysicalByTitle(searchKey)).thenReturn(mockProductList);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(get("/product/physical/search")
                        .param("key", searchKey)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Mock Title 1"))
                .andExpect(jsonPath("$[0].price").value(50))
                .andExpect(jsonPath("$[0].url").value("123"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Mock Title 2"))
                .andExpect(jsonPath("$[1].price").value(75))
                .andExpect(jsonPath("$[1].url").value("456"));

        verify(productService, times(1)).searchPhysicalByTitle(searchKey);
    }

    @Test
    void testSearchDigitalProduct() throws Exception {
        String searchKey = "Mock";
        List<ProductBriefDTO> mockProductList = new ArrayList<>();

        ProductBriefDTO mockProduct1 = new ProductBriefDTO();
        mockProduct1.setId(1);
        mockProduct1.setTitle("Mock Title 1");
        mockProduct1.setPrice(50);
        mockProduct1.setUrl("123");

        ProductBriefDTO mockProduct2 = new ProductBriefDTO();
        mockProduct2.setId(2);
        mockProduct2.setTitle("Mock Title 2");
        mockProduct2.setPrice(75);
        mockProduct2.setUrl("456");

        mockProductList.add(mockProduct1);
        mockProductList.add(mockProduct2);

        when(productService.searchDigitalByTitle(searchKey)).thenReturn(mockProductList);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(get("/product/digital/search")
                        .param("key", searchKey)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Mock Title 1"))
                .andExpect(jsonPath("$[0].price").value(50))
                .andExpect(jsonPath("$[0].url").value("123"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Mock Title 2"))
                .andExpect(jsonPath("$[1].price").value(75))
                .andExpect(jsonPath("$[1].url").value("456"));
        verify(productService, times(1)).searchDigitalByTitle(searchKey);
    }

    @Test
    public void testFilterPhysical_ValidFilters() throws Exception {
        List<ProductBriefDTO> mockProductList = new ArrayList<>();

        ProductBriefDTO mockProduct1 = new ProductBriefDTO();
        mockProduct1.setId(1);
        mockProduct1.setTitle("Mock Title 1");
        mockProduct1.setPrice(50.5F);
        mockProduct1.setUrl("123");

        ProductBriefDTO mockProduct2 = new ProductBriefDTO();
        mockProduct2.setId(2);
        mockProduct2.setTitle("Mock Title 2");
        mockProduct2.setPrice(75.6F);
        mockProduct2.setUrl("456");

        mockProductList.add(mockProduct1);
        mockProductList.add(mockProduct2);

        when(productService.filterPhysical(0F, 75F, "mouse")).thenReturn(List.of(mockProductList.get(0)));
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(get("/product/physical/filter")
                        //.param("lowerBound", "0F")
                        .param("upperBound", "75F")
                        .param("category", "mouse")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Mock Title 1"))
                .andExpect(jsonPath("$[0].price").value(50.5F))
                .andExpect(jsonPath("$[0].url").value("123"));

        verify(productService, times(1)).filterPhysical(0F, 75F, "mouse");
    }

    @Test
    public void testFilterPhysical_ValidFilters2() throws Exception {
        List<ProductBriefDTO> mockProductList = new ArrayList<>();

        ProductBriefDTO mockProduct1 = new ProductBriefDTO();
        mockProduct1.setId(1);
        mockProduct1.setTitle("Mock Title 1");
        mockProduct1.setPrice(50.5F);
        mockProduct1.setUrl("123");

        ProductBriefDTO mockProduct2 = new ProductBriefDTO();
        mockProduct2.setId(2);
        mockProduct2.setTitle("Mock Title 2");
        mockProduct2.setPrice(75.6F);
        mockProduct2.setUrl("456");

        mockProductList.add(mockProduct1);
        mockProductList.add(mockProduct2);

        when(productService.filterPhysical(0F, (float) Integer.MAX_VALUE, "mouse")).thenReturn(mockProductList);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(get("/product/physical/filter")
                        .param("lowerBound", "0F")
                        //.param("upperBound", "75.6F")
                        .param("category", "mouse")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Mock Title 1"))
                .andExpect(jsonPath("$[0].price").value(50.5F))
                .andExpect(jsonPath("$[0].url").value(equalTo("123")))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Mock Title 2"))
                .andExpect(jsonPath("$[1].price").value(75.6F))
                .andExpect(jsonPath("$[1].url").value(equalTo("456")));

        verify(productService, times(1)).filterPhysical(0F, (float) Integer.MAX_VALUE, "mouse");
    }

    @Test
    public void testFilterDigital_ValidFilters() throws Exception {
        List<ProductBriefDTO> mockProductList = new ArrayList<>();

        ProductBriefDTO mockProduct1 = new ProductBriefDTO();
        mockProduct1.setId(1);
        mockProduct1.setTitle("Digital Mock Title 1");
        mockProduct1.setPrice(100.5F);
        mockProduct1.setUrl("101112");

        ProductBriefDTO mockProduct2 = new ProductBriefDTO();
        mockProduct2.setId(2);
        mockProduct2.setTitle("Digital Mock Title 2");
        mockProduct2.setPrice(150.6F);
        mockProduct2.setUrl("131415");

        mockProductList.add(mockProduct1);
        mockProductList.add(mockProduct2);

        when(productService.filterDigital(0F, 150F, "action")).thenReturn(List.of(mockProductList.get(0)));
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(get("/product/digital/filter")
                        //.param("lowerBound", "100F")
                        .param("upperBound", "150F")
                        .param("category", "action")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Digital Mock Title 1"))
                .andExpect(jsonPath("$[0].price").value(100.5F))
                .andExpect(jsonPath("$[0].url").value("101112"));

        verify(productService, times(1)).filterDigital(0F, 150F, "action");
    }

    @Test
    public void testFilterDigital_ValidFilters2() throws Exception {
        List<ProductBriefDTO> mockProductList = new ArrayList<>();

        ProductBriefDTO mockProduct1 = new ProductBriefDTO();
        mockProduct1.setId(1);
        mockProduct1.setTitle("Digital Mock Title 1");
        mockProduct1.setPrice(100.5F);
        mockProduct1.setUrl("101112");

        ProductBriefDTO mockProduct2 = new ProductBriefDTO();
        mockProduct2.setId(2);
        mockProduct2.setTitle("Digital Mock Title 2");
        mockProduct2.setPrice(150.6F);
        mockProduct2.setUrl("131415");

        mockProductList.add(mockProduct1);
        mockProductList.add(mockProduct2);

        when(productService.filterDigital(100.5F, (float) Integer.MAX_VALUE, "action")).thenReturn(mockProductList);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(get("/product/digital/filter")
                        .param("lowerBound", "100.5F") //lowerBound will be null
                        //.param("upperBound", "150.6F")
                        .param("category", "action")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Digital Mock Title 1"))
                .andExpect(jsonPath("$[0].price").value(100.5F))
                .andExpect(jsonPath("$[0].url").value("101112"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Digital Mock Title 2"))
                .andExpect(jsonPath("$[1].price").value(150.6F))
                .andExpect(jsonPath("$[1].url").value("131415"));
        verify(productService, times(1)).filterDigital(100.5F, (float) Integer.MAX_VALUE, "action");
    }

    @Test
    void testSortPhysical() throws Exception {
        boolean ascending = true;
        List<ProductBriefDTO> mockProductList = new ArrayList<>();

        ProductBriefDTO mockProduct1 = new ProductBriefDTO();
        mockProduct1.setId(1);
        mockProduct1.setTitle("Mock Title 1");
        mockProduct1.setPrice(50.5F);
        mockProduct1.setUrl("123");

        ProductBriefDTO mockProduct2 = new ProductBriefDTO();
        mockProduct2.setId(2);
        mockProduct2.setTitle("Mock Title 2");
        mockProduct2.setPrice(75.6F);
        mockProduct2.setUrl("456");

        mockProductList.add(mockProduct1);
        mockProductList.add(mockProduct2);

        when(productService.sortPhysical(ascending)).thenReturn(mockProductList);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(get("/product/physical/sort")
                        .param("ascending", String.valueOf(ascending))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Mock Title 1"))
                .andExpect(jsonPath("$[0].price").value(50.5F))
                .andExpect(jsonPath("$[0].url").value("123"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Mock Title 2"))
                .andExpect(jsonPath("$[1].price").value(75.6F))
                .andExpect(jsonPath("$[1].url").value("456"));

        verify(productService, times(1)).sortPhysical(ascending);
    }

    @Test
    void testSortDigital() throws Exception {
        boolean ascending = true;
        List<ProductBriefDTO> mockProductList = new ArrayList<>();

        ProductBriefDTO mockProduct1 = new ProductBriefDTO();
        mockProduct1.setId(1);
        mockProduct1.setTitle("Digital Mock Title 1");
        mockProduct1.setPrice(100.5F);
        mockProduct1.setUrl("101112");

        ProductBriefDTO mockProduct2 = new ProductBriefDTO();
        mockProduct2.setId(2);
        mockProduct2.setTitle("Digital Mock Title 2");
        mockProduct2.setPrice(150.6F);
        mockProduct2.setUrl("131415");

        mockProductList.add(mockProduct1);
        mockProductList.add(mockProduct2);

        when(productService.sortDigital(ascending)).thenReturn(mockProductList);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(get("/product/digital/sort")
                        .param("ascending", String.valueOf(ascending))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Digital Mock Title 1"))
                .andExpect(jsonPath("$[0].price").value(100.5F))
                .andExpect(jsonPath("$[0].url").value("101112"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Digital Mock Title 2"))
                .andExpect(jsonPath("$[1].price").value(150.6F))
                .andExpect(jsonPath("$[1].url").value("131415"));

        verify(productService, times(1)).sortDigital(ascending);
    }
}