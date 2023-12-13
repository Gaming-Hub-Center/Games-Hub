package com.gameshub.requests;

import com.gameshub.controller.DTO.request.DigitalProductRequestDTO;
import com.gameshub.controller.DTO.request.PhysicalProductRequestDTO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.model.user.*;
import com.gameshub.repository.user.*;
import com.gameshub.utils.ProductRequestMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

/*
*  Tests that ensures Mapping is done as it is supposed to be
* */

@SpringBootTest
public class ProductRequestMapperTest {
    @Autowired
    ProductRequestMapper productRequestMapper;

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    SellerRepository sellerRepository;

    @BeforeEach
    public void setup() {
        buyerRepository.deleteAll();
        sellerRepository.deleteAll();

        sellerRepository.resetAutoIncrement();
        buyerRepository.resetAutoIncrement();
    }

    @AfterEach
    public void finish() {
        buyerRepository.deleteAll();
        sellerRepository.deleteAll();

        sellerRepository.resetAutoIncrement();
        buyerRepository.resetAutoIncrement();
    }

    @Test
    void PhysicalProductRequestMapperFromDAOtoDTOTest() {
        SellerDAO seller = new SellerDAO(
                "Name",
                "Email",
                "Password",
                "012232",
                "Address",
                1025,
                "ID",
                LocalDate.now(),
                "Description",
                "VAT"
        );

        PhysicalProductRequestDAO dao = new PhysicalProductRequestDAO(
                LocalDate.now(),
                "Status",
                "Type",
                "Title",
                120,
                "Description",
                LocalDate.now(),
                124,
                "CAT",
                seller
        );

        PhysicalProductRequestDTO dto = productRequestMapper.toDTO(dao);

        Assertions.assertNotNull(dto);

        // Asserting each attribute individually
        Assertions.assertEquals(dao.getDateReceived(), dto.getDateReceived());
        Assertions.assertEquals(dao.getStatus(), dto.getStatus());
        Assertions.assertEquals(dao.getRequestType(), dto.getRequestType());
        Assertions.assertEquals(dao.getTitle(), dto.getTitle());
        Assertions.assertEquals(dao.getPrice(), dto.getPrice());
        Assertions.assertEquals(dao.getDescription(), dto.getDescription());
        Assertions.assertEquals(dao.getPostDate(), dto.getPostDate());
        Assertions.assertEquals(dao.getCount(), dto.getCount());
        Assertions.assertEquals(dao.getCategory(), dto.getCategory());
        Assertions.assertEquals(dao.getSeller().getId(), dto.getSellerId());

    }

    @Test
    void DigitalProductRequestMapperFromDAOtoDTOTest() {
        SellerDAO seller = new SellerDAO(
                "Name",
                "Email",
                "Password",
                "012232",
                "Address",
                1025,
                "ID",
                LocalDate.now(),
                "Description",
                "VAT"
        );

        DigitalProductRequestDAO dao = new DigitalProductRequestDAO(
                LocalDate.now(),
                "Status",
                "Type",
                "Title",
                120,
                "Description",
                LocalDate.now(),
                124,
                "CAT",
                seller,
                "Code"
        );

        DigitalProductRequestDTO dto = productRequestMapper.toDTO(dao);

        Assertions.assertNotNull(dto);

        // Asserting each attribute individually
        Assertions.assertEquals(dao.getDateReceived(), dto.getDateReceived());
        Assertions.assertEquals(dao.getStatus(), dto.getStatus());
        Assertions.assertEquals(dao.getRequestType(), dto.getRequestType());
        Assertions.assertEquals(dao.getTitle(), dto.getTitle());
        Assertions.assertEquals(dao.getPrice(), dto.getPrice());
        Assertions.assertEquals(dao.getDescription(), dto.getDescription());
        Assertions.assertEquals(dao.getPostDate(), dto.getPostDate());
        Assertions.assertEquals(dao.getCount(), dto.getCount());
        Assertions.assertEquals(dao.getCategory(), dto.getCategory());
        Assertions.assertEquals(dao.getSeller().getId(), dto.getSellerId());
        Assertions.assertEquals(dao.getCode(), dto.getCode());

    }

    @Test
    void PhysicalProductRequestFromDTOtoDAOTest() {
        SellerDAO seller = new SellerDAO(
                "Name",
                "Email",
                "Password",
                "012232",
                "Address",
                1025,
                "ID",
                LocalDate.now(),
                "Description",
                "VAT"
        );

        productRequestMapper.userService.saveNewSeller(seller);

        SellerDAO sellerDAO = (SellerDAO) productRequestMapper.userService.getByEmail(seller.getEmail());

        PhysicalProductRequestDTO dto = new PhysicalProductRequestDTO();
        dto.setRequestType("Type");
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

        Assertions.assertNotNull(dao);
        Assertions.assertEquals(dto.getDateReceived(), dao.getDateReceived());
        Assertions.assertEquals(dto.getStatus(), dao.getStatus());
        Assertions.assertEquals(dto.getRequestType(), dao.getRequestType());
        Assertions.assertEquals(dto.getTitle(), dao.getTitle());
        Assertions.assertEquals(dto.getPrice(), dao.getPrice());
        Assertions.assertEquals(dto.getDescription(), dao.getDescription());
        Assertions.assertEquals(dto.getPostDate(), dao.getPostDate());
        Assertions.assertEquals(dto.getCount(), dao.getCount());
        Assertions.assertEquals(dto.getCategory(), dao.getCategory());
//        Assertions.assertEquals(seller, dao.getSeller());

    }

    @Test
    void DigitalProductRequestFromDTOtoDAOTest() {
        SellerDAO seller = new SellerDAO(
                "Name",
                "Email2",
                "Password",
                "012232",
                "Address",
                1025,
                "ID",
                LocalDate.now(),
                "Description",
                "VAT"
        );

        productRequestMapper.userService.saveNewSeller(seller);

        SellerDAO sellerDAO = (SellerDAO) productRequestMapper.userService.getByEmail(seller.getEmail());

        DigitalProductRequestDTO dto = new DigitalProductRequestDTO();
        dto.setRequestType("Type");
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

        Assertions.assertNotNull(dao);
        Assertions.assertEquals(dto.getDateReceived(), dao.getDateReceived());
        Assertions.assertEquals(dto.getStatus(), dao.getStatus());
        Assertions.assertEquals(dto.getRequestType(), dao.getRequestType());
        Assertions.assertEquals(dto.getTitle(), dao.getTitle());
        Assertions.assertEquals(dto.getPrice(), dao.getPrice());
        Assertions.assertEquals(dto.getDescription(), dao.getDescription());
        Assertions.assertEquals(dto.getPostDate(), dao.getPostDate());
        Assertions.assertEquals(dto.getCount(), dao.getCount());
        Assertions.assertEquals(dto.getCategory(), dao.getCategory());
//        Assertions.assertEquals(seller, dao.getSeller());
        Assertions.assertEquals(dto.getCode(), dao.getCode());

    }

}
