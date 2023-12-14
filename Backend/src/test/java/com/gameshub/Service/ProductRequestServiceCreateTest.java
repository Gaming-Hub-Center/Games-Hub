package com.gameshub.Service;

import com.gameshub.controller.DTO.request.DigitalProductRequestDTO;
import com.gameshub.controller.DTO.request.PhysicalProductRequestDTO;
import com.gameshub.exception.ResourceAlreadyFoundException;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.request.DigitalProductRequestRepository;
import com.gameshub.repository.request.PhysicalProductRequestRepository;
import com.gameshub.repository.user.SellerRepository;
import com.gameshub.service.request.ProductRequestService;
import com.gameshub.utils.ProductRequestMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductRequestServiceCreateTest {

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
        dto.setRequestType("pending");
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
        dto.setRequestType("pending");
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
    void testSavePhysicalProductRequest_WithDuplicate_Title_and_Description() {
        // Create a PhysicalProductRequestDTO with a duplicate title
        PhysicalProductRequestDTO dto = new PhysicalProductRequestDTO();
        dto.setRequestType("pending");
        dto.setCategory("CAT");
        dto.setStatus("STAT");
        dto.setDateReceived(LocalDate.now());
        dto.setCount(10);
        dto.setDescription("DESC");
        dto.setPrice(100);
        dto.setTitle("Title 1"); // Same title as an existing request
        dto.setSellerId(1);
        dto.setPostDate(LocalDate.now());

        // Duplicate Title
        assertDoesNotThrow(() -> productRequestService.saveProductRequest(dto));

        // Duplicate Description
        dto.setDescription("Description 1");
        dto.setTitle("Dummy");
        PhysicalProductRequestDAO existingDao = productRequestMapper.toDAO(dto);
        physicalProductRequestRepository.save(existingDao);
        assertDoesNotThrow(() -> productRequestService.saveProductRequest(dto));

        // Duplicate Description and Title
        dto.setTitle("Title 1");
        existingDao = productRequestMapper.toDAO(dto);
        physicalProductRequestRepository.save(existingDao);
        Exception exception = assertThrows(ResourceAlreadyFoundException.class, () -> {
            productRequestService.saveProductRequest(dto);
        });
        assertTrue(exception.getMessage().contains("Duplicate Found"));

        dto.setStatus("Accepted");
        existingDao = productRequestMapper.toDAO(dto);
        physicalProductRequestRepository.save(existingDao);
        exception = assertThrows(ResourceAlreadyFoundException.class, () -> {
            productRequestService.saveProductRequest(dto);
        });
        assertTrue(exception.getMessage().contains("Duplicate Found"));

        dto.setStatus("other_status");
        existingDao = productRequestMapper.toDAO(dto);
        physicalProductRequestRepository.save(existingDao);
        exception = assertThrows(ResourceAlreadyFoundException.class, () -> {
            productRequestService.saveProductRequest(dto);
        });
        assertTrue(exception.getMessage().contains("Duplicate Found"));
    }

    @Test
    void testSaveDigitalProductRequest_WithDuplicate() {
        // Create a PhysicalProductRequestDTO with a duplicate title
        DigitalProductRequestDTO dto = new DigitalProductRequestDTO();
        dto.setRequestType("pending");
        dto.setCategory("CAT");
        dto.setStatus("STAT");
        dto.setDateReceived(LocalDate.now());
        dto.setCount(10);
        dto.setDescription("DESC");
        dto.setPrice(100);
        dto.setTitle("Title 1"); // Same title as an existing request
        dto.setSellerId(1);
        dto.setPostDate(LocalDate.now());
        dto.setCode("CODE");

        // Duplicate Title
        assertDoesNotThrow(() -> productRequestService.saveProductRequest(dto));

        // Duplicate Description
        dto.setDescription("Description 1");
        dto.setTitle("Dummy");
        DigitalProductRequestDAO existingDao = productRequestMapper.toDAO(dto);
        digitalProductRequestRepository.save(existingDao);
        assertDoesNotThrow(() -> productRequestService.saveProductRequest(dto));

        // Duplicate Description and Title
        dto.setTitle("Title 1");
        existingDao = productRequestMapper.toDAO(dto);
        digitalProductRequestRepository.save(existingDao);
        Exception exception = assertThrows(ResourceAlreadyFoundException.class, () -> {
            productRequestService.saveProductRequest(dto);
        });
        assertTrue(exception.getMessage().contains("Duplicate Found"));

        dto.setStatus("Accepted");
        existingDao = productRequestMapper.toDAO(dto);
        digitalProductRequestRepository.save(existingDao);
        exception = assertThrows(ResourceAlreadyFoundException.class, () -> {
            productRequestService.saveProductRequest(dto);
        });
        assertTrue(exception.getMessage().contains("Duplicate Found"));

        dto.setStatus("other_status");
        existingDao = productRequestMapper.toDAO(dto);
        digitalProductRequestRepository.save(existingDao);
        exception = assertThrows(ResourceAlreadyFoundException.class, () -> {
            productRequestService.saveProductRequest(dto);
        });
        assertTrue(exception.getMessage().contains("Duplicate Found"));

    }

    private DigitalProductRequestDTO createDTO(String title, String description, int sellerId, String status) {
        DigitalProductRequestDTO dto = new DigitalProductRequestDTO();
        dto.setRequestType("Pending");
        dto.setCategory("CAT");
        dto.setStatus(status);
        dto.setDateReceived(LocalDate.now());
        dto.setCount(10);
        dto.setDescription(description);
        dto.setPrice(100);
        dto.setTitle(title);
        dto.setSellerId(sellerId);
        dto.setPostDate(LocalDate.now());
        dto.setCode("CODE");
        return dto;
    }

    private void saveAndAssertNoException(DigitalProductRequestDTO dto) {
        assertDoesNotThrow(() -> productRequestService.saveProductRequest(dto));
    }

    private void saveAndAssertException(DigitalProductRequestDTO dto, String expectedMessage) {
        Exception exception = assertThrows(ResourceAlreadyFoundException.class, () -> {
            productRequestService.saveProductRequest(dto);
        });
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void testSaveDigitalProductRequest_NoDuplicate() {
        DigitalProductRequestDTO dto = createDTO("Unique Title", "Unique Description", 1, "Pending");
        saveAndAssertNoException(dto);
    }

    @Test
    public void whenSavePhysicalProductRequestWithNoDuplicates_thenSucceeds() {
        PhysicalProductRequestDTO dto = new PhysicalProductRequestDTO();
        dto.setTitle("Unique Title");
        dto.setDescription("Unique Description");
        dto.setSellerId(1); // Assuming seller with ID 1 exists
        dto.setStatus("Pending");

        assertDoesNotThrow(() -> productRequestService.saveProductRequest(dto));

        assertTrue(physicalProductRequestRepository
                .existsByDescriptionAndTitleAndSellerIdAndStatus(
                        dto.getDescription(),
                        dto.getTitle(),
                        dto.getSellerId(),
                        dto.getStatus()));
    }

    @Test
    public void whenSaveDigitalProductRequestWithNoDuplicates_thenSucceeds() {
        DigitalProductRequestDTO dto = new DigitalProductRequestDTO();
        dto.setTitle("Unique Title");
        dto.setDescription("Unique Description");
        dto.setSellerId(1);
        dto.setStatus("Pending");

        assertDoesNotThrow(() -> productRequestService.saveProductRequest(dto));

        assertTrue(digitalProductRequestRepository
                .existsByDescriptionAndTitleAndSellerIdAndStatus(
                        dto.getDescription(),
                        dto.getTitle(),
                        dto.getSellerId(),
                        dto.getStatus()));
    }

    @Test
    void testSaveProductRequestWithNullInput() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            productRequestService.saveProductRequest(null);
        });

        assertThat(exception.getMessage(), containsString("Unsupported request type"));
    }

}
