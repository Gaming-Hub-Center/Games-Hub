package com.gameshub.requests;

import com.gameshub.model.user.*;
import com.gameshub.repository.user.*;
import com.gameshub.utils.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.time.*;
import java.util.*;

@SpringBootTest
public class ProductRequestMapperInteractionWithSellerTest {

    @Autowired
    private ProductRequestMapper productRequestMapper;

    @Autowired
    private SellerRepository sellerRepository;

    @Test
    void getAllSellersFromUserServiceInMapper() {
        // TEST to ensure that inserting many sellers reflects on the request
        List<SellerDAO> sellers = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            SellerDAO seller = new SellerDAO(
                    "Seller" + i,
                    "seller" + i + "@example.com",
                    "password" + i,
                    "123456789" + i,
                    "Address" + i,
                    1000.0f + i * 100,
                    "NationalID" + i,
                    LocalDate.now(),
                    "Description for Seller " + i,
                    "VAT" + i
            );
            sellers.add(seller);
        }

        for (int i = 0; i < 10; i++) {
            productRequestMapper.userService.saveNewSeller(sellers.get(i));
        }

        List<SellerDAO> retrievedSellers = productRequestMapper.userService.getAllSellers();

        Assertions.assertEquals(10, retrievedSellers.size(), "Number of retrieved sellers should be 10");

        // Assert that each saved seller exists in the retrieved list
        for (SellerDAO savedSeller : sellers) {
            Assertions.assertTrue(retrievedSellers.contains(savedSeller), "Saved seller should exist in retrieved sellers");
        }

    }

    // If repository exists in more than one place the repository is the same
    @Test
    public void getOneSellerFromUserServiceInMapper() {
        SellerDAO sellerDAO = new SellerDAO(
                "Seller",
                "Seller@Gmail.com",
                "Pass",
                "123456",
                "Address",
                102,
                "ID",
                LocalDate.now(),
                "Description",
                "VAT"
        );

        // Save seller into one repository
        sellerRepository.save(sellerDAO);

        // Retrieve from two sources
        Assertions.assertTrue(productRequestMapper.userService.getAllSellers().contains(sellerDAO));
        Assertions.assertTrue(sellerRepository.findByEmail(sellerDAO.getEmail()).isPresent());

        // CLEAR
        sellerRepository.deleteAll();

        // Save seller to another repository
        productRequestMapper.userService.saveNewSeller(sellerDAO);

        // Seller with the given Email exists (Retrieve from two sources By Email)
        Assertions.assertTrue(productRequestMapper.userService.getAllSellers().contains(sellerDAO));
        Assertions.assertTrue(sellerRepository.findByEmail(sellerDAO.getEmail()).isPresent());

        // Seller with id 1 exists
        // Seller with the given Id exists (Retrieve from two sources By Id)
        Assertions.assertNotNull(sellerRepository.findById(sellerDAO.getId()), "Seller with ID 1 should exist");
        Assertions.assertNotNull(productRequestMapper.userService.getSellerById(sellerDAO.getId()), "Seller with ID 1 should exist");

        // JUST to see the values in debugging
        Optional <SellerDAO> sellerDAO1 = sellerRepository.findById(sellerDAO.getId());
        Optional <SellerDAO> sellerDAO2 = sellerRepository.findByEmail(sellerDAO.getEmail());

        // CLEAR
        sellerRepository.deleteAll();

    }

}

/*
* TODO
*  1. Test the UserService in ProductRequestMapper
*       a. getAllSellers()
*       b. getSellerById()
* */