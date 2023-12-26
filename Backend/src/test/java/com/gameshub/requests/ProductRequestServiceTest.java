package com.gameshub.requests;

import com.gameshub.controller.DTO.request.*;
import com.gameshub.exception.*;
import com.gameshub.model.request.*;
import com.gameshub.model.user.*;
import com.gameshub.repository.request.*;
import com.gameshub.repository.user.*;
import com.gameshub.service.request.*;
import com.gameshub.utils.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import java.time.*;
import java.util.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRequestServiceTest {

    @Autowired
    private PhysicalProductRequestRepository physicalProductRequestRepository;

    @Autowired
    private DigitalProductRequestRepository digitalProductRequestRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ProductRequestMapper productRequestMapper;

    @Autowired
    private ProductRequestService productRequestService;

    @BeforeEach
    public void setup() {
        physicalProductRequestRepository.deleteAll();
        digitalProductRequestRepository.deleteAll();
        sellerRepository.deleteAll();

        physicalProductRequestRepository.resetAutoIncrement();
        digitalProductRequestRepository.resetAutoIncrement();
        sellerRepository.resetAutoIncrement();

        for(int i = 0; i < 4; i++) {
            SellerDAO sellerDAO = new SellerDAO(
                    "Seller " + (i + 1),
                    "seller" + (i + 1) + "@example.com",
                    "password123",
                    "123456789" + (i + 1),
                    "Address " + (i + 1),
                    1000.0f * (i + 1),
                    "ID-" + (i + 1),
                    LocalDate.now(),
                    "Description " + (i + 1),
                    "VAT-" + (i + 1)
            );

            sellerRepository.save(sellerDAO);

            sellerDAO = sellerRepository.findById((i + 1))
                    .orElseThrow(() -> new RuntimeException("Seller not found"));

            PhysicalProductRequestDAO physicalDAO = new PhysicalProductRequestDAO(
                    LocalDate.now(),
                    "Pending",
                    "Type",
                    "Title " + (i + 1),
                    100 * (i + 1),
                    "Description " + (i + 1),
                    LocalDate.now(),
                    i + 1,
                    "Category",
                    sellerDAO
            );
            physicalProductRequestRepository.save(physicalDAO);
        }


        for (int i = 0; i < 4; i++) {
            SellerDAO sellerDAO = sellerRepository.findById(i + 1).orElse(null);

            DigitalProductRequestDAO physicalDAO = new DigitalProductRequestDAO(
                    LocalDate.now(),
                    "Pending",
                    "Type",
                    "Title " + (i + 1),
                    100 * (i + 1),
                    "Description " + (i + 1),
                    LocalDate.now(),
                    i + 1,
                    "Category",
                    sellerDAO,
                    "Code"
            );
            digitalProductRequestRepository.save(physicalDAO);
        }

    }

    @AfterEach
    public void finish() {
        physicalProductRequestRepository.deleteAll();
        digitalProductRequestRepository.deleteAll();
        sellerRepository.deleteAll();

        physicalProductRequestRepository.resetAutoIncrement();
        digitalProductRequestRepository.resetAutoIncrement();
        sellerRepository.resetAutoIncrement();
    }

    @Test
    void testSavePhysicalProductRequest_NoDuplicates() {
        PhysicalProductRequestDTO dto = new PhysicalProductRequestDTO();
        dto.setRequestType("Pending");
        dto.setCategory("CAT");
        dto.setStatus("STAT");
        dto.setDateReceived(LocalDate.now());
        dto.setCount(10);
        dto.setDescription("DESC");
        dto.setPrice(100);
        dto.setTitle("TITLE");
        dto.setSellerId(1);
        dto.setPostDate(LocalDate.now());

        PhysicalProductRequestDAO dao = productRequestMapper.toDAO(dto);
        assertNotNull(dao);

        productRequestService.saveProductRequest(dto);

        List<PhysicalProductRequestDAO> requests = physicalProductRequestRepository.findAll();

        // One added :)
        assertEquals(5, requests.size());

        PhysicalProductRequestDAO savedDao = requests.get(4);

        assertNotNull(savedDao);
        assertEquals(savedDao.getDateReceived(), dto.getDateReceived());
        assertEquals(savedDao.getRequestType(), dto.getRequestType());
        assertEquals(savedDao.getCount(), dto.getCount());
        assertEquals(savedDao.getCategory(), dto.getCategory());
        assertEquals(savedDao.getDescription(), dto.getDescription());
        assertEquals(savedDao.getPrice(), dto.getPrice());
        assertEquals(savedDao.getTitle(), dto.getTitle());
        assertNotNull(savedDao.getSeller());
        assertEquals(savedDao.getSeller().getId(), dto.getSellerId());
        assertEquals(savedDao.getPostDate(), dto.getPostDate());
    }

    @Test
    void testSaveDigitalProductRequest_NoDuplicates() {
        DigitalProductRequestDTO dto = new DigitalProductRequestDTO();
        dto.setRequestType("Pending");
        dto.setCategory("CAT");
        dto.setStatus("STAT");
        dto.setDateReceived(LocalDate.now());
        dto.setCount(10);
        dto.setDescription("DESC");
        dto.setPrice(100);
        dto.setTitle("TITLE");
        dto.setSellerId(1);
        dto.setPostDate(LocalDate.now());
        dto.setCode("CODE");

        DigitalProductRequestDAO dao = productRequestMapper.toDAO(dto);
        assertNotNull(dao);

        productRequestService.saveProductRequest(dto);

        List<DigitalProductRequestDAO> requests = digitalProductRequestRepository.findAll();

        // One added :)
        assertEquals(5, requests.size());

        DigitalProductRequestDAO savedDao = requests.get(4);

        assertNotNull(savedDao);
        assertEquals(savedDao.getDateReceived(), dto.getDateReceived());
        assertEquals(savedDao.getRequestType(), dto.getRequestType());
        assertEquals(savedDao.getCount(), dto.getCount());
        assertEquals(savedDao.getCategory(), dto.getCategory());
        assertEquals(savedDao.getDescription(), dto.getDescription());
        assertEquals(savedDao.getPrice(), dto.getPrice());
        assertEquals(savedDao.getTitle(), dto.getTitle());
        assertNotNull(savedDao.getSeller());
        assertEquals(savedDao.getSeller().getId(), dto.getSellerId());
        assertEquals(savedDao.getPostDate(), dto.getPostDate());
        assertEquals(savedDao.getCode(), dto.getCode());
    }

    @Test
    void testSaveProductRequestWithNullInput() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            productRequestService.saveProductRequest(null);
        });

         assertThat(exception.getMessage(), containsString("Unsupported request type"));
    }

}
