package com.gameshub.products;

import com.gameshub.controller.DTO.product.DigitalProductDTO;
import com.gameshub.controller.DTO.product.PhysicalProductDTO;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.user.SellerRepository;
import com.gameshub.utils.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductMapperTest {

    private ProductMapper productMapper;

    @Mock
    private SellerRepository sellerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productMapper = new ProductMapper();
        productMapper.setRepository(sellerRepository);
    }

    @Test
    void testToProductDTODigitalProductDAO() {
        // Prepare test data
        DigitalProductDAO digitalProductDAO = new DigitalProductDAO();
        digitalProductDAO.setId(1);
        digitalProductDAO.setPrice(10);
        digitalProductDAO.setTitle("Title");
        digitalProductDAO.setPostDate(LocalDate.now());
        digitalProductDAO.setDescription("Description");
        digitalProductDAO.setCode("CODE");
        digitalProductDAO.setCategory("CATEGORY");

        // Mock behavior for repository.findById()
        SellerDAO sellerDAO = new SellerDAO();
        sellerDAO.setId(100);
        when(sellerRepository.findById(anyInt())).thenReturn(Optional.of(sellerDAO));
        digitalProductDAO.setSeller(sellerDAO);

        // Call the method to be tested
        DigitalProductDTO digitalProductDTO = productMapper.toProductDTO(digitalProductDAO);

        // Assertions
        assertEquals(digitalProductDAO.getId(), digitalProductDTO.getId());
        assertEquals(digitalProductDAO.getTitle(), digitalProductDTO.getTitle());
        assertEquals(digitalProductDAO.getCount(), digitalProductDTO.getCount());
        assertEquals(digitalProductDAO.getPrice(), digitalProductDTO.getPrice());
        assertEquals(digitalProductDAO.getCode(), digitalProductDTO.getCode());
        assertEquals(digitalProductDAO.getDescription(), digitalProductDTO.getDescription());
        assertEquals(digitalProductDAO.getPostDate(), digitalProductDTO.getPostDate());
        assertEquals(digitalProductDAO.getCategory(), digitalProductDTO.getCategory());
        assertEquals(digitalProductDAO.getSeller().getId(), digitalProductDTO.getSellerId());
        assertEquals(sellerDAO.getId(), digitalProductDTO.getSellerId());

    }

    @Test
    void testToProductDTOPhysicalProductDAO() {
        // Prepare test data
        PhysicalProductDAO physicalProductDAO = new PhysicalProductDAO();
        physicalProductDAO.setId(1);
        physicalProductDAO.setPrice(10);
        physicalProductDAO.setTitle("Title");
        physicalProductDAO.setPostDate(LocalDate.now());
        physicalProductDAO.setDescription("Description");
        physicalProductDAO.setCategory("CATEGORY");

        // Mock behavior for repository.findById()
        SellerDAO sellerDAO = new SellerDAO();
        sellerDAO.setId(100);
        when(sellerRepository.findById(anyInt())).thenReturn(Optional.of(sellerDAO));
        physicalProductDAO.setSeller(sellerDAO);

        // Call the method to be tested
        PhysicalProductDTO physicalProductDTO = productMapper.toProductDTO(physicalProductDAO);

        // Assertions
        assertEquals(physicalProductDAO.getId(), physicalProductDTO.getId());
        assertEquals(physicalProductDAO.getTitle(), physicalProductDTO.getTitle());
        assertEquals(physicalProductDAO.getCount(), physicalProductDTO.getCount());
        assertEquals(physicalProductDAO.getPrice(), physicalProductDTO.getPrice());
        assertEquals(physicalProductDAO.getDescription(), physicalProductDTO.getDescription());
        assertEquals(physicalProductDAO.getPostDate(), physicalProductDTO.getPostDate());
        assertEquals(physicalProductDAO.getCategory(), physicalProductDTO.getCategory());
        assertEquals(physicalProductDAO.getSeller().getId(), physicalProductDTO.getSellerId());
        assertEquals(sellerDAO.getId(), physicalProductDTO.getSellerId());
    }

    @Test
    void testToProductDAODigitalProductDTO() {
        // Prepare test data
        DigitalProductDTO digitalProductDTO = new DigitalProductDTO();
        digitalProductDTO.setId(1);
        digitalProductDTO.setSellerId(100);
        // ... set other properties

        // Mock behavior for repository.findById()
        SellerDAO sellerDAO = new SellerDAO();
        sellerDAO.setId(100);
        when(sellerRepository.findById(anyInt())).thenReturn(Optional.of(sellerDAO));

        // Call the method to be tested
        DigitalProductDAO digitalProductDAO = productMapper.toProductDAO(digitalProductDTO);

        // Assertions
        assertEquals(digitalProductDTO.getId(), digitalProductDAO.getId());
        // ... assert other properties
        assertEquals(sellerDAO.getId(), digitalProductDAO.getSeller().getId());
    }

    @Test
    void testToProductDAOPhysicalProductDTO() {

    }
}
