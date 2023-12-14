package com.gameshub.Service;


import com.gameshub.controller.DTO.request.*;
import com.gameshub.model.product.*;
import com.gameshub.model.user.*;
import com.gameshub.repository.product.*;
import com.gameshub.repository.user.*;
import com.gameshub.service.product.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.time.*;
import java.util.*;

@SpringBootTest
public class ProductServiceTests {

    @Autowired
    ProductService productService;

    @Autowired
    DigitalProductRepository digitalProductRepository;

    @Autowired
    PhysicalProductRepository physicalProductRepository;

    @Autowired
    SellerRepository sellerRepository;

    @BeforeEach
    public void setup(){
        digitalProductRepository.deleteAll();
        digitalProductRepository.resetAutoIncrement();
        physicalProductRepository.deleteAll();
        physicalProductRepository.resetAutoIncrement();
        sellerRepository.deleteAll();
        sellerRepository.resetAutoIncrement();

        sellerRepository.save(
                new SellerDAO(
                        "Marwan",
                        "m@example.com",
                        "placeholderPassword",
                        "",
                        "",
                        0.0f,
                        "",
                        LocalDate.now(),
                        "",
                        "")
        );

        Optional<SellerDAO> sellerDAO = sellerRepository.findByEmail("m@example.com");
        assert sellerDAO.isPresent();
        SellerDAO seller = sellerDAO.get();
        digitalProductRepository.save(
                new DigitalProductDAO(
                        "sample digital product",
                        10,
                        "",
                        seller.getId(),
                        1,
                        "Game",
                        "",
                        LocalDate.now()
                )
        );

        physicalProductRepository.save(
                new PhysicalProductDAO(
                        "sample physical product",
                        10,
                        "",
                        seller.getId(),
                        20,
                        "Mouse",
                        LocalDate.now()
                )
        );

        // Digital Products
        for (int i = 1; i <= 4; i++) {
            digitalProductRepository.save(
                    new DigitalProductDAO(
                            "Digital Product " + i,
                            10 + i * 5,
                            "Description for Digital Product " + i,
                            seller.getId(),
                            2 + i,
                            "Software",
                            "https://example.com/digital-product-" + i,
                            LocalDate.now()
                    )
            );
        }

        // Physical Products
        for (int i = 1; i <= 4; i++) {
            physicalProductRepository.save(
                    new PhysicalProductDAO(
                            "Physical Product " + i,
                            15 + i * 5,
                            "Description for Physical Product " + i,
                            seller.getId(),
                            20 + i * 5,
                            "Electronics",
                            LocalDate.now()
                    )
            );
        }

    }

    @AfterEach
    void finish() {
        digitalProductRepository.deleteAll();
        digitalProductRepository.resetAutoIncrement();
        physicalProductRepository.deleteAll();
        physicalProductRepository.resetAutoIncrement();
        sellerRepository.deleteAll();
        sellerRepository.resetAutoIncrement();
    }

    @Test
    public void testGetDigitalProduct() {
        // Digital Product
        DigitalProductDAO originalDigital = productService.getDigitalProductByProductID(1);
        assert originalDigital.getTitle().equals("sample digital product") && originalDigital.getDescription().isEmpty();

        DigitalProductDAO nonExistingDigital = productService.getDigitalProductByProductID(10);
        assert nonExistingDigital.toString().equals(new DigitalProductDAO().toString());
    }

    @Test
    public void testDeleteDigitalProduct() {
        assert !productService.deleteDigitalProductBySellerIdAndProductID(1, 10);
        assert productService.deleteDigitalProductBySellerIdAndProductID(1, 1);
    }

    @Test
    public void testEditDigitalProduct() {
        // Digital Product
        DigitalProductDAO originalDigital = productService.getDigitalProductByProductID(1);
        assert originalDigital.getTitle().equals("sample digital product") && originalDigital.getDescription().isEmpty();

        assert productService.updateDigitalProductByProductID(1, new ProductPatchDTO("new name", "new Description"));

        DigitalProductDAO updatedDigitalProduct = productService.getDigitalProductByProductID(1);

        assert updatedDigitalProduct.getTitle().equals("new name") && updatedDigitalProduct.getDescription().equals("new Description");
        assert !originalDigital.getTitle().equals(updatedDigitalProduct.getTitle()) && !originalDigital.getDescription().equals(updatedDigitalProduct.getDescription());
    }

    @Test
    public void testGetPhysicalProduct() {
        // Physical Product
        PhysicalProductDAO originalPhysical = productService.getPhysicalProductByProductID(1);
        assert originalPhysical.getTitle().equals("sample physical product") && originalPhysical.getDescription().isEmpty();

        PhysicalProductDAO nonExistingPhysical = productService.getPhysicalProductByProductID(10);
        assert nonExistingPhysical.toString().equals(new PhysicalProductDAO().toString());
    }

    @Test
    public void testDeletePhysicalProduct() {
        assert !productService.deletePhysicalProductBySellerIdAndProductID(1, 10);
        assert productService.deletePhysicalProductBySellerIdAndProductID(1, 1);
    }

    @Test
    public void testEditPhysicalProduct() {
        // Physical Product
        PhysicalProductDAO originalPhysical = productService.getPhysicalProductByProductID(1);
        assert originalPhysical.getTitle().equals("sample physical product") && originalPhysical.getDescription().isEmpty();

        assert productService.updatePhysicalProductByProductID(1, new ProductPatchDTO("new name", "new Description"));

        PhysicalProductDAO updatedPhysicalProduct = productService.getPhysicalProductByProductID(1);

        assert updatedPhysicalProduct.getTitle().equals("new name") && updatedPhysicalProduct.getDescription().equals("new Description");
        assert !originalPhysical.getTitle().equals(updatedPhysicalProduct.getTitle()) && !originalPhysical.getDescription().equals(updatedPhysicalProduct.getDescription());
    }

    @Test
    public void getAllDigitalProductsBySellerIDTest() {
        List<DigitalProductDAO> digitalProducts = productService.getAllDigitalProductsBySellerID(1);
        digitalProducts.remove(0);
        // Ensure there are four digital products
        assert(digitalProducts.size() == 4);

        for (DigitalProductDAO digitalProduct : digitalProducts) {
            // Add specific assertions based on your data
            assert(digitalProduct.getTitle().startsWith("Digital Product"));
            assert(digitalProduct.getPrice() > 0);
            assert(digitalProduct.getSellerID() != 0);
            // Add more assertions as needed
        }
    }

    @Test
    public void getAllPhysicalProductsBySellerIDTest() {
        List<PhysicalProductDAO> physicalProducts = productService.getAllPhysicalProductsBySellerID(1);
        physicalProducts.remove(0);
        // Ensure there are four physical products
        assert(physicalProducts.size() == 4);

        for (PhysicalProductDAO physicalProduct : physicalProducts) {
            // Add specific assertions based on your data
            assert(physicalProduct.getTitle().startsWith("Physical Product"));
            assert(physicalProduct.getPrice() > 0);
            assert(physicalProduct.getSellerID() != 0);
            // Add more assertions as needed
        }
    }

}
